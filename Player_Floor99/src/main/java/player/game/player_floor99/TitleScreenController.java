package player.game.player_floor99;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TitleScreenController implements Initializable {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    private DialogueScreenController DSC;

    public GridPane titleScreenBackground;

    public ImageView titleImageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void switchDialogueScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DialogueScreen.fxml"));
        root = loader.load();
        DSC = loader.getController();

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void exitProgram(ActionEvent e){
        stage = (Stage) titleScreenBackground.getScene().getWindow();
        stage.close();

    }
}