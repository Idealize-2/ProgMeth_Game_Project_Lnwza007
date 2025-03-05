package MenuController;



import Application.Main;
import Scene.GameScene;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GameSceneController {
	@FXML private Pane stagePane;
	
	private Main main;
	private GameScene gameScene;
	private Canvas canvas;
	private GraphicsContext gc;
	@FXML private Text monsterLeft;
	private int enemiesleft;
	
	@FXML private ImageView stage1;
	@FXML private ImageView stage2;
	@FXML private ImageView stage3;
	@FXML private ImageView stage4;
	@FXML private ImageView stage5;
	@FXML private Text stageNo;
	
	
	public void initialize() {
		stage1.setImage(new Image("/images/stage1.png"));
		stage2.setImage(new Image("/images/stage2.png"));
		stage3.setImage(new Image("/images/stage3.png"));
		stage4.setImage(new Image("/images/stage4.png"));
		stage5.setImage(new Image("/images/stage5.png"));
		
		

		
	}
	public void setMain(Main main) {
        this.main = main;
    }
    public void setCanvas(Canvas canvas) {
    	this.canvas = canvas;
    	gc = canvas.getGraphicsContext2D();
    	
    }
    public void setGameScene(GameScene gameScene) {
    	this.gameScene = gameScene;
    }
    
    public void showStage1() {
    	FadeTransition fadeIn = new FadeTransition(Duration.seconds(5), stage1);
        fadeIn.setToValue(1); 

        fadeIn.setOnFinished(event -> {
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(5), stage1);
            fadeOut.setToValue(0); 
            fadeOut.play();
        });

        fadeIn.play();
    	
    }
    
    public void showStage2() {
    	FadeTransition fadeIn = new FadeTransition(Duration.seconds(5), stage2);
        fadeIn.setToValue(1); 

        fadeIn.setOnFinished(event -> {
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(5), stage2);
            fadeOut.setToValue(0); 
            fadeOut.play();
        });

        fadeIn.play();
    }
    
    public void setStageNo(int stageNum) {
    	stageNo.setText("STAGE "+stageNum);
    }
    public void setMonsterLeft(int enemy) {
    	enemiesleft = enemy;
    	monsterLeft.setText("Monster Left : "+enemiesleft);
    }
    
    
}
