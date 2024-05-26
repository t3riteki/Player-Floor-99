package player.game.player_floor99;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class BattleScreenController implements Initializable {
    public VBox background;

    public Label enmyNameLabel;
    public Label enmyManaLabel;
    public Label enmyHPLabel;
    public Label protagNameLabel;
    public Label protagManaLabel;
    public Label protagHPLabel;
    public Label btlDiagLabel;

    public Button defButton;
    public Button sklButton;
    public Button fleeButton;
    public Button atkButton;

    public ImageView enmyImageView;
    public GridPane actionBar;
    public GridPane enmyStatus;
    public GridPane protagStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btlDiagLabel.setWrapText(true);
    }
}
