package Scene;
import Application.Main;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class GameMenuController {
    private Main main;

    @FXML
    private Button startButton;

    public GameMenuController() {
        // คอนสตรัคเตอร์ที่ไม่มีพารามิเตอร์
    }

    // Setter method สำหรับรับค่า Main
    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    public void initialize() {
        animateStartButton(); // เรียกใช้งาน Animation ตอนเริ่ม
    }

    private void animateStartButton() {
        // ตั้งค่าให้ปุ่มเริ่มต้นอยู่นอกจอ (แกน Y)
        startButton.setTranslateY(-100);

        // สร้าง Animation
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), startButton);
        transition.setFromY(-100); // เริ่มจากตำแหน่งด้านบน
        transition.setToY(0); // เคลื่อนที่ลงมาจบที่ตำแหน่งเดิม
        transition.play(); // เล่น Animation
    }

    @FXML
    private void handleStartButtonAction(ActionEvent event) {
        if (main != null) {
            main.startGame();
        }
    }

    @FXML
    private void handleExitButtonAction(ActionEvent event) {
        Platform.exit();
    }
}
