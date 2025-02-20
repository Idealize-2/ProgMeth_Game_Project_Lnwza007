import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.*;

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

    public GameScene(Stage stage, Main main) {
        this.stage = stage;
        this.main = main;
        createGameScene();
    }

    private void createGameScene() {
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gameScene = new Scene(new StackPane(canvas));

        gameScene.setOnKeyPressed(e -> keysPressed.add(e.getCode()));
        gameScene.setOnKeyReleased(e -> keysPressed.remove(e.getCode()));
        gameScene.setOnMouseClicked(e -> bullets.add(new Bullet(player.x, player.y, e.getX(), e.getY())));

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
        player = new Player(400, 300);
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        keysPressed.clear();
        lastSpawnTime = System.currentTimeMillis();
    }

    private void update() {
        if (keysPressed.contains(KeyCode.W)) player.moveUp();
        if (keysPressed.contains(KeyCode.S)) player.moveDown();
        if (keysPressed.contains(KeyCode.A)) player.moveLeft();
        if (keysPressed.contains(KeyCode.D)) player.moveRight();

        if (System.currentTimeMillis() - lastSpawnTime >= 5000) {
            enemies.add(new Monster(random.nextInt(800), random.nextInt(600)));
            lastSpawnTime = System.currentTimeMillis();
        }

        for (Monster enemy : enemies) enemy.update(player);
        bullets.removeIf(Bullet::isOutOfBounds);
        bullets.forEach(Bullet::update);

        enemies.removeIf(enemy -> bullets.removeIf(bullet -> bullet.checkCollision(enemy)));

        for (Monster enemy : enemies) {
            if (player.checkCollision(enemy)) {
                running = false;
                System.out.println("Game Over!");
                main.backToMenu();
                break;
            }
        }
    }

    private void render(GraphicsContext gc) {
        gc.clearRect(0, 0, 800, 600);
        player.render(gc);
        for (Monster enemy : enemies) enemy.render(gc);
        for (Bullet bullet : bullets) bullet.render(gc);
    }

    public void show() {
        resetGame();  // รีเซ็ตค่าตัวเกมก่อนเริ่มใหม่
        stage.setScene(gameScene);
        gameLoop.start();  // เริ่ม Animation Timer
    }
}
