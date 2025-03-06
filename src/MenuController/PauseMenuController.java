package MenuController;

import Application.Main; 
import Scene.GameScene;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

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
    
    @FXML private Rectangle fadeBackground;
    @FXML private Button restartButton;
    @FXML private Button backToMenuButton;
    @FXML private ImageView gameOverIcon;
    
    private GameScene gameScene;
    
    public void setMain(Main main) {
        this.main = main;
    }

    public void initialize() {
    	
    	setupVolumeControl();
    	gameOverIcon.setImage(new Image("/images/gameOverIcon.png"));
    	
    	
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
    	volumeSlider.setValue(main.getCurrentVolume());
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

        String musicPath = "res/music/gameBGM.mp3"; 
        Media media = new Media(new java.io.File(musicPath).toURI().toString());
        gameBgmPlayer = new MediaPlayer(media);

        gameBgmPlayer.setCycleCount(MediaPlayer.INDEFINITE);  
        gameBgmPlayer.setVolume(main.getCurrentVolume());  
        gameBgmPlayer.play();
    }

    private void stopGameBackgroundMusic() {
        if (gameBgmPlayer != null) {
            gameBgmPlayer.stop();
        }
    }

    
    @FXML //ปุ่ม backtomenu
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
    	stopGameBackgroundMusic();

        // รีสตาร์ทเกม
        gameScene.resetGame();
        gameOverPane.setVisible(false);

        
    }
    
    private void setupVolumeControl() {
        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
        	 if (gameBgmPlayer != null) {
                 gameBgmPlayer.setVolume(newVal.doubleValue());  // ปรับเสียงตามค่าจาก Slider
             }
        	 main.setCurrentVolume(newVal.doubleValue());
        	
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
    
    public void fadeToBlackAndShowGameOver() {
    	showGameOver();
        // เริ่มจากซ่อนปุ่มและข้อความก่อน
        restartButton.setOpacity(0);
        backToMenuButton.setOpacity(0);
        gameOverIcon.setOpacity(0);
        
        fadeBackground.setOpacity(0); // เริ่มต้นให้โปร่งใส
        fadeBackground.setVisible(true); // แสดง Rectangle

        // เอฟเฟกต์จอมืดลง
        FadeTransition fadeToBlack = new FadeTransition(Duration.seconds(2), fadeBackground);
        fadeToBlack.setToValue(1); // ค่อยๆ มืดลง

        // หลังจากมืดสนิทแล้ว ค่อยทำให้ปุ่มกับข้อความแสดงขึ้น
        fadeToBlack.setOnFinished(event -> {
            FadeTransition fadeButtons = new FadeTransition(Duration.seconds(1), restartButton);
            fadeButtons.setToValue(1);
            
            FadeTransition fadeMenuButton = new FadeTransition(Duration.seconds(1), backToMenuButton);
            fadeMenuButton.setToValue(1);
            
            FadeTransition fadeText = new FadeTransition(Duration.seconds(1), gameOverIcon);
            fadeText.setToValue(1);
            
            fadeButtons.play();
            fadeMenuButton.play();
            fadeText.play();
        });

        fadeToBlack.play(); // เริ่มเอฟเฟกต์จอมืด
    }

    
    

}
