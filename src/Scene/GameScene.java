package Scene;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.*;

import AnimationEffect.HealthBar;
import AnimationEffect.SelectWeapon;
import AnimationEffect.UsePotionEffect;
import Application.*;
import Entity.*;
import Item.BuffItem;
import Item.Item;
import Item.buff;
import MenuController.PauseMenuController;
import MenuController.ShopMenuController;
import Weapon.Bullet;
import Weapon.Croissant;
import Weapon.Sushi;

public class GameScene {
    private Stage stage;
    private Main main;
    private Scene gameScene;
    private boolean running;
    private boolean paused = false; 
    private Player player;
    private ArrayList<Monster> enemies;
    private ArrayList<Bullet> bullets;
    private Set<KeyCode> keysPressed = new HashSet<>();
    private Random random = new Random();
    private long lastSpawnTime = 0;
    private AnimationTimer gameLoop;
    private PauseMenuController controllerPause;
    private ShopMenuController controllerShop;
    
    private Parent pauseMenuFXML;
    private Parent shopMenuFXML;
    
/////////////////////////////// NPC position and interaction range //////////////////////////////////
    private final double npcX = 1000;
    private final double npcY = 600;
    private final double interactionRange = 50;
    private boolean nearNpc = false;
    private Image npcImage = new Image("images/shopkeeper.jpg");
    
    //////////////////////////weapon select//////////////
    static private short weaponSelect = 0;

////////////////////////////////////////////scroll map    ////////////////////////////////////////////////////////
    Image mapImage = new Image("/images/demoBG.png");
    public static final int mapWidth = 2000;
    public static final int mapHeight = 1200;
    private final int viewportWidth = 800;
    private final int viewportHeight = 600;

    public double offsetX = 0;
    public double offsetY = 0;
////////////////////////////////////////////scroll map    ////////////////////////////////////////////////////////
    private StackPane root;
    
    // Shop inventory
    private final List<Item> shopItems = new ArrayList<>();
    private final List<Item> playerInventory = new ArrayList<>();
    private int playerMoney = 100; // Player starts with $100
    

    public GameScene(Stage stage, Main main) {
        this.stage = stage;
        this.main = main;
        createGameScene();
    }

