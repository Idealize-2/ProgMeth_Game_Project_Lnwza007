package AnimationEffect;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MoneyDisplay {
	
    public static void renderMoneyBox(GraphicsContext gc, double x, double y, double money) {
        double boxWidth = 170;
        double boxHeight = 30;
        
        double arcWidth = 10;
        double arcHeight = 10;

        // Draw the MOney box background
        gc.setFill(Color.WHITESMOKE);
        gc.fillRoundRect(x, y , boxWidth, boxHeight, arcWidth, arcHeight);
        
        // Draw border
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeRoundRect(x, y, boxWidth, boxHeight, arcWidth, arcHeight);

        
        // Draw money text
        gc.setFill(Color.GOLD);
        gc.setFont(new Font("Arial", 20));
        gc.fillText("Money: $" + money, x + 10, y + 22.5);
    }
}