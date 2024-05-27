package player.game.player_floor99;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ResourceBundle;

public class BattleScreenController implements Initializable {
    @FXML
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

    BattleHandler battle;

    String c1, c2, c3, c4, c5;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        battle = new BattleHandler(this);

        btlDiagLabel.setWrapText(true);
        battle.battleInit();
    }

    public void Attack(ActionEvent event){
        battle.selectAction(c1);
    }

    public void Defend(ActionEvent event){
        battle.selectAction(c2);
    }

    public void Skill(ActionEvent event){
        battle.selectAction(c3);
    }

    public void Flee(ActionEvent event){
        battle.selectAction(c4);
    }

    public void nextTurn (MouseEvent event){
        battle.selectAction(c5);
    }

    public void updateBattleDialogue(String battleText){
        btlDiagLabel.setText(battleText);
    }

    public void updatePlayerStatus (int HP, int Mana){
       protagNameLabel.setText(battle.user.name);
       protagHPLabel.setText("HP: " + HP);
       protagManaLabel.setText("Mana: " + Mana);
    }

    public void updateCases(String c1, String c2, String c3, String c4, String c5){
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
        this.c5 = c5;
    }

    public void updateEnemyStatus(int HP, int Mana){
       enmyNameLabel.setText(battle.enemy.name);
       enmyHPLabel.setText("HP: " + HP);
       enmyManaLabel.setText("Mana: " + Mana);

    }
}
