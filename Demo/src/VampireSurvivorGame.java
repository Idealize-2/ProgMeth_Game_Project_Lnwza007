import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

public class VampireSurvivorGame extends Canvas implements Runnable, KeyListener {
    private boolean isRunning = false;
    private Player player;
    private ArrayList<Monster> enemies;
    private ArrayList<Bullet> bullets;
    private Random random = new Random();
    private long lastSpawnTime = System.currentTimeMillis();

    public VampireSurvivorGame() {
        JFrame frame = new JFrame("Simple Vampire Survivor");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setVisible(true);
        
        this.addKeyListener(this);
        this.setFocusable(true);
        
        player = new Player(400, 300);  // Center of the screen
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        
        start();
    }

    public synchronized void start() {
        isRunning = true;
        new Thread(this).start();
    }

    public synchronized void stop() {
        isRunning = false;
    }

    public void run() {
        while (isRunning) {
            update();
            render();
            try {
                Thread.sleep(16); // Roughly 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Spawn enemies every 5 seconds
    private void spawnEnemies() {
        if (System.currentTimeMillis() - lastSpawnTime >= 5000) {
            enemies.add(new Monster(random.nextInt(800), random.nextInt(600), 20, 2, Color.RED));
            lastSpawnTime = System.currentTimeMillis();
        }
    }

    public void update() {
        player.update();
        
        // Update enemies and check for collision with bullets
        for (int i = enemies.size() - 1; i >= 0; i--) {
            Monster enemy = enemies.get(i);
            enemy.update(player);
            player.checkCollision(enemy);

            // Check if enemy is hit by any bullets
            for (int j = bullets.size() - 1; j >= 0; j--) {
                Bullet bullet = bullets.get(j);
                if (bullet.checkCollision(enemy)) {
                    enemy.damage(10);
                    bullets.remove(j);  // Remove the bullet upon collision
                    if (!enemy.isAlive()) {
                        enemies.remove(i);  // Remove enemy if health is 0
                    }
                }
            }
        }

        // Update bullets (move them)
        for (int i = bullets.size() - 1; i >= 0; i--) {
            bullets.get(i).update();
            if (bullets.get(i).isOutOfBounds()) {
                bullets.remove(i);  // Remove bullets that are off the screen
            }
        }

        // Spawn enemies every 5 seconds
        spawnEnemies();

        if (player.getHealth() <= 0) {
            stop();
            System.out.println("Game Over!");
        }
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.clearRect(0, 0, 800, 600);

        // Render player, enemies, and bullets
        player.render(g);
        for (Monster enemy : enemies) {
            enemy.render(g);
        }
        for (Bullet bullet : bullets) {
            bullet.render(g);
        }

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new VampireSurvivorGame();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
        // Shoot bullet on SPACE key press
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bullets.add(player.shoot());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
