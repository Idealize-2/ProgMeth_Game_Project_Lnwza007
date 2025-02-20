import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Monster {
    double x, y, speed = 1.5;

    public Monster(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void update(Player player) {
        x += (player.x - x) > 0 ? speed : -speed;
        y += (player.y - y) > 0 ? speed : -speed;
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillOval(x, y, 25, 25);
    }
}