    private void createGameScene() {
        Canvas canvas = new Canvas(viewportWidth, viewportHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root = new StackPane(canvas);
        gameScene = new Scene(root);
        
        ///test inventory
        playerInventory.add(new BuffItem("BerserkPotion", 10, "", "images/maki.jpg", buff.BERSERK) );

        //Set Up พวก scene เสริม
        setupShopMenu();
        setupPauseMenu();
        

        gameScene.setOnKeyPressed(e -> {
            keysPressed.add(e.getCode());
            if (e.getCode() == KeyCode.ESCAPE) {
            	togglePauseForMenu();
            }
            if (e.getCode() == KeyCode.DIGIT1) {
            	System.out.println("Sushi Selected");
            	weaponSelect = 0;
            }
            if (e.getCode() == KeyCode.DIGIT2) {
            	System.out.println("Croissant Selected");
            	weaponSelect = 1;
            }
            if (e.getCode() == KeyCode.DIGIT3) {
            	System.out.println("Pizza Bazuka Selected");
            	weaponSelect = 2;
            }
            if (e.getCode() == KeyCode.E && nearNpc) {
            	togglePauseForOpenShop();
            }
            if (e.getCode() == KeyCode.Q) {
            	((BuffItem)playerInventory.get(0)).useEffect(this.player);
            }
        });

        gameScene.setOnKeyReleased(e -> keysPressed.remove(e.getCode()));

        gameScene.setOnMouseClicked(e -> {
            if (!paused && Player.CanShoot() ) {
                //bullets.add(new Bullet(player.x, player.y, e.getX() + offsetX, e.getY() + offsetY));
            	
            	if(weaponSelect == 0)
            	{
            		bullets.add(new Sushi(player.x, player.y, e.getX() + offsetX, e.getY() + offsetY));
            		player.runCooldown(player.getAtkSpeed() + Sushi.weaponCooldown);
            	}
            	else if(weaponSelect == 1)
            	{
            		bullets.add(new Croissant(player.x, player.y, e.getX() + offsetX, e.getY() + offsetY));
            		player.runCooldown(player.getAtkSpeed() + Croissant.weaponCooldown);
            	}
            	else if(weaponSelect == 2) {
            		
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
   	        

   	    } catch (Exception e) {
   	        e.printStackTrace();
   	    }
   }
    


    public void togglePauseForMenu() {
    	
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
        if (paused && controllerShop != null) {
        	//System.out.println("work"); for debugging
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
        
        pauseMenuFXML.setVisible(false);
        shopMenuFXML.setVisible(false);
        paused = false;
        controllerPause.playGameBackgroundMusic();
    }

    private void update() {
        if (paused) return; // ถ้าหยุดเกม ไม่ต้องอัปเดต

        double dx = 0, dy = 0;

        if (keysPressed.contains(KeyCode.W)) dy -= player.getSpeed();
        if (keysPressed.contains(KeyCode.S)) dy += player.getSpeed();
        if (keysPressed.contains(KeyCode.A)) dx -= player.getSpeed();
        if (keysPressed.contains(KeyCode.D)) dx += player.getSpeed();

        player.setX(clamp(player.x + dx, 0, mapWidth - 40));
        player.setY(clamp(player.y + dy, 0, mapHeight - 40));

        offsetX = clamp(player.x - viewportWidth / 2.0, 0, mapWidth - viewportWidth);
        offsetY = clamp(player.y - viewportHeight / 2.0, 0, mapHeight - viewportHeight);

        if (System.currentTimeMillis() - lastSpawnTime >= 3000) {
        	int oEnemies = (int) (Math.random() * 3);
        	switch (oEnemies) {
			case 0:
				enemies.add(new Monster(random.nextInt(800), random.nextInt(600)));
				break;
			case 1:
				enemies.add(new MonsterWeakness(random.nextInt(800), random.nextInt(600)));
				break;
			case 2:
				enemies.add(new MonsterBoss(random.nextInt(2000), random.nextInt(2000)));
				break;
			default:
				break;
			}
            lastSpawnTime = System.currentTimeMillis();
        }
        
        double distanceToNpc = Math.hypot(player.x - npcX, player.y - npcY);
        nearNpc = distanceToNpc < interactionRange;

        for (Monster enemy : enemies) enemy.update(player);
        bullets.removeIf(Bullet::isOutOfBounds);
        bullets.forEach(Bullet::update);
        
        ///enemies.removeIf( enemy -> bullets.removeIf(bullet -> bullet.checkCollision(enemy)) );
        ///new version แก้ได้ก็ดีนะ กูไม่รู้จะเขียนไงอะ
        for ( int i = 0 ; i <  enemies.size() ; i++ ) 
        {
        	if(enemies.size() == 0)break;
        	for( int k = 0 ; k <  bullets.size() ; k++ ) 
        	{
        		if(bullets.size() == 0)break;
        		
        		if( enemies.get(i).checkCollision( bullets.get(k) ,this.player ) ) 
        		{
        			enemies.remove( i );
        			bullets.remove( k );
        			i--;
        			k--;
        			if(enemies.size() == 0)break;
        			
        			i = Math.max(i, 0);
        			k = Math.max(k, 0);
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
                controllerPause.showGameOver();
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
        player.render(gc , player.x - offsetX, player.y - offsetY);
        
        if(UsePotionEffect.isbuffAvailable) 
        {
        	UsePotionEffect.renderBerserkEffect(gc , player.x - offsetX, player.y - offsetY);
        }
     // Activate healing particles when healing
        if (UsePotionEffect.isHealing) 
        {
            UsePotionEffect.renderHealingEffect(gc ,player.x - offsetX, player.y - offsetY);
        }

        // Update & render particles
        UsePotionEffect.updateParticles(gc);

        // Draw enemies
        for (Monster enemy : enemies) enemy.render(gc , enemy.x - offsetX,enemy.y - offsetY);

        // Draw bullets
        for (Bullet bullet : bullets) bullet.render(gc, bullet.x - offsetX, bullet.y - offsetY); 
        
        
        // Draw the NPC
        double npcScreenX = npcX - offsetX;
        double npcScreenY = npcY - offsetY;
        gc.drawImage(npcImage,npcScreenX, npcScreenY, 80, 80); // NPC size
        
        if (nearNpc) {
            gc.setFill(Color.WHITE);
            gc.fillText("Press E to open shop", npcScreenX + 30, npcScreenY - 10);
        }
        ///render weapon select boxes
        SelectWeapon.renderWeaponBoxes( gc , viewportWidth ,weaponSelect);
        
        //render health bar
        HealthBar.renderHealthBar(gc, 20, 20, this.player.getMaxHp() , this.player.getHp() );
        
        
    
    }
   
	    


    public void show() {
    	paused = true;
    	togglePauseForMenu();
        resetGame();
        stage.setScene(gameScene);
        gameLoop.start();
    }
    
    public void setRunning(boolean running) {
    	this.running = running;
    }
    
}
