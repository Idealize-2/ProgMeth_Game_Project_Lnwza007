package Scene;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.*;

import Application.*;
import Component.Bullet;
import Entity.*;

public class GameScene {
    private Stage stage;
    private Main main;
    private Scene gameScene;
    private boolean running;
    private Player player;
    private ArrayList<Monster> enemies;
    private ArrayList<Bullet> bullets;
    private Set<KeyCode> keysPressed = new HashSet<>();
    private Random random = new Random();
    private long lastSpawnTime = 0;
    private AnimationTimer gameLoop;
    
//////////////////////////////////////////// scroll map    ////////////////////////////////////////////////////////
    Image mapImage = new Image("/images/demoBG.png");
    // Map dimensions
    public static final int mapWidth = 2000;
    public static final int mapHeight = 1200;

    // Viewport (Canvas) dimensions
    private final int viewportWidth = 800;
    private final int viewportHeight = 600;
    
    // Offset for scrolling
    public double offsetX = 0;
    public double offsetY = 0;
    
    
//////////////////////////////////////////// scroll map    ////////////////////////////////////////////////////////

    public GameScene(Stage stage, Main main) {
        this.stage = stage;
        this.main = main;
        createGameScene();
    }

    private void createGameScene() {
    	
    	
    	Canvas canvas = new Canvas(viewportWidth, viewportHeight);
     
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //gc.drawImage(mapImage, 0, 0, canvas.getWidth(), canvas.getHeight());
    	
        gameScene = new Scene(new StackPane(canvas));
        
        


        gameScene.setOnKeyPressed(e -> keysPressed.add(e.getCode()));
        gameScene.setOnKeyReleased(e -> keysPressed.remove(e.getCode()));
        gameScene.setOnMouseClicked(e -> bullets.add(new Bullet(player.x, player.y, e.getX() + offsetX, e.getY() + offsetY )));
//        gameScene.setOnMouseClicked(e ->{
//        	System.out.println(e.getX() + " " + e.getY() + " Raw MOuse");
//        	System.out.println((e.getX()- offsetX) + " " + (e.getY() - offsetY) + " Minus  Offset");
//        });

        gameLoop = new AnimationTimer() {
            public void handle(long now) {
                if (running) {
                    update();
                    render(gc);
                }
            }
        };
    }

    private void resetGame() {
        running = true;

        // Initialize player in the center of the map
        player = new Player(mapWidth / 2.0, mapHeight / 2.0);

        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        keysPressed.clear();
        lastSpawnTime = System.currentTimeMillis();

        // Center the viewport on the player at the start
        offsetX = clamp(player.x - viewportWidth / 2.0, 0, mapWidth - viewportWidth);
        offsetY = clamp(player.y - viewportHeight / 2.0, 0, mapHeight - viewportHeight);
    }


    private void update() {
    	double dx = 0, dy = 0;

    	
//        if (keysPressed.contains(KeyCode.W)) player.moveUp();
//        if (keysPressed.contains(KeyCode.S)) player.moveDown();
//        if (keysPressed.contains(KeyCode.A)) player.moveLeft();
//        if (keysPressed.contains(KeyCode.D)) player.moveRight();
    	
    	if (keysPressed.contains(KeyCode.W)) dy -= player.getSpeed();
        if (keysPressed.contains(KeyCode.S)) dy += player.getSpeed();
        if (keysPressed.contains(KeyCode.A)) dx -= player.getSpeed();
        if (keysPressed.contains(KeyCode.D)) dx += player.getSpeed();

        // Update player position with clamping
        player.setX( clamp ( player.x + dx, 0, mapWidth - 40 ) ); 
        player.setY( clamp ( player.y + dy, 0, mapHeight - 40 ) );
        
        offsetX = clamp(player.x - viewportWidth / 2.0, 0, mapWidth - viewportWidth);
        offsetY = clamp(player.y - viewportHeight / 2.0, 0, mapHeight - viewportHeight);


        
        
        
        

        if (System.currentTimeMillis() - lastSpawnTime >= 3000) {
            enemies.add(new Monster(random.nextInt(800), random.nextInt(600)));
            lastSpawnTime = System.currentTimeMillis();
        }

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
        		
        		if( enemies.get(i).checkCollision( bullets.get(k) ) ) 
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
//old one
//            if (player.checkCollision(enemy)) {
//                running = false;
//                System.out.println("Game Over!");
//                main.backToMenu();
//                break;
//            }
        	
	          if (player.checkCollision(enemy)) {
	          running = false;
	          System.out.println("Game Over!");
	          main.backToMenu();
	          break;
	          }
        }
    }
    
 // Clamp a value between min and max
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

        // Draw enemies
        for (Monster enemy : enemies) enemy.render(gc , enemy.x - offsetX,enemy.y - offsetY);

        // Draw bullets
        for (Bullet bullet : bullets) bullet.render(gc, bullet.x - offsetX, bullet.y - offsetY);
    }


    public void show() {
        resetGame();  // รีเซ็ตค่าตัวเกมก่อนเริ่มใหม่
        stage.setScene(gameScene);
        gameLoop.start();  // เริ่ม Animation Timer
    }
}