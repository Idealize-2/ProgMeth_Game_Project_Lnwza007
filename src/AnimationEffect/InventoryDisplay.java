package AnimationEffect;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class InventoryDisplay {
    private static Image img1 = new Image("/images/Potion1.jpg");
    private static Image img2 = new Image("/images/Potion2.jpg");
    private static Image img3 = new Image("/images/Potion3.jpg");
    private static Image img4 = new Image("/images/Potion4.jpg");

    public static void renderInventory(GraphicsContext gc, double viewportWidth, double viewportHeight, int selectedItem, int cnt1, int cnt2, int cnt3, int cnt4) {
        double startX = (viewportWidth - 360) / 2; // Center the inventory bar
        double y = viewportHeight - 75; // Position at bottom center

        renderItemBox(gc, startX, y, selectedItem == 0, 1, cnt1);
        renderItemBox(gc, startX + 90, y, selectedItem == 1, 2, cnt2);
        renderItemBox(gc, startX + 180, y, selectedItem == 2, 3, cnt3);
        renderItemBox(gc, startX + 270, y, selectedItem == 3, 4, cnt4);
    }

    private static void renderItemBox(GraphicsContext gc, double x, double y, boolean isSelected, int box, int count) {
        double size = isSelected ? 80 : 65;
        double offset = isSelected ? -5 : 0;

        // Draw the item box background
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(x + offset, y + offset, size, size);

        // Apply glow effect
        if (isSelected) {
            gc.setEffect(new DropShadow(30, Color.YELLOW));
        } else {
            gc.setEffect(new DropShadow(20, Color.ORANGE));
        }

        // Draw item image
        switch (box) {
            case 1:
                gc.drawImage(img1, x + 10 + offset, y + 10 + offset, 40, 40);
                break;
            case 2:
                gc.drawImage(img2, x + 10 + offset, y + 10 + offset, 40, 40);
                break;
            case 3:
                gc.drawImage(img3, x + 10 + offset, y + 10 + offset, 40, 40);
                break;
            case 4:
                gc.drawImage(img4, x + 10 + offset, y + 10 + offset, 40, 40);
                break;
        }

        // Draw item count
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 16));
        gc.fillText("x" + count, x + offset + 50, y + offset + 60);

        // Reset effect
        gc.setEffect(null);
    }
}
