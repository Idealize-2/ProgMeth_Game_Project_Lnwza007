package Scene;

import Application.Main;
import MenuController.GameMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameMenu {
    private Stage stage;
    private Main main;
    private Scene menuScene;
    private GameMenuController gameMenuController;
    

    public GameMenu(Stage stage, Main main) {
        this.stage = stage;
        this.main = main;
        createMenu();
    }

    private void createMenu() {
        try {
            // โหลด FXML และกำหนด controller ด้วยมือ
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GameMenuFXML.fxml"));
            gameMenuController = new GameMenuController();
            gameMenuController.setMain(main);  // ส่ง Main ไปยัง controller
            loader.setController(gameMenuController);  // กำหนด controller ด้วยมือ

            Parent root = loader.load();
            menuScene = new Scene(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void show() {
        stage.setScene(menuScene);
        if (gameMenuController != null) {
            gameMenuController.resetMainMenu(); // ✅ เรียกใช้ฟังก์ชัน reset
        }
    }
}
