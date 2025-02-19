import java.awt.*;

public class Bullet {
    private int x, y;
    private int speed = 10;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        // Bullets will move upwards
        y -= speed;
    }

    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, 10, 10);
    }

    public boolean checkCollision(Monster monster) {
        Rectangle bulletBounds = new Rectangle(x, y, 10, 10);
        Rectangle monsterBounds = new Rectangle(monster.getX(), monster.getY(), 30, 30);
        return bulletBounds.intersects(monsterBounds);
    }

    public boolean isOutOfBounds() {
        return y < 0 || y > 600;
    }
}
