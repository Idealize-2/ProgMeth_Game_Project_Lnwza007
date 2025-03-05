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
import Weapon.Croissant;
import Weapon.Pizza;
import Weapon.Sushi;

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
    
	private Main main;
	private GameScene gameScene;
	
	static public int shopWeaponSelect;
    

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
	
	
	
	//////////////////////add on
	@FXML
	private void handleWeapon1Click() {
	    System.out.println("Weapon 0 selected");
	    // Add logic to purchase or equip Weapon 1
	    ShopMenuController.shopWeaponSelect = 0;
	}

	@FXML
	private void handleWeapon2Click() {
	    System.out.println("Weapon 1 selected");
	    ShopMenuController.shopWeaponSelect = 1;
	    // Add logic to purchase or equip Weapon 2
	}

	@FXML
	private void handleWeapon3Click() {
	    System.out.println("Weapon 2 selected");
	    ShopMenuController.shopWeaponSelect = 2;
	    // Add logic to purchase or equip Weapon 3
	}

	@FXML
	private void handleUpgradeWeaponDamage() {
	    
	    if(GameScene.playerMoney >= 40 ) 
		{
			GameScene.playerMoney -= 40;
			switch (ShopMenuController.shopWeaponSelect) {
			case 0: {
				Sushi.setWeaponDamage( Sushi.getWeaponDamage() + 10);
				System.out.println("Sushi damage upgrade : " + Sushi.getWeaponDamage());
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
	    // Add logic to upgrade weapon damage
	}

	@FXML
	private void handleUpgradeWeaponSpeed() {
		if(GameScene.playerMoney >= 40 ) 
		{
			GameScene.playerMoney -= 40;
			switch (ShopMenuController.shopWeaponSelect) {
			case 0: {
				Sushi.setSpeed( Sushi.getSpeed() + 0.5 );
				System.out.println("Sushi speed upgrade : " + Sushi.getSpeed());
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
	}

	@FXML
	private void handleUpgradeWeaponAtkSpeed() {
		if(GameScene.playerMoney >= 100 ) 
		{
			GameScene.playerMoney -= 100;
			switch (ShopMenuController.shopWeaponSelect) {
			case 0: {
				if(Sushi.getWeaponCooldown() == 0) {
					System.out.println("Weapon cooldown already reach 0");
					GameScene.playerMoney += 100;
					break;
				}
				Sushi.setWeaponCooldown( Sushi.getWeaponCooldown() - 50 );
				System.out.println("Sushi cooldown decease : " + Sushi.getWeaponCooldown() );
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
	}
	
	@FXML
	private void handleUpLevelWeapon() {
		if(GameScene.playerMoney > 500 ) 
		{
			GameScene.playerMoney -= 500;
			switch (ShopMenuController.shopWeaponSelect) {
			case 0: {
				if(Sushi.getWeaponLevel() == 1) {
					System.out.println("already Level up Sushi");
					GameScene.playerMoney += 500;
					break;
				}
				Sushi.setWeaponLevel(1);
				System.out.println("Sushi Level up");
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
    }
    @FXML private void handleSpecialPotion() {
    	if(GameScene.playerMoney >= GameScene.playerInventory.get(3).getPrice() ) 
		{
			GameScene.playerMoney -= GameScene.playerInventory.get(3).getPrice();
			GameScene.playerInventory.get(3).setItemCount( GameScene.playerInventory.get(3).getItemCount() + 1 );
		}
		else
		{
			System.out.println("Not enought money to buy");
		}
    }


	
}
