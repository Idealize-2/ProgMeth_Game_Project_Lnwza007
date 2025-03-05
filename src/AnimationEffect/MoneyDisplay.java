package AnimationEffect;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MoneyDisplay {
	
    public static void renderMoneyBox(GraphicsContext gc, double x, double y, double money) {
        double boxWidth = 150;
        double boxHeight = 30;

        // Draw the box background
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(x, y, boxWidth, boxHeight);
        
        // Draw border
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeRect(x, y, boxWidth, boxHeight);
        
        // Draw money text
        gc.setFill(Color.GOLD);
        gc.setFont(new Font("Arial", 20));
        gc.fillText("Money: $" + money, x + 10, y + 22.5);
    }
}