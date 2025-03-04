package MenuController;

import Application.Main; 
import Scene.GameScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;

public class PauseMenuController {
    
    
    @FXML
    private Rectangle dimBackground;  // เชื่อมโยงกับ Rectangle ใน FXML
    @FXML private Pane pausePane;
    @FXML private Pane optionPane;
    @FXML private Pane gameOverPane;
    @FXML
    private Slider volumeSlider;
    private MediaPlayer gameBgmPlayer;
    
    private Main main;
    @FXML private Button resumeButton;
    @FXML private Button optionButton;
    @FXML private Button exitButton;
    
    private GameScene gameScene;
    
    public void setMain(Main main) {
        this.main = main;
    }

    public void initialize() {
    	setupVolumeControl();
    	
    	
    }
    public void setGameScene(GameScene gameScene) {
    	this.gameScene = gameScene;
    }
    
    @FXML
    private void handleResumeButtonAction(ActionEvent event) {
    	ensurePauseMenuVisible();
    	gameScene.togglePauseForMenu();
    	
    }
    
    @FXML
    private void handleOptionButtonAction(ActionEvent event) {
    	optionPane.setVisible(true);
    	pausePane.setVisible(false);
        
    }
    
    @FXML
    private void handleExitButtonAction(ActionEvent event) {
    	
            System.out.println("Exiting to menu...");
            main.backToMenu();
            gameScene.setRunning(false);
            stopGameBackgroundMusic();
            
        
        
    }
    public void playGameBackgroundMusic() {
        // ระบุเส้นทางของไฟล์เพลง
        String musicPath = "res/music/gameBGM.mp3"; // เปลี่ยนเป็นไฟล์เพลงที่คุณต้องการใช้
        Media media = new Media(new java.io.File(musicPath).toURI().toString());
        gameBgmPlayer = new MediaPlayer(media);
        
        // ตั้งค่าการเล่นเพลง
        gameBgmPlayer.setCycleCount(MediaPlayer.INDEFINITE);  // เล่นซ้ำ
        gameBgmPlayer.setVolume(0.1);  // ปรับระดับเสียงเริ่มต้น
        gameBgmPlayer.play();  // เริ่มเล่นเพลง
    }
    
    private void stopGameBackgroundMusic() {
        if (gameBgmPlayer != null) {
            gameBgmPlayer.stop();  // หยุดการเล่นเพลง
        }
    }
    
    @FXML
    private void handleExitButtonAfterGameOverAction(ActionEvent event) {
    	
        System.out.println("Exiting to menu...");
        gameOverPane.setVisible(false);
        main.backToMenu();
        gameScene.setRunning(false);
        stopGameBackgroundMusic();
    
    
}
    
    @FXML
   private void handleBackButtonAction(ActionEvent event) {
    	optionPane.setVisible(false);
    	pausePane.setVisible(true);
    }
    
    @FXML
    private void handleRestartButtonAction(ActionEvent event) {
    	gameScene.resetGame();
    	gameOverPane.setVisible(false);
    }
    
    private void setupVolumeControl() {
        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
        	 if (gameBgmPlayer != null) {
                 gameBgmPlayer.setVolume(newVal.doubleValue());  // ปรับเสียงตามค่าจาก Slider
             }
        	
        });
    }
    
    public void ensurePauseMenuVisible() {
        optionPane.setVisible(false);
        pausePane.setVisible(true);
    }
    
    public void showGameOver() {
    	pausePane.setVisible(false);
    	optionPane.setVisible(false);
    	gameOverPane.setVisible(true);
    	
    }

}
