package Component;
import Entity.Monster;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet {
    double x, y, speed = 5, dx, dy;

    public Bullet(double x, double y, double targetX, double targetY) {
        this.x = x + 10;
        this.y = y;

        double angle = Math.atan2(targetY - y, targetX - x);
        this.dx = Math.cos(angle) * speed;
        this.dy = Math.sin(angle) * speed;
    }

    public void update() {
        x += dx;
        y += dy;
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.ORANGE);
        gc.fillOval(x, y, 10, 10);
    }

    public boolean isOutOfBounds() {
        return x < 0 || x > 800 || y < 0 || y > 600;
    }

    public boolean checkCollision(Monster enemy) {
        return Math.hypot(x - enemy.x, y - enemy.y) < 15;
    }
}
