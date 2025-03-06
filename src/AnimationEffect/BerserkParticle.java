package AnimationEffect;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class BerserkParticle extends Particle {

	public BerserkParticle(double x, double y) {
		super(x, y);
	}
	
	@Override
	public void render(GraphicsContext gc) {
        gc.setStroke(Color.rgb(255, 50, 50, lifespan)); // Red color with fade-out
        gc.setLineWidth(5); // Thickness of the arcs

        double size = 15; // Base size
        double arcRadius = size * 1.2; // Radius for the arc curve
        double arcAngle = 60; // Angle of the arc segment

        gc.save();
        gc.translate(x, y); // Move to center

        for (int i = 0; i < 4; i++) {
            gc.save();
            gc.rotate(i * 90); // Rotate for each arc

            gc.strokeArc(-arcRadius / 2, -arcRadius, arcRadius, arcRadius, 0, arcAngle, ArcType.OPEN);

            gc.restore();
        }

        gc.restore();
    }
	
}
