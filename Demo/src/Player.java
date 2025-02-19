import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    private int x, y;
    private int speed = 5;
    private boolean up, down, left, right;
    private int health = 100;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        if (up) y -= speed;
        if (down) y += speed;
        if (left) x -= speed;
        if (right) x += speed;
    }

    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 40, 40);
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> up = true;
            case KeyEvent.VK_S -> down = true;
            case KeyEvent.VK_A -> left = true;
            case KeyEvent.VK_D -> right = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> up = false;
            case KeyEvent.VK_S -> down = false;
            case KeyEvent.VK_A -> left = false;
            case KeyEvent.VK_D -> right = false;
        }
    }

    public void checkCollision(Monster monster) {
        Rectangle playerBounds = new Rectangle(x, y, 40, 40);
        Rectangle monsterBounds = new Rectangle(monster.getX(), monster.getY(), 30, 30);

        if (playerBounds.intersects(monsterBounds)) {
            health -= 10;  // Player takes damage
            monster.damage(10);  // Deal damage to the enemy
        }
    }

    public Bullet shoot() {
        // Create a bullet starting from the player's current position
        return new Bullet(x + 20, y + 20);  // Bullet starts at the center of the player
    }

    public int getHealth() {
        return health;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
