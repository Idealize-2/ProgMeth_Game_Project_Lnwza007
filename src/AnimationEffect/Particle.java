package AnimationEffect;
import javafx.scene.canvas.GraphicsContext;


public abstract class Particle {
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

    abstract public void render(GraphicsContext gc);
    
    
    




    
    
}
