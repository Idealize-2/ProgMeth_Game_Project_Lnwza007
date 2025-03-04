// Change to your actual package
package MenuController;

 import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.List;

import Application.Main;
import Item.*;
import Scene.GameScene;

public class ShopMenuController {
	@FXML private StackPane rootPane;
	@FXML private Pane shopWeapon;
	@FXML private Pane shopPotion;
    
    @FXML private Label moneyLabel;
    @FXML private ListView<String> shopListView;
    @FXML private ListView<String> inventoryView;
    
    @FXML private Button backButton;
    @FXML private Button weaponButtonWShop; //WShop = Weapon Shop
    @FXML private Button potionButtonWShop;
    
    @FXML private Button weaponButtonPShop; //PShop = Weapon Shop
    @FXML private Button potionButtonPShop;
    
	private Main main;
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
    private void handleBackButtonAction() {
    	gameScene.togglePauseForOpenShop();
    }

	
	
	 public void ensureWeaponShopVisible() {
		 shopWeapon.setVisible(true);
		 shopPotion.setVisible(false);
	 }
	 
	@FXML
	private void handleWeaponButtonAction() {
		shopWeapon.setVisible(true);
		shopPotion.setVisible(false);
	}
	
	@FXML
	private void handlePotionButtonAction() {
		shopPotion.setVisible(true);
		shopWeapon.setVisible(false);
	}
	
	//<-----------Animation zone------------>
	
	@FXML
	private void handleWeaponEntered() {
	    weaponButtonWShop.setScaleX(1.1); // ขยายขนาดในแนวแกน X
	    weaponButtonWShop.setScaleY(1.1); // ขยายขนาดในแนวแกน Y
	    weaponButtonPShop.setScaleX(1.1); 
	    weaponButtonPShop.setScaleY(1.1); 
	}

	@FXML
	private void handleWeaponExited() {
	    weaponButtonWShop.setScaleX(1.0); // ขนาดปกติในแนวแกน X
	    weaponButtonWShop.setScaleY(1.0); // ขนาดปกติในแนวแกน Y
	    weaponButtonPShop.setScaleX(1.0); 
	    weaponButtonPShop.setScaleY(1.0); 
	}
	
	@FXML
	private void handlePotionEntered() {
	    potionButtonWShop.setScaleX(1.1); // ขยายขนาดในแนวแกน X
	    potionButtonWShop.setScaleY(1.1); // ขยายขนาดในแนวแกน Y
	    potionButtonPShop.setScaleX(1.1); 
	    potionButtonPShop.setScaleY(1.1); 
	}

	@FXML
	private void handlePotionExited() {
		potionButtonWShop.setScaleX(1.0); // ขนาดปกติในแนวแกน X
		potionButtonWShop.setScaleY(1.0); // ขนาดปกติในแนวแกน Y
		potionButtonPShop.setScaleX(1.0); 
		potionButtonPShop.setScaleY(1.0); 
	}

	
}
