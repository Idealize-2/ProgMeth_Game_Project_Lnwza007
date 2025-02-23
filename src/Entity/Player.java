package Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {
    public double x, y, speed = 3;
    private long lastShotTime = 0;
    private final long SHOOT_DELAY = 300;
    private Image playerImage;
    private int width = 40;  // ความกว้างที่ต้องการ
    private int height = 40; // ความสูงที่ต้องการ
    public Player(double x, double y) {
        this.x = x;
        this.y = y;
        try {
            // โหลดภาพ PNG
            playerImage = new Image("images/Airi_plush.jpg"); // ใส่ path ของไฟล์ PNG ที่ต้องการ

          
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

    public void moveUp() { y = Math.max(0, y - speed); }
    public void moveDown() { y = Math.min(600 - 30, y + speed); }
    public void moveLeft() { x = Math.max(0, x - speed); }
    public void moveRight() { x = Math.min(800 - 30, x + speed); }

    public void render(GraphicsContext gc) {
        gc.drawImage(playerImage, x, y, width, height);
    }

    public boolean checkCollision(Monster enemy) {
        return Math.hypot(x - enemy.x, y - enemy.y) < 25;
    }

    public boolean canShoot() {
        return System.currentTimeMillis() - lastShotTime >= SHOOT_DELAY;
    }

    public void setShootCooldown() {
        lastShotTime = System.currentTimeMillis();
    }
}
