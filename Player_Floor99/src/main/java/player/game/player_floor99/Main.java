/*
Main Execution File: Holds Window Information
*/
package player.game.player_floor99;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(getClass().getResource("/player/game/player_floor99/Fonts/BlueScreen.ttf").toExternalForm(),10);
        Parent root = FXMLLoader.load(this.getClass().getResource("TitleScreen.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("PF99Styles.css").toExternalForm());
        stage.setTitle("PLAYER FLOOR 99");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}