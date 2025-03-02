package AnimationEffect;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Particle {
    double x, y;
    double velocityX, velocityY;
    double lifespan = 1.0; // Opacity (1.0 = fully visible, 0.0 = invisible)

    public Particle(double x, double y) {
        this.x = x;
        this.y = y;
        this.velocityX = Math.random() * 2 - 1; // Random horizontal movement
        this.velocityY = Math.random() * 2 - 1; // Random vertical movement
    }

    public void update() {
        x += velocityX;  
        y += velocityY;
        lifespan -= 0.020; // Gradually fade out
    }

    public boolean isDead() {
        return lifespan <= 0;
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.rgb(50, 255, 50, lifespan)); // Green with fade-out

        double size = 15; // Size of the plus sign

        // Draw horizontal bar (centered properly)
        gc.fillRect(x - size / 2, y - size / 6, size, size / 3);

        // Draw vertical bar (centered properly)
        gc.fillRect(x - size / 6, y - size / 2, size / 3, size);
    }
}
