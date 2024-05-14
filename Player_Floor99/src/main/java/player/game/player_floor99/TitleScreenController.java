package player.game.player_floor99;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TitleScreenController {
    public GridPane background;
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    private DialogueScreenController DSC;

    public void switchDialogueScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DialogueScreen.fxml"));
        root = loader.load();
        DSC = loader.getController();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        DSC.startGame();
    }

    public void exitProgram(ActionEvent e){
        stage = (Stage) background.getScene().getWindow();
        stage.close();

    }
}