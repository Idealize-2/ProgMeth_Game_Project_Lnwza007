package MenuController;

import Application.Main;
import Scene.GameScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class PauseMenuController {
    
    
    @FXML
    private Rectangle dimBackground;  // เชื่อมโยงกับ Rectangle ใน FXML
    @FXML private Pane pausePane;
    @FXML private Pane optionPane;
    @FXML private Pane gameOverPane;
    @FXML private Slider volumeSlider;
    
    private Main main;
    @FXML private Button resumeButton;
    @FXML private Button optionButton;
    @FXML private Button exitButton;
    
    private GameScene gameScene;
    
    public void setMain(Main main) {
        this.main = main;
    }

    public void initialize() {
    	
    	
    }
    public void setGameScene(GameScene gameScene) {
    	this.gameScene = gameScene;
    }
    
    @FXML
    private void handleResumeButtonAction(ActionEvent event) {
    	if(optionPane.isVisible()) {
    		optionPane.setVisible(false);
    		pausePane.setVisible(true);
    	}
    	gameScene.togglePause();
    	
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
        
        
    }
    
    @FXML
    private void handleExitButtonAfterGameOverAction(ActionEvent event) {
    	
        System.out.println("Exiting to menu...");
        gameOverPane.setVisible(false);
        main.backToMenu();
        gameScene.setRunning(false);
    
    
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
            //Dummy code รอใส่เพลงในตัวเกม
        	
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
