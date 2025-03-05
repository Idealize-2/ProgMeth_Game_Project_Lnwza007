// Change to your actual package
package MenuController;

 import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.List;

import AnimationEffect.MoneyDisplay;
import Application.Main;
import Item.*;
import Scene.GameScene;
import Weapon.Croissant;
import Weapon.Pizza;
import Weapon.Chocolate;

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
    
    
    @FXML private Button Weapon1;
    @FXML private Button Weapon2;
    @FXML private Button Weapon3;
    @FXML private Button UpWeaponDamage;
    @FXML private Button UpGradeWeaponSpeed;
    @FXML private Button UpGradeWeaponAtkSpeed;
    @FXML private Button UpGradeWeapon;
    @FXML private Button UpLevelWeapon;
    
    @FXML private Button MPotion;
    @FXML private Button BPotion;
    @FXML private Button BerserkPotion;
    @FXML private Button SpecialPotion;
    
    @FXML private ImageView WeaponMenuBackground;
    @FXML private ImageView PotionMenuBackground;
    @FXML private ImageView chocolatePic;
    @FXML private ImageView croissantPic;
    @FXML private ImageView pizzaPic;
    
	private Main main;
	private GameScene gameScene;
	private Canvas canvas;
	private GraphicsContext gc;
	
	static public int shopWeaponSelect;
    

    public void setMain(Main main) {
        this.main = main;
    }
    public void setCanvas(Canvas canvas) {
    	this.canvas = canvas;
    	gc = canvas.getGraphicsContext2D();
    	
    }

    public void initialize() {
    	selectedButtonCorrecting();
    	WeaponMenuBackground.setImage(new Image("/images/weaponMenu.png"));
    	PotionMenuBackground.setImage(new Image("/images/potionMenu.png"));
    	chocolatePic.setImage(new Image("/images/chocolate.png"));
    	croissantPic.setImage(new Image("/images/croissant.png"));
    	pizzaPic.setImage(new Image("/images/pizza.png"));
    	chocolatePic.setMouseTransparent(true);
    	croissantPic.setMouseTransparent(true);
    	pizzaPic.setMouseTransparent(true);

    	

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
	
	
	
	//////////////////////add on
	@FXML
	private void handleWeapon1Click() {
	    System.out.println("Weapon 0 selected");
	    ShopMenuController.shopWeaponSelect = 0;
	    selectedButtonCorrecting();
	}

	@FXML
	private void handleWeapon2Click() {
	    System.out.println("Weapon 1 selected");
	    ShopMenuController.shopWeaponSelect = 1;
	    selectedButtonCorrecting();
	}

	@FXML
	private void handleWeapon3Click() {
	    System.out.println("Weapon 2 selected");
	    ShopMenuController.shopWeaponSelect = 2;
	    selectedButtonCorrecting();
	}

	@FXML
	private void handleUpgradeWeaponDamage() {
	    
	    if(GameScene.playerMoney >= 40 ) 
		{
			GameScene.playerMoney -= 40;
			switch (ShopMenuController.shopWeaponSelect) {
			case 0: {
				Chocolate.setWeaponDamage( Chocolate.getWeaponDamage() + 10);
				System.out.println("Sushi damage upgrade : " + Chocolate.getWeaponDamage());
				break;
			}
			case 1: {
				Croissant.setWeaponDamage( Croissant.getWeaponDamage() + 10);
				System.out.println("Croissant damage upgrade : " + Croissant.getWeaponDamage());
				break;
			}
			case 2: {
				Pizza.setWeaponDamage( Pizza.getWeaponDamage() + 10);
				System.out.println("Pizza damage upgrade : " + Pizza.getWeaponDamage());
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + ShopMenuController.shopWeaponSelect);
			}
			
		}
		else
		{
			System.out.println("Not enought money to buy");
		}
	    renderMoney();
	}

	@FXML
	private void handleUpgradeWeaponSpeed() {
		if(GameScene.playerMoney >= 40 ) 
		{
			GameScene.playerMoney -= 40;
			switch (ShopMenuController.shopWeaponSelect) {
			case 0: {
				Chocolate.setSpeed( Chocolate.getSpeed() + 0.5 );
				System.out.println("Sushi speed upgrade : " + Chocolate.getSpeed());
				break;
			}
			case 1: {
				Croissant.setSpeed( Croissant.getSpeed() + 0.5 );
				System.out.println("Croissant speed upgrade : " + Croissant.getSpeed());
				break;
			}
			case 2: {
				Pizza.setSpeed( Pizza.getSpeed() + 0.5 );
				System.out.println("Pizza speed upgrade : " + Pizza.getSpeed());
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + ShopMenuController.shopWeaponSelect);
			}
			
		}
		else
		{
			System.out.println("Not enought money to buy");
		}
		renderMoney();
	}

	@FXML
	private void handleUpgradeWeaponAtkSpeed() {
		if(GameScene.playerMoney >= 100 ) 
		{
			GameScene.playerMoney -= 100;
			switch (ShopMenuController.shopWeaponSelect) {
			case 0: {
				if(Chocolate.getWeaponCooldown() == 0) {
					System.out.println("Weapon cooldown already reach 0");
					GameScene.playerMoney += 100;
					break;
				}
				Chocolate.setWeaponCooldown( Chocolate.getWeaponCooldown() - 50 );
				System.out.println("Sushi cooldown decease : " + Chocolate.getWeaponCooldown() );
				break;
			}
			case 1: {
				if(Croissant.getWeaponCooldown() == 0) {
					System.out.println("Weapon cooldown already reach 0");
					GameScene.playerMoney += 100;
					break;
				}
				Croissant.setWeaponCooldown( Croissant.getWeaponCooldown() - 50 );
				System.out.println("Croissant cooldown decease : " + Croissant.getWeaponCooldown() );
				break;
			}
			case 2: {
				if(Pizza.getWeaponCooldown() == 0) {
					System.out.println("Weapon cooldown already reach 0");
					GameScene.playerMoney += 100;
					break;
				}
				Pizza.setWeaponCooldown( Pizza.getWeaponCooldown() - 50 );
				System.out.println("Pizza cooldown decease : " + Pizza.getWeaponCooldown() );
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + ShopMenuController.shopWeaponSelect);
			}
			
		}
		else
		{
			System.out.println("Not enought money to buy");
		}
		renderMoney();
	}
	
	@FXML
	private void handleUpLevelWeapon() {
		if(GameScene.playerMoney > 500 ) 
		{
			GameScene.playerMoney -= 500;
			renderMoney();
			switch (ShopMenuController.shopWeaponSelect) {
			case 0: {
				if(Chocolate.getWeaponLevel() == 1) {
					System.out.println("already Level up Sushi");
					GameScene.playerMoney += 500;
					break;
				}
				Chocolate.setWeaponLevel(1);
				System.out.println("Chocolate Level up");
				break;
			}
			case 1: {
				if(Croissant.getWeaponLevel() == 1) {
					System.out.println("already Level up Croissant");
					GameScene.playerMoney += 500;
					break;
				}
				Croissant.setWeaponLevel(1);
				System.out.println("Croissant Level up");
				break;
			}
			case 2: {
				if(Pizza.getWeaponLevel() == 1) {
					System.out.println("already Level up Pizza");
					GameScene.playerMoney += 500;
					break;
				}
				Pizza.setWeaponLevel(1);
				System.out.println("Pizza Level up");
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + ShopMenuController.shopWeaponSelect);
			}
			
		}
		else
		{
			System.out.println("Not enought money to buy");
		}
		renderMoney();
	}
	@FXML 
	private void handleMPotion() {
		if(GameScene.playerMoney >= GameScene.playerInventory.get(0).getPrice() ) 
		{
			GameScene.playerMoney -= GameScene.playerInventory.get(0).getPrice();
			GameScene.playerInventory.get(0).setItemCount( GameScene.playerInventory.get(0).getItemCount() + 1 );
		}
		else
		{
			System.out.println("Not enought money to buy");
		}
		renderMoney();
	}
    @FXML private void handleBPotion() {
    	if(GameScene.playerMoney >= GameScene.playerInventory.get(1).getPrice() ) 
		{
			GameScene.playerMoney -= GameScene.playerInventory.get(1).getPrice();
			GameScene.playerInventory.get(1).setItemCount( GameScene.playerInventory.get(1).getItemCount() + 1 );
		}
		else
		{
			System.out.println("Not enought money to buy");
		}
    	renderMoney();
    }
    @FXML private void handleBerserkPotion() {
    	if(GameScene.playerMoney >= GameScene.playerInventory.get(2).getPrice() ) 
		{
			GameScene.playerMoney -= GameScene.playerInventory.get(2).getPrice();
			GameScene.playerInventory.get(2).setItemCount( GameScene.playerInventory.get(2).getItemCount() + 1 );
		}
		else
		{
			System.out.println("Not enought money to buy");
		}
    	renderMoney();
    }
    @FXML private void handleSpecialPotion() {
    	if(GameScene.playerMoney >= GameScene.playerInventory.get(3).getPrice() ) 
		{
			GameScene.playerMoney -= GameScene.playerInventory.get(3).getPrice();
			renderMoney();
			GameScene.playerInventory.get(3).setItemCount( GameScene.playerInventory.get(3).getItemCount() + 1 );
		}
		else
		{
			System.out.println("Not enought money to buy");
		}
    	renderMoney();
    }
    
    public void renderMoney() {
    	MoneyDisplay.renderMoneyBox(gc,300 , 50,GameScene.playerMoney);
    }
    
   
    public void selectedButtonCorrecting() {
    	String currentStyle;
    	switch(ShopMenuController.shopWeaponSelect) {
    	
    	case 0:
    		currentStyle = Weapon1.getStyle();
        	Weapon1.setStyle(currentStyle + "; -fx-background-color: #32d611;");
        	currentStyle = Weapon2.getStyle();
        	Weapon2.setStyle(currentStyle + "; -fx-background-color: #5cff87;");
        	currentStyle = Weapon3.getStyle();
        	Weapon3.setStyle(currentStyle + "; -fx-background-color: #5cff87;");
    		break;
    	case 1:
    		currentStyle = Weapon1.getStyle();
        	Weapon1.setStyle(currentStyle + "; -fx-background-color: #5cff87;");
        	currentStyle = Weapon2.getStyle();
        	Weapon2.setStyle(currentStyle + "; -fx-background-color: #32d611;");
        	currentStyle = Weapon3.getStyle();
        	Weapon3.setStyle(currentStyle + "; -fx-background-color: #5cff87;");
        	break;
    	case 2:
    		currentStyle = Weapon1.getStyle();
        	Weapon1.setStyle(currentStyle + "; -fx-background-color: #5cff87;");
        	currentStyle = Weapon2.getStyle();
        	Weapon2.setStyle(currentStyle + "; -fx-background-color: #5cff87;");
        	currentStyle = Weapon3.getStyle();
        	Weapon3.setStyle(currentStyle + "; -fx-background-color: #32d611;");
        	break;
        default:
    	}
    }

	
}
