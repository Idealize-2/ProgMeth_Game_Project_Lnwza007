package MenuController;

import Application.Main;
import Scene.GameScene;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class GameMenuController {
    private Main main;

    @FXML private Pane menuPane; // เมนูหลัก
    @FXML private Pane optionPane; // เมนูปรับเสียง
    @FXML private Button startButton;
    @FXML private Button optionButton;
    @FXML private Button backButton;
    @FXML private Button exitButton;
    @FXML private Slider volumeSlider;
    @FXML private ImageView backgroundImage;
    @FXML private CheckBox cheatActivated;
    
    private MediaPlayer mediaPlayer;
    private MediaPlayer bgmPlayer;
    
    

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    public void initialize() {
    	
        playBackgroundMusic();
        setupVolumeControl();
        setCheckBoxCheat();
        setBackgroundImage();
       
        
        
    }

    private void setBackgroundImage() {
    	backgroundImage.setImage(new Image("/images/backgroundMain.png"));
		
	}

//    private void playBackgroundVideo() {
//        String videoPath = "res/video/demobackgroundvideo.mp4";
//        mediaPlayer = new MediaPlayer(new javafx.scene.media.Media(new java.io.File(videoPath).toURI().toString()));
//        mediaView.setMediaPlayer(mediaPlayer);
//        mediaView.setFitHeight(600);
//        mediaView.setFitWidth(800);
//        mediaView.setPreserveRatio(false);
//        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); //คลิปวนลูป
//        mediaPlayer.setMute(true); //ไม่เอาเสียงวิดีโอ
//        mediaPlayer.play();
//        
//    }
    
    private void playBackgroundMusic() {
        // ถ้ามี MediaPlayer เก่าที่ยังเล่นอยู่ ให้หยุดมันก่อน
        if (bgmPlayer != null) {
            bgmPlayer.stop();  // หยุดการเล่นเพลงเก่า
        }

        // สร้าง MediaPlayer ใหม่และเล่นเพลง
        String musicPath = "res/music/menugame3.mp3";
        bgmPlayer = new MediaPlayer(new Media(new java.io.File(musicPath).toURI().toString()));
        bgmPlayer.setCycleCount(MediaPlayer.INDEFINITE);  // เล่นซ้ำ
        bgmPlayer.setVolume(main.getCurrentVolume());  // ตั้งค่าเริ่มต้น
        bgmPlayer.play();
    }
    

    private void setupVolumeControl() {
        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (bgmPlayer != null) {
                bgmPlayer.setVolume(newVal.doubleValue());
            }
            main.setCurrentVolume(newVal.doubleValue());
        });
    }

    @FXML
    private void handleStartButtonAction(ActionEvent event) {
        if (main != null) {
        	stopAllMedia();
            main.startGame();
        }
    }

    @FXML
    private void handleOptionButtonAction(ActionEvent event) {
    	volumeSlider.setValue(main.getCurrentVolume());
        menuPane.setVisible(false);
        optionPane.setVisible(true);
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        optionPane.setVisible(false);
        menuPane.setVisible(true);
    }

    @FXML
    private void handleExitButtonAction(ActionEvent event) {
        Platform.exit();
    }
    
    public void resetMainMenu() { //call when go back to main menu
        //playBackgroundVideo(); // เล่นวิดีโอพื้นหลังใหม่
        playBackgroundMusic(); // เล่นเพลงเมนูใหม่
        menuPane.setVisible(true);
        optionPane.setVisible(false);
    }

    private void stopAllMedia() {//call when change scene to other song
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        if (bgmPlayer != null) {
            bgmPlayer.stop();
        }
    }
    
    @FXML
    private void handleStartGameButtonEntered() {
    	String currentStyle = startButton.getStyle();
    	startButton.setStyle(currentStyle + "; -fx-background-color: #ffd375;");
    }
    
    @FXML
    private void handleStartGameButtonExited() {
    	String currentStyle = startButton.getStyle();
    	startButton.setStyle(currentStyle + "; -fx-background-color: orange;");
    }
    
    @FXML
    private void handleOptionButtonEntered() {
    	String currentStyle = optionButton.getStyle();
    	optionButton.setStyle(currentStyle + "; -fx-background-color: #ffd375;");	
    }
    
    @FXML
    private void handleOptionButtonExited() {
    	String currentStyle = optionButton.getStyle();
    	optionButton.setStyle(currentStyle + "; -fx-background-color: orange;");
    }
    
    @FXML
    private void handleExitButtonEntered() { //not good รอแก้ บักตรงมุม
    	String currentStyle = exitButton.getStyle();
    	exitButton.setStyle(currentStyle + "; -fx-background-color: #fa7c5a;");


    }
    
    @FXML
    private void handleExitButtonExited() {
    	String currentStyle = exitButton.getStyle();
    	exitButton.setStyle(currentStyle + "; -fx-background-color: orange;");
    }
    
    private void setCheckBoxCheat() {
    	cheatActivated.setOnAction(e -> {
    	    if (cheatActivated.isSelected()) {
    	        GameScene.setCheat(true);
    	    } else {
    	    	GameScene.setCheat(false);
    	    }
    	});
    }
    
    

}
