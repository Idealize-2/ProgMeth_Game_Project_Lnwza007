import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;

public class VampireSurvivorGame extends Application {
    private final int WIDTH = 800, HEIGHT = 600;
    private boolean running = true;

    private Player player;
    private ArrayList<Monster> enemies;
    private ArrayList<Bullet> bullets;
    private Set<KeyCode> keysPressed = new HashSet<>();
    private Random random = new Random();
    private long lastSpawnTime = 0;

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Scene scene = new Scene(new javafx.scene.layout.StackPane(canvas));
        stage.setScene(scene);
        stage.setTitle("JavaFX Vampire Survivor");
        stage.show();

        player = new Player(WIDTH / 2, HEIGHT / 2);
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();

        scene.setOnKeyPressed(e -> keysPressed.add(e.getCode()));
        scene.setOnKeyReleased(e -> keysPressed.remove(e.getCode()));

        AnimationTimer gameLoop = new AnimationTimer() {
            public void handle(long now) {
                if (running) {
                    update();
                    render(gc);
                }
            }
        };
        gameLoop.start();
    }

    private void update() {
        // Handle movement based on multiple key presses
        if (keysPressed.contains(KeyCode.W)) player.moveUp();
        if (keysPressed.contains(KeyCode.S)) player.moveDown();
        if (keysPressed.contains(KeyCode.A)) player.moveLeft();
        if (keysPressed.contains(KeyCode.D)) player.moveRight();
        if (keysPressed.contains(KeyCode.SPACE) && player.canShoot()) {
            bullets.add(new Bullet(player.x, player.y));
            player.setShootCooldown();
        }

        // Enemy spawning every 5 sec
        if (System.currentTimeMillis() - lastSpawnTime >= 5000) {
            enemies.add(new Monster(random.nextInt(WIDTH), random.nextInt(HEIGHT)));
            lastSpawnTime = System.currentTimeMillis();
        }

        // Update enemies
        Iterator<Monster> enemyIter = enemies.iterator();
        while (enemyIter.hasNext()) {
            Monster enemy = enemyIter.next();
            enemy.update(player);
            if (player.checkCollision(enemy)) {
                running = false;  // Stop game if player is caught
                System.out.println("Game Over!");
                break;
            }
        }

        // Update bullets and check collisions
        Iterator<Bullet> bulletIter = bullets.iterator();
        while (bulletIter.hasNext()) {
            Bullet bullet = bulletIter.next();
            bullet.update();
            if (bullet.isOutOfBounds()) {
                bulletIter.remove();
            } else {
                // Check if bullet hits any enemy
                Iterator<Monster> enemyCheck = enemies.iterator();
                while (enemyCheck.hasNext()) {
                    Monster enemy = enemyCheck.next();
                    if (bullet.checkCollision(enemy)) {
                        enemyCheck.remove();  // Remove enemy
                        bulletIter.remove();  // Remove bullet
                        break;
                    }
                }
            }
        }
    }

    private void render(GraphicsContext gc) {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        player.render(gc);
        for (Monster enemy : enemies) enemy.render(gc);
        for (Bullet bullet : bullets) bullet.render(gc);
    }

    public static void main(String[] args) {
        launch(args);
    }

    // 🏃 Player Class
    static class Player {
        double x, y, speed = 3;
        private long lastShotTime = 0;
        private final long SHOOT_DELAY = 300; // 300ms delay between shots

        Player(double x, double y) {
            this.x = x;
            this.y = y;
        }

        void moveUp() { y = Math.max(0, y - speed); }
        void moveDown() { y = Math.min(600 - 30, y + speed); }
        void moveLeft() { x = Math.max(0, x - speed); }
        void moveRight() { x = Math.min(800 - 30, x + speed); }

        void render(GraphicsContext gc) {
            gc.setFill(Color.BLUE);
            gc.fillOval(x, y, 30, 30);
        }

        boolean checkCollision(Monster enemy) {
            return Math.hypot(x - enemy.x, y - enemy.y) < 25;
        }

        boolean canShoot() {
            return System.currentTimeMillis() - lastShotTime >= SHOOT_DELAY;
        }

        void setShootCooldown() {
            lastShotTime = System.currentTimeMillis();
        }
    }

    // 👾 Monster Class
    static class Monster {
        double x, y, speed = 1.5;

        Monster(double x, double y) {
            this.x = x;
            this.y = y;
        }

        void update(Player player) {
            x += (player.x - x) > 0 ? speed : -speed;
            y += (player.y - y) > 0 ? speed : -speed;
        }

        void render(GraphicsContext gc) {
            gc.setFill(Color.RED);
            gc.fillOval(x, y, 25, 25);
        }
    }

    // 🔫 Bullet Class
    static class Bullet {
        double x, y, speed = 5;

        Bullet(double x, double y) {
            this.x = x + 10;  // Offset to start at player position
            this.y = y;
        }

        void update() { y -= speed; }

        void render(GraphicsContext gc) {
            gc.setFill(Color.ORANGE);
            gc.fillOval(x, y, 10, 10);
        }

        boolean isOutOfBounds() {
            return y < 0;
        }

        boolean checkCollision(Monster enemy) {
            return Math.hypot(x - enemy.x, y - enemy.y) < 15;
        }
    }
}
