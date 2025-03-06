// Change to your actual package
package MenuController;

 import javafx.animation.FadeTransition;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    @FXML private ImageView PotionBackground;
    @FXML private ImageView chocolatePic;
    @FXML private ImageView croissantPic;
    @FXML private ImageView pizzaPic;
    
    @FXML private ImageView MPotionIcon;
    @FXML private ImageView BPotionIcon;
    @FXML private ImageView BerserkPotionIcon;
    @FXML private ImageView SpecialPotionIcon;
    
    
    @FXML private Text MPotionLeft;
    @FXML private Text BPotionLeft;
    @FXML private Text BerserkPotionLeft;
    @FXML private Text SpecialPotionLeft;
    @FXML private Text moneyLeft1;
    @FXML private Text moneyLeft2;
    @FXML private Text statusTextWeapon;
    @FXML private Text statusTextPotion;
    
    
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
    	potionLeftCorrecting();
    	WeaponMenuBackground.setImage(new Image("/images/weaponMenu.png"));
    	PotionMenuBackground.setImage(new Image("/images/potionMenu.png"));
    	chocolatePic.setImage(new Image("/images/chocolate.png"));
    	croissantPic.setImage(new Image("/images/croissant.png"));
    	pizzaPic.setImage(new Image("/images/pizza.png"));
    	PotionBackground.setImage(new Image("/images/potion3.png"));
    	MPotionIcon.setImage(new Image("/images/potion1.png"));
    	BPotionIcon.setImage(new Image("/images/potion2.png"));
    	BerserkPotionIcon.setImage(new Image("/images/potion3.png"));
    	SpecialPotionIcon.setImage(new Image("/images/potion4.png"));
    	
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
				System.out.println("Chocolate damage upgrade : " + Chocolate.getWeaponDamage());
				showStatusText("Chocolate damage increased to "+Chocolate.getWeaponDamage(),"green");
				break;
			}
			case 1: {
				Croissant.setWeaponDamage( Croissant.getWeaponDamage() + 10);
				System.out.println("Croissant damage upgrade : " + Croissant.getWeaponDamage());
				showStatusText("Croissant damage increased to "+Croissant.getWeaponDamage(),"green");
				break;
			}
			case 2: {
				Pizza.setWeaponDamage( Pizza.getWeaponDamage() + 10);
				System.out.println("Pizza damage upgrade : " + Pizza.getWeaponDamage());
				showStatusText("Pizza damage increased to "+Pizza.getWeaponDamage(),"green");
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + ShopMenuController.shopWeaponSelect);
			}
			
		}
		else
		{
			System.out.println("Not enought money to buy");
			showStatusText("Not Enough Money!","red");
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
				System.out.println("Chocolate speed upgrade : " + Chocolate.getSpeed());
				showStatusText("Chocolate speed increased to "+Chocolate.getSpeed(),"green");
				break;
			}
			case 1: {
				Croissant.setSpeed( Croissant.getSpeed() + 0.5 );
				System.out.println("Croissant speed upgrade : " + Croissant.getSpeed());
				showStatusText("Chocolate Speed increased to "+Croissant.getSpeed(),"green");
				break;
			}
			case 2: {
				Pizza.setSpeed( Pizza.getSpeed() + 0.5 );
				System.out.println("Pizza speed upgrade : " + Pizza.getSpeed());
				showStatusText("Pizza Speed increased to "+Pizza.getSpeed(),"green");
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + ShopMenuController.shopWeaponSelect);
			}
			
		}
		else
		{
			System.out.println("Not enought money to buy");
			showStatusText("Not Enough Money!","red");
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
					showStatusText("Chocolate ATK Cooldown Max! ","red");
					GameScene.playerMoney += 100;
					break;
				}
				Chocolate.setWeaponCooldown( Chocolate.getWeaponCooldown() - 50 );
				System.out.println("Chocolate cooldown decease : " + Chocolate.getWeaponCooldown() );
				showStatusText("Chocolate Cooldown Decreased to "+ Chocolate.getWeaponCooldown(),"green");
				break;
			}
			case 1: {
				if(Croissant.getWeaponCooldown() == 0) {
					System.out.println("Weapon cooldown already reach 0");
					showStatusText("Croissant ATK Cooldown Max! ","red");
					GameScene.playerMoney += 100;
					break;
				}
				Croissant.setWeaponCooldown( Croissant.getWeaponCooldown() - 50 );
				System.out.println("Croissant cooldown decease : " + Croissant.getWeaponCooldown() );
				showStatusText("Croissant Cooldown Decreased to "+ Croissant.getWeaponCooldown(),"green");
				break;
			}
			case 2: {
				if(Pizza.getWeaponCooldown() == 0) {
					System.out.println("Weapon cooldown already reach 0");
					showStatusText("Pizza ATK Cooldown Max! ","red");
					GameScene.playerMoney += 100;
					break;
				}
				Pizza.setWeaponCooldown( Pizza.getWeaponCooldown() - 50 );
				System.out.println("Pizza cooldown decreased : " + Pizza.getWeaponCooldown() );
				showStatusText("Pizza Cooldown Decreased to "+ Pizza.getWeaponCooldown(),"green");
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + ShopMenuController.shopWeaponSelect);
			}
			
		}
		else
		{
			System.out.println("Not enought money to buy");
			showStatusText("Not Enough Money!","red");
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
					System.out.println("already Level up Chocolate");
					showStatusText("Already upgraded!","red");
					GameScene.playerMoney += 500;
					break;
				}
				Chocolate.setWeaponLevel(1);
				System.out.println("Chocolate Level up");
				showStatusText("Chocolate Upgraded!","green");
				break;
			}
			case 1: {
				if(Croissant.getWeaponLevel() == 1) {
					System.out.println("already Level up Croissant");
					showStatusText("Already upgraded!","red");
					GameScene.playerMoney += 500;
					break;
				}
				Croissant.setWeaponLevel(1);
				System.out.println("Croissant Level up");
				showStatusText("Croissant Upgraded!","green");
				break;
			}
			case 2: {
				if(Pizza.getWeaponLevel() == 1) {
					System.out.println("already Level up Pizza");
					showStatusText("Already upgraded!","red");
					GameScene.playerMoney += 500;
					break;
				}
				Pizza.setWeaponLevel(1);
				System.out.println("Pizza Level up");
				showStatusText("Pizza Upgraded!","green");
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + ShopMenuController.shopWeaponSelect);
			}
			
		}
		else
		{
			System.out.println("Not enought money to buy");
			showStatusText("Not Enough Money!","red");
		}
		renderMoney();
	}
	@FXML 
	private void handleMPotion() {
		if(GameScene.playerMoney >= GameScene.playerInventory.get(0).getPrice() ) 
		{
			GameScene.playerMoney -= GameScene.playerInventory.get(0).getPrice();
			GameScene.playerInventory.get(0).setItemCount( GameScene.playerInventory.get(0).getItemCount() + 1 );
			showStatusText("Buy Success!","green");
		}
		else
		{
			System.out.println("Not enought money to buy");
			showStatusText("Not Enough Money!","red");
		}
		renderMoney();
		potionLeftCorrecting();
	}
    @FXML private void handleBPotion() {
    	if(GameScene.playerMoney >= GameScene.playerInventory.get(1).getPrice() ) 
		{
			GameScene.playerMoney -= GameScene.playerInventory.get(1).getPrice();
			GameScene.playerInventory.get(1).setItemCount( GameScene.playerInventory.get(1).getItemCount() + 1 );
			showStatusText("Buy Success!","green");
		}
		else
		{
			System.out.println("Not enought money to buy");
			showStatusText("Not Enough Money!","red");
			
		}
    	renderMoney();
    	potionLeftCorrecting();
    }
    @FXML private void handleBerserkPotion() {
    	if(GameScene.playerMoney >= GameScene.playerInventory.get(2).getPrice() ) 
		{
			GameScene.playerMoney -= GameScene.playerInventory.get(2).getPrice();
			GameScene.playerInventory.get(2).setItemCount( GameScene.playerInventory.get(2).getItemCount() + 1 );
			showStatusText("Buy Success!","green");
		}
		else
		{
			System.out.println("Not enought money to buy");
			showStatusText("Not Enough Money!","red");
		}
    	renderMoney();
    	potionLeftCorrecting();
    }
    @FXML private void handleSpecialPotion() {
    	if(GameScene.playerMoney >= GameScene.playerInventory.get(3).getPrice() ) 
		{
			GameScene.playerMoney -= GameScene.playerInventory.get(3).getPrice();
			renderMoney();
			GameScene.playerInventory.get(3).setItemCount( GameScene.playerInventory.get(3).getItemCount() + 1 );
			showStatusText("Buy Success!","green");
		}
		else
		{
			System.out.println("Not enought money to buy");
			showStatusText("Not Enough Money!","red");
		}
    	renderMoney();
    	potionLeftCorrecting();
    }
    
    public void renderMoney() {
    	moneyLeft1.setText("MONEY : $"+GameScene.playerMoney);
    	moneyLeft2.setText("MONEY : $"+GameScene.playerMoney);
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
    
    public void potionLeftCorrecting() {
    	MPotionLeft.setText("MPotion : "+GameScene.playerInventory.get(0).getItemCount());
    	BPotionLeft.setText("BPotion : "+GameScene.playerInventory.get(1).getItemCount());
    	BerserkPotionLeft.setText("BerserkPotion : "+GameScene.playerInventory.get(2).getItemCount());
    	SpecialPotionLeft.setText("SpecialPotion : "+GameScene.playerInventory.get(3).getItemCount());
    	
    	
    }
    
    private void showStatusText(String respond,String colorName) {
    	if(shopWeapon.isVisible()) {
    		statusTextWeapon.setText(respond);
        	if(colorName.equals("red")) statusTextWeapon.setFill(Color.RED);
        	else if(colorName.equals("green")) statusTextWeapon.setFill(Color.GREEN);
        	 FadeTransition fade = new FadeTransition(Duration.seconds(2), statusTextWeapon);
        	    fade.setFromValue(1.0);  
        	    fade.setToValue(0.0);
        	    fade.setDelay(Duration.seconds(0));
        	    fade.play();
    		
    	}
    	else if(shopPotion.isVisible()) {
    		statusTextPotion.setText(respond);
        	if(colorName.equals("red")) statusTextPotion.setFill(Color.RED);
        	else if(colorName.equals("green")) statusTextPotion.setFill(Color.GREEN);
        	 FadeTransition fade = new FadeTransition(Duration.seconds(2), statusTextPotion);
        	    fade.setFromValue(1.0);  
        	    fade.setToValue(0.0);
        	    fade.setDelay(Duration.seconds(0));
        	    fade.play();
    		
    	}
    	
    	
    }

	
}
