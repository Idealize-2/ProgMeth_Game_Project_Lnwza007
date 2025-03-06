package AnimationEffect;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SpecialParticle extends Particle {

	public SpecialParticle(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void render(GraphicsContext gc) {
        gc.setStroke(Color.rgb(50, 150, 255, lifespan)); // Bright blue color
        gc.setLineWidth(4); // Thicker stroke for visibility

        double sparkLength = 12; // Base length of each spark
        double centerOffset = 5; // Small offset for randomness

        gc.save();
        gc.translate(x, y); // Move to center

        for (int i = 0; i < 6; i++) { // 6 sparks for a jagged effect
            double angle = Math.random() * 360; // Random angle
            double length = sparkLength + Math.random() * 6; // Varying length

            double x1 = Math.cos(Math.toRadians(angle)) * centerOffset;
            double y1 = Math.sin(Math.toRadians(angle)) * centerOffset;
            double x2 = Math.cos(Math.toRadians(angle)) * length;
            double y2 = Math.sin(Math.toRadians(angle)) * length;

            // Create a jagged look by adding a midpoint
            double midX = (x1 + x2) / 2 + (Math.random() * 4 - 2);
            double midY = (y1 + y2) / 2 + (Math.random() * 4 - 2);

            gc.beginPath();
            gc.moveTo(x1, y1);
            gc.lineTo(midX, midY);
            gc.lineTo(x2, y2);
            gc.stroke();
        }

        gc.restore();
    }

}
