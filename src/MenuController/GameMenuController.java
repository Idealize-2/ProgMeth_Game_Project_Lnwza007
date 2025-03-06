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
    @FXML private Pane creditsPane;
    @FXML private Button startButton;
    @FXML private Button optionButton;
    @FXML private Button backButton;
    @FXML private Button exitButton;
    @FXML private Slider volumeSlider;
    @FXML private ImageView backgroundImage;
    @FXML private CheckBox cheatActivated;
    @FXML private Button creditsbutton;
    
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
        try {
            backgroundImage.setImage(new Image(getClass().getClassLoader().getResource("images/backgroundMain.png").toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void playBackgroundMusic() {
        if (bgmPlayer != null) {
            bgmPlayer.stop(); 
        }

     
        String musicPath = getClass().getClassLoader().getResource("music/menugame3.mp3").toExternalForm();
        
       
        bgmPlayer = new MediaPlayer(new Media(musicPath));
        bgmPlayer.setCycleCount(MediaPlayer.INDEFINITE); 
        bgmPlayer.setVolume(Main.getCurrentVolume()); 
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
        creditsPane.setVisible(false);
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
    
    @FXML
    private void handleCreditsButtonAction() {
    	creditsPane.setVisible(true);
    	menuPane.setVisible(false);
    	
    }
    
    

}
