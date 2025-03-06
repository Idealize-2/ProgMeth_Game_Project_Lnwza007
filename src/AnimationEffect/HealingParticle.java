package AnimationEffect;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HealingParticle extends Particle{

	public HealingParticle(double x, double y) {
		super(x, y);
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.setFill(Color.rgb(50, 255, 50, lifespan)); // Green with fade-out

        double size = 15; // Size of the plus sign

        // Draw horizontal bar (centered properly)
        gc.fillRect(x - size / 2, y - size / 6, size, size / 3);

        // Draw vertical bar (centered properly)
        gc.fillRect(x - size / 6, y - size / 2, size / 3, size);
		
	}
	
}
