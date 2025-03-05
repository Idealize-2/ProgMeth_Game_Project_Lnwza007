package AnimationEffect;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class SelectWeapon {
	private static Image img1 = new Image("/images/sushi.jpg");
	private static Image img2 = new Image("/images/cr.png");
	private static Image img3 = new Image("/images/Pizza.jpg");
	
	static public void renderWeaponBoxes(GraphicsContext gc, double viewportWidth, int weapon) {
        double startX = viewportWidth - 260; // Start positioning from the right
        double y = 20; // Fixed at the top
        
        renderWeaponBox(gc, startX, y, weapon == 0 , 1);      // First box
        renderWeaponBox(gc, startX + 90, y, weapon == 1, 2); // Second box
        renderWeaponBox(gc, startX + 180, y, weapon == 2, 3); // Third box
    }

    static private void renderWeaponBox(GraphicsContext gc, double x, double y, boolean isSelected , int box) {
        double size = isSelected ? 80 : 65; // Increase size when selected
        double offset = isSelected ? -5 : 0; // Adjust position to keep centered

        // Draw the weapon box background
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(x + offset, y + offset, size, size);

        // Apply glow effect
        if (isSelected) {
            gc.setEffect(new DropShadow(30, Color.YELLOW)); // Stronger glow for selected
        } else {
            gc.setEffect(new DropShadow(20, Color.ORANGE)); // Default glow
        }

        switch (box) {
		case 1:
			gc.drawImage( img1 , x + 30 + offset, y + 30 + offset, 40, 40);
			break;
		case 2:
			gc.drawImage( img2 , x + 30 + offset, y + 30 + offset, 40, 40);
			break;
		case 3:
			gc.drawImage( img3 , x + 30 + offset, y + 30 + offset, 40, 40);
			break;
		default:
			break;
		}
 
        //gc.drawImage( img1 , x + 30 + offset, y + 30 + offset, 40, 40);
        // Reset effect
        gc.setEffect(null);
    }
}
