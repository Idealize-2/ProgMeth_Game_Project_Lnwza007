package MenuController;

import Application.Main; 
import Scene.GameScene;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
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
    @FXML private Pane gameWinPane;
    
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
    
    @FXML private Rectangle yellowBackgroundWin;
    @FXML private ImageView gameClearIcon;
    @FXML private ImageView gameClearImage;
    @FXML private ImageView backToMenuAfterWinPic;
    @FXML private Button backToMenuAfterWin;
    
    
    
    private GameScene gameScene;
    
    public void setMain(Main main) {
        this.main = main;
    }

    public void initialize() {
    	setupVolumeControl();
    	setUpImages();
    	
    }
    
    private void setUpImages() {
    	gameOverIcon.setImage(new Image(getClass().getClassLoader().getResource("images/gameOverIcon.png").toString()));
    	gameClearIcon.setImage(new Image(getClass().getClassLoader().getResource("images/gameClearIcon.png").toString()));
    	gameClearImage.setImage(new Image(getClass().getClassLoader().getResource("images/gameClearImage.png").toString()));
    	backToMenuAfterWinPic.setImage(new Image(getClass().getClassLoader().getResource("images/backToMenuIconEnd.png").toString()));
    	backToMenuAfterWinPic.setMouseTransparent(true);
		
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
    	volumeSlider.setValue(Main.getCurrentVolume());
    	optionPane.setVisible(true);
    	pausePane.setVisible(false);
    	
        
    }
    
    @FXML
    private void handleExitButtonAction(ActionEvent event) {
    	
            System.out.println("Exiting to menu...");
            gameOverPane.setVisible(false);
            gameWinPane.setVisible(false);
            main.backToMenu();
            gameScene.setRunning(false);
            stopGameBackgroundMusic();
            
        
        
    }
    public void playGameBackgroundMusic() {
        String musicPath = getClass().getClassLoader().getResource("music/gameBGM.mp3").toString();
        
        Media media = new Media(musicPath);
        gameBgmPlayer = new MediaPlayer(media);

        gameBgmPlayer.setCycleCount(MediaPlayer.INDEFINITE);  
        gameBgmPlayer.setVolume(Main.getCurrentVolume());  
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
    public void showWinScene() {
    	pausePane.setVisible(false);
    	optionPane.setVisible(false);
    	gameOverPane.setVisible(false);
    	gameWinPane.setVisible(true);
    	
    	yellowBackgroundWin.setOpacity(0);
        gameClearIcon.setOpacity(0);
        gameClearImage.setOpacity(0);
        backToMenuAfterWinPic.setOpacity(0);

        // Fade in yellowBackgroundWin
        FadeTransition fadeYellow = new FadeTransition(Duration.seconds(1), yellowBackgroundWin);
        fadeYellow.setFromValue(0);
        fadeYellow.setToValue(1);

        // Fade in gameClearIcon
        FadeTransition fadeIcon = new FadeTransition(Duration.seconds(0.5), gameClearIcon);
        fadeIcon.setFromValue(0);
        fadeIcon.setToValue(1);

        // Fade in gameClearImage
        FadeTransition fadeImage = new FadeTransition(Duration.seconds(0.5), gameClearImage);
        fadeImage.setFromValue(0);
        fadeImage.setToValue(1);

        // Fade in backToMenuAfterWinPic
        FadeTransition fadeMenuPic = new FadeTransition(Duration.seconds(0.5), backToMenuAfterWinPic);
        fadeMenuPic.setFromValue(0);
        fadeMenuPic.setToValue(1);

        // Run transitions sequentially
        SequentialTransition sequence = new SequentialTransition(fadeYellow, fadeIcon, fadeImage, fadeMenuPic);
        sequence.play();
    	
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
