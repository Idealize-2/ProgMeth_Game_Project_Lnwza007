package Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Monster {
    public double x, y, speed = 1.5;
    private Image monsterImage = new Image("yowtf.png"); // ใส่ path ของไฟล์ PNG ที่ต้องการ


    public Monster(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void update(Player player) {
        x += (player.x - x) > 0 ? speed : -speed;
        y += (player.y - y) > 0 ? speed : -speed;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(monsterImage, x, y, 50, 50);

//        gc.setFill(Color.RED);
//        gc.fillOval(x, y, 25, 25);
    }
}
