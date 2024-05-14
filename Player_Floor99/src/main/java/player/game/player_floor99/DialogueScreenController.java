/*
Holds methods for choices
*/

package player.game.player_floor99;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DialogueScreenController implements Initializable{
    @FXML
    public Button choice1;
    public Button choice2;
    public Button choice3;
    public Label characterNameLabel;
    public Label characterDialogueLabel;
    public GridPane choiceBox;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private StoryHandler story;
    String c1="", c2="", c3="";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        story = new StoryHandler(this);
    }


    public void startGame() throws IOException {
        story.testcase();
    }

    public void hideChoiceBox(){
        choiceBox.setVisible(false);
    }
    public void showChoiceBox(){
        choiceBox.setVisible(true);
    }

    public void switchTitleScreen(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TitleScreen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updateChoices(String c1, String c2, String c3) {
        choice1.setText(c1);
        choice2.setText(c2);
        choice3.setText(c3);
    }

    public void updateCases(String c1, String c2, String c3){
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
    }

    public void updateCharacterName(String name){
        characterNameLabel.setText(name);
    }

    public void updateCharacterDialogue(String text){
        characterDialogueLabel.setText(text);
    }

    public void choose1(ActionEvent event){
        story.selectPos(c1);
        story.EndingValue+=5;
    }

    public void choose2(ActionEvent event){
        story.selectPos(c2);
    }

    public void choose3(ActionEvent event){
        story.selectPos(c3);
        story.EndingValue-=5;
    }


}

