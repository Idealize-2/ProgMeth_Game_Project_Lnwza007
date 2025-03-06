package Scene;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

import AnimationEffect.Cooldownable;
import AnimationEffect.HealthBar;
import AnimationEffect.InventoryDisplay;
import AnimationEffect.MoneyDisplay;
import AnimationEffect.SelectWeapon;
import AnimationEffect.UsePotionEffect;
import Application.*;
import Entity.*;
import Item.BuffItem;
import Item.HealItem;
import Item.Item;
import Item.buff;
import MenuController.GameSceneController;
import MenuController.PauseMenuController;
import MenuController.ShopMenuController;
import Weapon.Bullet;
import Weapon.Croissant;
import Weapon.Pizza;
import Weapon.Chocolate;

public class GameScene implements Cooldownable{
    private Stage stage;
    private Main main;
    private Scene gameScene;
    private Canvas canvas;
    private boolean running;
    private boolean paused = false; 
    private static boolean cheatMode;
    private Player player;
    private ArrayList<Monster> enemies;
    static public ArrayList<Bullet> bullets;
    private Set<KeyCode> keysPressed = new HashSet<>();
    private Random random = new Random();
    private long lastSpawnTime = 0;
    private AnimationTimer gameLoop;
    private PauseMenuController controllerPause;
    private ShopMenuController controllerShop;
    private GameSceneController controllerGame;
    
    
    private Parent pauseMenuFXML;
    private Parent shopMenuFXML;
    private Parent gameSceneFXML;
    
    private boolean isShowLabelStage1;
    private boolean isShowLabelStage2;
    private boolean isShowLabelStage3;
    private boolean isShowLabelStage4;
    private boolean isShowLabelStage5;
    

    private long lastUpdate = System.nanoTime();
    private int currentFrameIndex = 0;
    private final long frameDelay = 500_000_000;  
    private final int frameCount = 2;
    
    private Timeline spawnCooldown;

    
    
    
/////////////////////////////////////// Stage Monster spawn///////////////////////////////////////
    private ArrayList<Short> stage1 = new ArrayList<>(); // ✅ Initialized

    { // Instance initializer block
        for (int i = 0; i < 20; i++) {
            stage1.add((short) 0);
        }
    }
    private ArrayList<Short> stage2 = new ArrayList<>(); // ✅ Initialized

    { // Instance initializer block
        for (int i = 0; i < 10; i++) {
        	stage2.add((short) 0);
        }
        for (int i = 0; i < 10; i++) {
        	stage2.add((short) 1);
        }
        Collections.shuffle(stage2);
    }
    private ArrayList<Short> stage3 = new ArrayList<>(); // ✅ Initialized

    { // Instance initializer block
        for (int i = 0; i < 10; i++) {
        	stage3.add((short) 0);
        }
        for (int i = 0; i < 20; i++) {
        	stage3.add((short) 1);
        }
        Collections.shuffle(stage3);
    }
    
    private ArrayList<Short> stage4 = new ArrayList<>(); // ✅ Initialized

    { // Instance initializer block
        for (int i = 0; i < 10; i++) {
        	stage4.add((short) 0);
        }
        for (int i = 0; i < 20; i++) {
        	stage4.add((short) 1);
        }
        for (int i = 0; i < 5; i++) {
        	stage4.add((short) 2);
        }
        Collections.shuffle(stage4);
    }
    
    private ArrayList<Short> stage5 = new ArrayList<>(); // ✅ Initialized

    { // Instance initializer block
        for (int i = 0; i < 20; i++) {
        	stage5.add((short) 0);
        }
        for (int i = 0; i < 20; i++) {
        	stage5.add((short) 1);
        }
        for (int i = 0; i < 10; i++) {
        	stage5.add((short) 2);
        }
        Collections.shuffle(stage5);
    }
    
    public boolean s1Clear = false;
    public boolean s2Clear = false;
    public boolean s3Clear = false;
    public boolean s4Clear = false;
    public boolean s5Clear = false;
    
