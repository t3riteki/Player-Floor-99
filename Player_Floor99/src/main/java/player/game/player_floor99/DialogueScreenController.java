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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import player.game.player_floor99.game_objects.npc.Entity;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DialogueScreenController implements Initializable {
    @FXML
    public Button choice1;
    public Button choice2;
    public Label characterNameLabel;
    public Label characterDialogueLabel;
    public VBox dialogueScreenBackground;
    public ImageView characterImage;
    public Pane dialoguePane;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public StoryHandler story;
    public Image SeolJin = new Image(getClass().getResourceAsStream("Seol-Jin.jpg"));
    public Image Yama = new Image(getClass().getResourceAsStream("Yama.png"));
    public Image Nabi = new Image(getClass().getResourceAsStream("Nabi.jpg"));
    public Image Lancelot = new Image(getClass().getResourceAsStream("Lancelot.jpg"));

    public String c1="", c2="", c3="";
    public boolean disableNextDialogue;
    public int diagVal1, diagVal2;

    public Entity enemy;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        story = new StoryHandler(this);
        characterDialogueLabel.setWrapText(true);
        story.gameInit();
    }


    public void hideChoiceBox(){
        choice1.setVisible(false);
        choice2.setVisible(false);
    }

    public void showChoiceBox(){
        choice1.setVisible(true);
        choice2.setVisible(true);
    }

    public void switchTitleScreen(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TitleScreen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToBattle() throws IOException {
        root = FXMLLoader.load(getClass().getResource("BattleScreen.fxml"));
        stage = (Stage)((Node)dialogueScreenBackground).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updateChoices(String c1, String c2) {
        choice1.setText(c1);
        choice2.setText(c2);
    }

    public void updateCases(String c1, String c2){
        this.c1 = c1;
        this.c2 = c2;
    }

    public void updateCharacterName(String name){
        characterNameLabel.setText(name);
    }

    public void updateCharacterDialogue(String text){
        characterDialogueLabel.setText(text);
    }

    public void updateCharacterImage(String name){
        System.out.println(name);
        switch (name){
            case "Seol-Jin":characterImage.setImage(SeolJin);break;
            case "Yama":characterImage.setImage(Yama);break;
            case "Ryujin":characterImage.setImage(Yama);break;
            case "Nabi":characterImage.setImage(Nabi);break;
            case "Lancelot":characterImage.setImage(Lancelot);break;
        }
    }

    public void choose1(ActionEvent event) throws IOException {
        story.selectPos(c1);
        story.dialIndex+=diagVal1;
        story.checkDialogue(story.Dialogue);
        story.nextDialogue(story.Dialogue);
    }

    public void choose2(ActionEvent event) throws IOException {
        story.selectPos(c2);
        story.dialIndex+=diagVal2;
        story.checkDialogue(story.Dialogue);
        story.nextDialogue(story.Dialogue);
    }

    public void nextDialogue(MouseEvent event){
        if (!disableNextDialogue) {
            updateCharacterName(story.nextSpeaker(story.Dialogue));
            updateCharacterDialogue(story.nextDialogue(story.Dialogue));
            updateCharacterImage(story.nextSpeaker(story.Dialogue));
            story.dialIndex++;
            story.checkDialogue(story.Dialogue);
        }
    }
}

