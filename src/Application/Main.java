package Application;
import javafx.application.Application;
import Scene.GameMenu;
import Scene.GameScene;

import javafx.stage.Stage;

public class Main extends Application {
    private GameMenu gameMenu;
    private GameScene gameScene;

    @Override
    public void start(Stage primaryStage) {
        gameMenu = new GameMenu(primaryStage, this);
        gameScene = new GameScene(primaryStage, this);
        
        gameMenu.show(); // แสดงเมนูแรก
        primaryStage.setResizable(false); //ปรับให้ Windows เต็มจอไม่ได้
        primaryStage.setTitle("Vampire Survivor Game"); // ตั้งชื่อหน้าต่าง
        primaryStage.show(); // แสดง Stage หลังเซ็ต Scene
    }

    public void startGame() {
        gameScene.show(); // เปลี่ยนเป็นหน้าตัวเกม
    }

    public void backToMenu() {
        gameMenu.show(); // กลับไปที่เมนู
    }

    public static void main(String[] args) {
        launch(args);
    }
}