    public boolean canSpawn = true;

    
    
/////////////////////////////// NPC position and interaction range //////////////////////////////////
    private final double npcX = 1000;
    private final double npcY = 600;
    private final double interactionRange = 100;
    private boolean nearNpc = false;
    private final Image[] npcframes = {
	        new Image("images/shopkeeper1.png"),
	        new Image("images/shopkeeper2.png")
	    };
    //////////////////////////weapon select//////////////
    public static short weaponSelect = 0;

////////////////////////////////////////////scroll map    ////////////////////////////////////////////////////////
    Image mapImage = new Image("/images/gameBackground.png");
    public static final int mapWidth = 1900;
    public static final int mapHeight = 1200;
    private final int viewportWidth = 800;
    private final int viewportHeight = 600;

    static public double offsetX = 0;
    static public double offsetY = 0;
////////////////////////////////////////////scroll map    ////////////////////////////////////////////////////////
    private StackPane root;
    
    // Shop inventory
    static public final List<Item> playerInventory = new ArrayList<>();
    static public int playerMoney = 0;
    static public short itemSelect = 0;
    

    public GameScene(Stage stage, Main main) {
        this.stage = stage;
        this.main = main;
        createGameScene();
    }

    private void createGameScene() {
    	canvas = new Canvas(viewportWidth, viewportHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root = new StackPane(canvas);
        gameScene = new Scene(root);
        
        ///test inventory
        playerInventory.add(new HealItem("MediumPotion", 30, "images/potion1.png", 50 ) );
        playerInventory.add(new HealItem("BigPotion", 50, "images/potion2.png", 100 ) );
        playerInventory.add(new BuffItem("BerserkPotion", 100, "images/potion3.png", buff.BERSERK) );
        playerInventory.add(new BuffItem("SpecialPotion", 150,"images/potion4.png", buff.SPECIAL) );

        //Set Up พวก scene เสริม
        setupGameController();
        setupShopMenu();
        setupPauseMenu();
        
        
        
        

        gameScene.setOnKeyPressed(e -> {
            keysPressed.add(e.getCode());
            if (e.getCode() == KeyCode.ESCAPE) {
            	togglePauseForMenu();
            }
            if (e.getCode() == KeyCode.DIGIT1) {
            	System.out.println("Medium Potion Selected");
            	itemSelect = 0;
            }
            if (e.getCode() == KeyCode.DIGIT2) {
            	System.out.println("Big Potion Selected");
            	itemSelect = 1;
            }
            if (e.getCode() == KeyCode.DIGIT3) {
            	System.out.println("Berserk Potion Selected");
            	itemSelect = 2;
            }
            if (e.getCode() == KeyCode.DIGIT4) {
            	System.out.println("Special Potion Selected");
            	itemSelect = 3;
            }
            if (e.getCode() == KeyCode.E && nearNpc) {
            	togglePauseForOpenShop();
            }
            if (e.getCode() == KeyCode.Q) {
            	switch (itemSelect) {
				case 0: {
					((HealItem)playerInventory.get(0)).useEffect(this.player);
					break;
				}
				case 1: {
					((HealItem)playerInventory.get(1)).useEffect(this.player);
					break;
				}
				case 2: {
					((BuffItem)playerInventory.get(2)).useEffect(this.player);
					break;
				}
				case 3: {
					((BuffItem)playerInventory.get(3)).useEffect(this.player);
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + itemSelect);
				}
            }
        });

        gameScene.setOnKeyReleased(e -> keysPressed.remove(e.getCode()));

        gameScene.setOnMouseClicked(e -> {
            if (!paused && Player.CanShoot() ) {
                //bullets.add(new Bullet(player.x, player.y, e.getX() + offsetX, e.getY() + offsetY));
            	
            	if (e.getButton() == MouseButton.PRIMARY) { // Left Click
            		playSoundShoot();
                    if (weaponSelect == 0) 
                    {
                    	if(Chocolate.getWeaponLevel() + player.getUpgradeWeapon() == 0) 
                    	{
                    		bullets.add(new Chocolate(player.x, player.y, e.getX() + offsetX, e.getY() + offsetY, 1));
                    	}
                    	else if(Chocolate.getWeaponLevel() + player.getUpgradeWeapon() == 1) 
                    	{
                    		bullets.add(new Chocolate(player.x, player.y, e.getX() + offsetX, e.getY() + offsetY, 2));
                    	}
                    	else
                    	{
                    		bullets.add(new Chocolate(player.x, player.y, e.getX() + offsetX, e.getY() + offsetY, 4));
                    	}
                        player.runCooldown(player.getAtkSpeed() + Chocolate.getWeaponCooldown() );
                    } 
                    else if (weaponSelect == 1) 
                    {
                    	if(Croissant.getWeaponLevel() + player.getUpgradeWeapon() == 0) 
                    	{
                    		bullets.add(new Croissant(player.x, player.y, e.getX() + offsetX, e.getY() + offsetY));
                    	}
                    	else if(Croissant.getWeaponLevel() + player.getUpgradeWeapon() == 1) 
                    	{
                    		 double angle = Math.atan2(e.getY() - player.y, e.getX() - player.x);
                    		 bullets.add(new Croissant(player.x, player.y, 
                                     e.getX() + offsetX + Math.cos(angle + Math.PI / 2) * 30, 
                                     e.getY() + offsetY + Math.sin(angle + Math.PI / 2) * 30));
           
                    		 bullets.add(new Croissant(player.x, player.y, 
                                     e.getX() + offsetX - Math.cos(angle + Math.PI / 2) * 30, 
                                     e.getY() + offsetY - Math.sin(angle + Math.PI / 2) * 30));
                    	}
                    	else
                    	{
                    		double angle = Math.atan2(e.getY() - player.y, e.getX() - player.x);
                    		bullets.add(new Croissant(player.x, player.y, e.getX() + offsetX, e.getY() + offsetY));
                    		
                    		bullets.add(new Croissant(player.x, player.y, 
                                    e.getX() + offsetX + Math.cos(angle + Math.PI / 2) * 30, 
                                    e.getY() + offsetY + Math.sin(angle + Math.PI / 2) * 30));
          
                    		bullets.add(new Croissant(player.x, player.y, 
                                    e.getX() + offsetX - Math.cos(angle + Math.PI / 2) * 30, 
                                    e.getY() + offsetY - Math.sin(angle + Math.PI / 2) * 30));
                    	}
                        player.runCooldown(player.getAtkSpeed() + Croissant.getWeaponCooldown() );
                        
                    } else if (weaponSelect == 2) 
                    {
                    	 bullets.add(new Pizza(player.x, player.y, e.getX() + offsetX, e.getY() + offsetY));
                         player.runCooldown(player.getAtkSpeed() + Pizza.getWeaponCooldown() );
                    }
                } else if (e.getButton() == MouseButton.SECONDARY) { // Right Click
                	weaponSelect++;
                	if(weaponSelect == 3) weaponSelect = 0;
                }
            }
        });


        gameLoop = new AnimationTimer() {
            public void handle(long now) {
                if (running && !paused) {
                    update();
                    render(gc);
                }
            }
        };
    }
    
    private void playSoundShoot() {
        String soundPath = getClass().getResource("/music/SFX/throwFood.wav").toExternalForm();
        Media sound = new Media(soundPath);
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(Main.getCurrentVolume());
        mediaPlayer.play();
    }
    
    private void setupPauseMenu() {
    	 try {
    	        // โหลด FXML และกำหนด controller
    	        FXMLLoader loader = new FXMLLoader(getClass().getResource("PauseMenuFXML.fxml"));
    	        
    	        // นำเข้าฉากจาก FXML
    	        pauseMenuFXML = loader.load();
    	        
    	        // เพิ่มใน root ที่เป็น StackPane ของเรา
    	        root.getChildren().add(pauseMenuFXML);
    	        
    	        // กำหนดให้สามารถซ่อน/แสดงเมนู Pause ได้
    	        pauseMenuFXML.setVisible(false);  // ซ่อนเมนูในตอนแรก

    	        // เรียก SetController เพื่อให้มีการใช้ Main ใน FXML (ถ้าจำเป็น)
    	        controllerPause = loader.getController();
    	        controllerPause.setMain(main);
    	        controllerPause.setGameScene(this);
    	        

    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    }
    
    private void setupShopMenu() {
   	 try {
   	        // โหลด FXML และกำหนด controller
   	        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShopMenuFXML.fxml"));
   	        
   	        // นำเข้าฉากจาก FXML
   	        shopMenuFXML = loader.load();
   	        
   	        // เพิ่มใน root ที่เป็น StackPane ของเรา
   	        root.getChildren().add(shopMenuFXML);
   	        
   	        // กำหนดให้สามารถซ่อน/แสดงเมนู Pause ได้
   	        shopMenuFXML.setVisible(false);  // ซ่อนเมนูในตอนแรก

   	        // เรียก SetController เพื่อให้มีการใช้ Main ใน FXML (ถ้าจำเป็น)
   	        controllerShop = loader.getController();
   	        controllerShop.setMain(main);
   	        controllerShop.setGameScene(this);
   	        controllerShop.setCanvas(canvas);
   	        

   	    } catch (Exception e) {
   	        e.printStackTrace();
   	    }
   }
    
    private void setupGameController() {
    	try {
   	        // โหลด FXML และกำหนด controller
   	        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameSceneFXML.fxml"));
   	        
   	        // นำเข้าฉากจาก FXML
   	        gameSceneFXML = loader.load();
   	        gameSceneFXML.setMouseTransparent(true);
   	        
   	        // เพิ่มใน root ที่เป็น StackPane ของเรา
   	        root.getChildren().add(gameSceneFXML);
   	        
   	        // กำหนดให้สามารถซ่อน/แสดงเมนู Pause ได้
   	        gameSceneFXML.setVisible(true);  

   	        // เรียก SetController เพื่อให้มีการใช้ Main ใน FXML (ถ้าจำเป็น)
   	        controllerGame = loader.getController();
   	        controllerGame.setMain(main);
   	        controllerGame.setGameScene(this);
   	        controllerGame.setCanvas(canvas);
   	        

   	    } catch (Exception e) {
   	        e.printStackTrace();
   	    }
    	
    }
    


    public void togglePauseForMenu() {
    	if(s5Clear)return;
    	if(!shopMenuFXML.isVisible())paused = !paused;
    	
    	if (pauseMenuFXML.isVisible() && shopMenuFXML.isVisible()) pauseMenuFXML.setVisible(false);
    	else pauseMenuFXML.setVisible(paused);
        
        
        if (paused && controllerPause != null) {
        	//System.out.println("work"); for debugging
            controllerPause.ensurePauseMenuVisible();
        }
    }
    
    public void togglePauseForOpenShop() {
    	
    	paused = !paused;
        shopMenuFXML.setVisible(paused);
        controllerShop.renderMoney();
        if (paused && controllerShop != null) {
        	//System.out.println("work"); for debugging
        	ShopMenuController.shopWeaponSelect = 0;
        	controllerShop.selectedButtonCorrecting();
            controllerShop.ensureWeaponShopVisible();
        }
    }

    public void resetGame() {
        running = true;
        player = new Player(mapWidth / 2.0, mapHeight / 2.0);
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        keysPressed.clear();
        lastSpawnTime = System.currentTimeMillis();
        offsetX = clamp(player.x - viewportWidth / 2.0, 0, mapWidth - viewportWidth);
        offsetY = clamp(player.y - viewportHeight / 2.0, 0, mapHeight - viewportHeight);
        
        if(spawnCooldown!=null)spawnCooldown.stop();
        backToOriginal();
        
        pauseMenuFXML.setVisible(false);
        shopMenuFXML.setVisible(false);
        paused = false;
        controllerPause.playGameBackgroundMusic();
        runCooldown(5000);
        
    }
    public static void setCheat(boolean cheat) {
    	cheatMode = cheat;
    }
    public void backToOriginal() {
    	player.setHp(player.getMaxHp());
    	s1Clear = s2Clear = s3Clear = s4Clear = s5Clear = false;
    	canSpawn = true;
    	if(cheatMode)playerMoney = 10000; //for test
    	else playerMoney = 0;
        itemSelect = 0;
        weaponSelect = 0;
        monsterIndex = -1;
        
    	Croissant.backToOriginal();
    	Chocolate.backToOriginal();
    	Pizza.backToOriginal();
    	for (Item item : playerInventory) {
			item.setItemCount(0);
		}
    	
    	resetStageIcon();
        controllerGame.setStageNo(1);
    }
    @Override
	public void runCooldown(long cooldown) {
    	canSpawn = false;
    	spawnCooldown = new Timeline(
			    new KeyFrame(Duration.millis(cooldown), e -> canSpawn = true )
		);
	    
    	spawnCooldown.setCycleCount(1);
    	spawnCooldown.play();
	}
    public void spawnMonster(int Monster)
    {
    	System.out.println("Enemies count: " + enemies.size());

    	switch (Monster) {
		case 0:
			enemies.add(new Monster(random.nextInt(1900), random.nextInt(1100)));
			System.out.println("Monster spawn!!");
			break;
		case 1:
			enemies.add(new MonsterWeakness(random.nextInt(1900), random.nextInt(1100)));
			System.out.println("MonsterWeak spawn!!");
			break;
		case 2:
			enemies.add(new MonsterBoss(random.nextInt(1900), random.nextInt(1100)));
			System.out.println("MonsterBoss spawn!!");
			break;
		default:
			break;
    	}	
    }
    static public boolean playerSide = false;
    

    private void update() {
        if (paused) return; // ถ้าหยุดเกม ไม่ต้องอัปเดต

        double dx = 0, dy = 0;

        if (keysPressed.contains(KeyCode.W)) dy -= player.getSpeed();
        if (keysPressed.contains(KeyCode.S)) dy += player.getSpeed();
        if (keysPressed.contains(KeyCode.A)) {
        	dx -= player.getSpeed();
        	playerSide = false;
        }
        if (keysPressed.contains(KeyCode.D)) {
        	dx += player.getSpeed();
        	playerSide = true;
        }

        player.setX(clamp(player.x + dx, 0, mapWidth - 40));
        player.setY(clamp(player.y + dy, 0, mapHeight - 40));

        offsetX = clamp(player.x - viewportWidth / 2.0, 0, mapWidth - viewportWidth);
        offsetY = clamp(player.y - viewportHeight / 2.0, 0, mapHeight - viewportHeight);
        
        
        double distanceToNpc = Math.hypot(player.x - npcX, player.y - npcY);
        nearNpc = distanceToNpc < interactionRange;

        for (Monster enemy : enemies) enemy.update(player);
        bullets.removeIf(Bullet::isOutOfBounds);
        bullets.forEach(Bullet::update);
        
        
        RunGameStage();

        ///new version แก้ได้ก็ดีนะ กูไม่รู้จะเขียนไงอะ
        for ( int i = 0 ; i <  enemies.size() ; i++ ) 
        {
        	if(enemies.size() == 0)break;
        	for( int k = 0 ; k <  bullets.size() ; k++ ) 
        	{
        		if(bullets.size() == 0)break;
        		
        		
        		if( enemies.get(i).checkCollision( bullets.get(k) ,this.player ) ) 
        		{
        			if( bullets.get(k).checkCollision( enemies.get(i) ) ) 
            		{
            			bullets.remove( k );
            			k--;
            			k = Math.max(k, 0);
            		}
        			playerMoney += enemies.get(i).getReward();
        			enemies.remove( i );
        			//bullets.remove( k );
        			i--;
        			//k--;
        			i = Math.max(i, 0);
        			if(enemies.size() == 0)break;
        			
        			//i = Math.max(i, 0);
        			//k = Math.max(k, 0);
        			continue;
        		}
        		if( bullets.get(k).checkCollision( enemies.get(i) ) ) 
        		{
        			bullets.remove( k );
        			k--;
        			k = Math.max(k, 0);
        		}
        		
        	}
        }

        for (Monster enemy : enemies) {
            if (player.checkCollision(enemy)) {
                running = false;
                System.out.println("Game Over!");
                pauseMenuFXML.setVisible(true);
                controllerPause.fadeToBlackAndShowGameOver();
                break;
            }
        }
    }

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    private void render(GraphicsContext gc) {
    	// Clear canvas
        gc.clearRect(0, 0, viewportWidth, viewportHeight);

        // Draw the map image shifted by the offset
        gc.drawImage(mapImage, offsetX, offsetY, viewportWidth, viewportHeight,
                0, 0, viewportWidth, viewportHeight);

        // Draw player relative to the viewport
        player.renderAnimation(gc , player.x - offsetX, player.y - offsetY);
        
        
        if(UsePotionEffect.isbuffAvailable) 
        {
        	if(UsePotionEffect.isbuffBerserkAvailable)
        	{
        		UsePotionEffect.renderBerserkEffect(gc , player.x - offsetX, player.y - offsetY);
        	}
        	else if(UsePotionEffect.isbuffSpecialAvailable)
        	{
        		UsePotionEffect.renderSpecialEffect(gc, player.x - offsetX, player.y - offsetY);
        	}
        }
     // Activate healing particles when healing
        if (UsePotionEffect.isHealing) 
        {
            UsePotionEffect.renderHealingEffect(gc ,player.x - offsetX, player.y - offsetY);
        }

        // Update & render particles
        UsePotionEffect.updateParticles(gc);
        
        // Draw bullets
        for (Bullet bullet : bullets) bullet.render(gc, bullet.x - offsetX, bullet.y - offsetY); 
        
        // Draw enemies
        for (Monster enemy : enemies) enemy.renderAnimation(gc , enemy.x - offsetX,enemy.y - offsetY);

        // Draw bullets
        //for (Bullet bullet : bullets) bullet.render(gc, bullet.x - offsetX, bullet.y - offsetY); 
        //RunGameStage(); //code ลับห้ามเปิดดูเด็ดขาด
        
        // Draw the NPC
        double npcScreenX = npcX - offsetX;
        double npcScreenY = npcY - offsetY;
        
        //<-------npc animation---------->
        
        long now = System.nanoTime();
	    if (now - lastUpdate >= frameDelay) {
	         currentFrameIndex = (currentFrameIndex + 1) % frameCount; // Loop animation
	         lastUpdate = now;
	     }
	    gc.drawImage(npcframes[currentFrameIndex], npcScreenX, npcScreenY, 80, 80);
        
        if (nearNpc) {
            gc.setFill(Color.BLACK);
            gc.fillText("Press E to open shop", npcScreenX + 30, npcScreenY - 10);
        }
        ///render weapon select boxes
        SelectWeapon.renderWeaponBoxes( gc , viewportWidth ,weaponSelect);
        
        //render health bar
        HealthBar.renderHealthBar(gc, 20, 20, this.player.getMaxHp() , this.player.getHp() );
        //render Current MOney
        MoneyDisplay.renderMoneyBox(gc, 20, 50, playerMoney);
        
        //render Player Inventory
        InventoryDisplay.renderInventory(gc, 800, 600, itemSelect,
        		playerInventory.get(0).getItemCount(),
        		playerInventory.get(1).getItemCount(),
        		playerInventory.get(2).getItemCount(),
        		playerInventory.get(3).getItemCount());
        
    
    }
    private int monsterIndex = -1;

    public void RunGameStage()
    {
        //stage 1
        if(!s1Clear) {
        	if(canSpawn)monsterIndex++;
        	if(monsterIndex >= stage1.size())monsterIndex = stage1.size();
        	//System.out.println("Can spawn: " + canSpawn);
        	//System.out.println("enemies left"+ enemies.size());
        	
        	if (canSpawn && monsterIndex < stage1.size() ) {
    	    	runCooldown(500);
    	    	spawnMonster(stage1.get(monsterIndex));
    	    	System.out.println("Stage 1 SpawnMonster!!!");
    	     }
    	    if(monsterIndex == stage1.size() && enemies.isEmpty() ) {
    	    	s1Clear = true;
    	    	monsterIndex = -1;
    	    	System.out.println("Stage 1 Clear!!!");
    	    	controllerGame.setStageNo(2);
    	    }

        }

	    
	    
	    //stage 2
        if(s1Clear && !s2Clear) {
        	
        	if(canSpawn)monsterIndex++;
        	if(monsterIndex >= stage2.size() )monsterIndex = stage2.size();
        	//System.out.println("Can spawn: " + canSpawn);
        	
        	if (canSpawn && monsterIndex < stage2.size() ) {
    	    	runCooldown(500);
    	    	spawnMonster(stage2.get(monsterIndex));
    	    	System.out.println("Stage 2 SpawnMonster!!!");
    	     }
    	    if(monsterIndex == stage2.size() && enemies.isEmpty() ) {
    	    	s2Clear = true;
    	    	monsterIndex = -1;
    	    	System.out.println("Stage 2 Clear!!!");
    	    	controllerGame.setStageNo(3);
    	    }

        }

	    
	    //// stage 3
	    if(s2Clear && !s3Clear) {
        	
        	if(canSpawn)monsterIndex++;
        	if(monsterIndex >= stage3.size() )monsterIndex = stage3.size();
        	//System.out.println("Can spawn: " + canSpawn);
        	
        	if (canSpawn && monsterIndex < stage3.size() ) {
    	    	runCooldown(500);
    	    	spawnMonster(stage3.get(monsterIndex));
    	    	System.out.println("Stage 3 SpawnMonster!!!");
    	    }
    	    if(monsterIndex == stage3.size() && enemies.isEmpty() ) {
    	    	s3Clear = true;
    	    	monsterIndex = -1;
    	    	System.out.println("Stage 3 Clear!!!");
    	    	controllerGame.setStageNo(4);
    	    }

        }

	    
	    
	    	//// stage 4
		    if(s3Clear && !s4Clear) {
	        	
	        	if(canSpawn)monsterIndex++;
	        	if(monsterIndex >= stage4.size() )monsterIndex = stage4.size();
	        	//System.out.println("Can spawn: " + canSpawn);
	        	
	        	if (canSpawn && monsterIndex < stage4.size() ) {
			    	runCooldown(100);
			    	spawnMonster(stage4.get(monsterIndex));
			    	System.out.println("Stage 4 SpawnMonster!!!");
			    }
			    if(monsterIndex == stage4.size() && enemies.isEmpty() ) {
			    	s4Clear = true;
			    	monsterIndex = -1;
			    	System.out.println("Stage 4 Clear!!!");
			    	controllerGame.setStageNo(5);
			    }
	        }

		    
		//// stage 5
		    if(s4Clear && !s5Clear) {
	        	
	        	if(canSpawn)monsterIndex++;
	        	if(monsterIndex >= stage5.size() )monsterIndex = stage5.size();
	        	System.out.println("Can spawn: " + canSpawn);
	        	if (canSpawn && monsterIndex < stage5.size() ) {
			    	runCooldown(100);
			    	spawnMonster(stage5.get(monsterIndex));
			    	System.out.println("Stage 5 SpawnMonster!!!");
			    }
			    if(monsterIndex == stage5.size() && enemies.isEmpty() ) {
			    	s5Clear = true;
			    	monsterIndex = -1;
			    	System.out.println("Stage 5 Clear!!!");
			    }

	        }

		    
		    
		    if(s1Clear && s2Clear && s3Clear && s4Clear && s5Clear)
		    {
		    	running = false;
		    	System.out.println("yeah u win");
		    	pauseMenuFXML.setVisible(true);
                controllerPause.showWinScene();
		    	
		    }
		    
		    //Show Stage Logo zone
		    if(!isShowLabelStage1) {
	        	isShowLabelStage1 = true;
	        	Thread thread = new Thread(()->{
	        		try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		controllerGame.showStage1();
	        	});
	        	thread.start();
	        }
		    else if (!isShowLabelStage2 && s1Clear){
		    	isShowLabelStage2 = true;
		    	controllerGame.showStage2();
		    }
		    else if (!isShowLabelStage3 && s2Clear) {
		        isShowLabelStage3 = true;
		        controllerGame.showStage3();
		    }
		    else if (!isShowLabelStage4 && s3Clear) {
		        isShowLabelStage4 = true;
		        controllerGame.showStage4();
		    }
		    else if (!isShowLabelStage5 && s4Clear) {
		        isShowLabelStage5 = true;
		        controllerGame.showStage5();
		    }
		    
		    controllerGame.setMonsterLeft(enemies.size());
    }
    private void resetStageIcon() {
    	isShowLabelStage1 = false;
    	isShowLabelStage2 = false;
    	isShowLabelStage3 = false;
    	isShowLabelStage4 = false;
    	isShowLabelStage5 = false;
    	
    }
   
	    


    public void show() {
    	paused = true;
    	togglePauseForMenu();
        resetGame();
        gameLoop.start();
        stage.setScene(gameScene);
        
    }
    
    public void setRunning(boolean running) {
    	this.running = running;
    }

	
    
}
