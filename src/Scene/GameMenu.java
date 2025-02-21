package Scene;
import Application.Main;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameMenu {
    private Stage stage;
    private Main main;
    private Scene menuScene;

    public GameMenu(Stage stage, Main main) {
        this.stage = stage;
        this.main = main;
        createMenu();
    }

    private void createMenu() {
        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> main.startGame());

        VBox layout = new VBox(20, startButton);
        layout.setStyle("-fx-alignment: center; -fx-background-color: black;");
        menuScene = new Scene(layout, 800, 600);
    }

    public void show() {
        stage.setScene(menuScene);
    }
}
