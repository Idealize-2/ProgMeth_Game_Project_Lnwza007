package AnimationEffect;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HealthBar {
	static public void renderHealthBar(GraphicsContext gc, double x, double y, double maxHealth, double currentHealth) {
	    double barWidth = 200;  // Full width of the health bar
	    double barHeight = 20;  // Height of the health bar

	    // Calculate remaining health width
	    double healthRatio = currentHealth / maxHealth;
	    double currentBarWidth = barWidth * healthRatio;

	    // Change color based on health percentage
	    if (healthRatio > 0.6) {
	        gc.setFill(Color.GREEN);
	    } else if (healthRatio > 0.3) {
	        gc.setFill(Color.ORANGE);
	    } else {
	        gc.setFill(Color.RED);
	    }

	    // Draw the health bar
	    gc.fillRect(x, y, currentBarWidth, barHeight);

	    // Draw border
	    gc.setStroke(Color.BLACK);
	    gc.setLineWidth(2);
	    gc.strokeRect(x, y, barWidth, barHeight);
	}
}
