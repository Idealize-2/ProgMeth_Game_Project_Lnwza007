import java.awt.*;

public class Monster {
    private int x, y;
    private int health;
    private int speed;
    private Color color;

    public Monster(int x, int y, int health, int speed, Color color) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.speed = speed;
        this.color = color;
    }

    public void update(Player player) {
        // Move towards the player
        if (player.getX() > x) x += speed;
        if (player.getX() < x) x -= speed;
        if (player.getY() > y) y += speed;
        if (player.getY() < y) y -= speed;
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, 30, 30);  // Draw the NPC enemy
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void damage(int amount) {
        health -= amount;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
