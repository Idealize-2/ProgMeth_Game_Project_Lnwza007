// Change to your actual package
package MenuController;

 import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.util.List;

import Item.*;

public class ShopController {
    
    @FXML private Label moneyLabel;
    @FXML private ListView<String> shopListView;
    @FXML private ListView<String> inventoryView;
    
    private int playerMoney;
    private List<Item> shopItems;
    private List<Item> playerInventory;

    // Initialize the shop with player data
    public void initData(int money, List<Item> items, List<Item> playerInventory2) {
        playerMoney = money;
        shopItems = items;
        playerInventory = playerInventory2;

        moneyLabel.setText("Your Money: $" + playerMoney);
        shopListView.getItems().clear();
        for (Item item : shopItems) {
            shopListView.getItems().add(item.getName() + " - $" + item.getPrice());
        }
        //inventoryView.getItems().addAll(playerInventory);
    }

    @FXML
    private void handleBuy() {
        int selectedIndex = shopListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Item selectedItem = shopItems.get(selectedIndex);
            if (playerMoney >= selectedItem.getPrice() ) {
                playerMoney -= selectedItem.getPrice();
               //playerInventory.addAll(selectedItem.getName());
                inventoryView.getItems().add(selectedItem.getName());
                moneyLabel.setText("Your Money: $" + playerMoney);
            } else {
                moneyLabel.setText("Not enough money!");
            }
        }
    }

    @FXML
    private void handleClose() {
        Stage stage = (Stage) moneyLabel.getScene().getWindow();
        stage.close();
    }
}
