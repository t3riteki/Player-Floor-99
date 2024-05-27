package player.game.player_floor99;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import player.game.player_floor99.game_objects.npc.Player;
import player.game.player_floor99.game_objects.npc.Entity;
import player.game.player_floor99.game_objects.npc.Seol_Jin_npc;

import java.io.IOException;

public class StoryHandler {
    private DialogueScreenController DSC;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public StoryHandler(DialogueScreenController dController) {
        this.DSC = dController;
    }

    Player user = new Player();
    public Entity enemy;// = Seol jin

    String currentSpeaker,currentDialogue;
    String[][] Dialogue;

    int cnIndex = 0, cdIndex = 1, dialIndex = 0, dialogueSize;

    /*-----------------Initializing Methods-------------------*/

    public void selectPos(String choice) throws IOException {
        switch (choice) {
            case "Battle":Battle();break;
            case "ch1":chapter1();break;
            case "ch1_1":chapter1_1();break;
            case "ch1A":chapter1A();break;
            case "ch1B":chapter1B();break;
            case "ch2":chapter2();break;
        }
    }

    public void Battle() throws IOException {
        DSC.switchToBattle();
    }

    public void gameInit(){
        user.name = "Yama";
        chapter1();
    }

    public String nextSpeaker(String[][] dialogue){
        currentSpeaker = dialogue[dialIndex][cnIndex];
        return currentSpeaker;
    }

    public String nextDialogue(String[][] dialogue){
        currentDialogue = dialogue[dialIndex][cdIndex];
        checkDialogue(Dialogue);
        return currentDialogue;
    }

    public void checkDialogue(String[][] array){
        dialogueSize = array.length;
        if (dialIndex == dialogueSize){
            DSC.showChoiceBox();
        }

        if (dialIndex > dialogueSize-1) {
            cnIndex = 0;
            cdIndex = 1;
            dialIndex = 0;

            DSC.disableNextDialogue = true;
        }
    }

    public void initStoryNode(){
        DSC.disableNextDialogue = false;
        DSC.updateCharacterName(Dialogue[dialIndex][cnIndex]);
        DSC.updateCharacterDialogue(Dialogue[dialIndex][cdIndex]);
        DSC.hideChoiceBox();
        DSC.diagVal1 = 1;
        DSC.diagVal2 = 1;

        dialIndex++;
    }

    /*-----------------Story Methods-------------------*/

    public void chapter1() {
        Dialogue = new String[][]{
                {"Narrator", "As everyone questions Seol-Jin's worthiness, the battle for the players' representative has begun!"},
                {"Narrator", "Watching the spectacle of a fight between a boy chosen by the Tower's God and a woman hailed for strength and malice..."},
                {"Narrator", "The crowd roars as the fight abruptly ends... in a conversation?"},
                {"Mysterious Person", "It's getting rowdy..."},
                {"Nabi","I've taken a liking to you"},
                {"Seol-Jin","!"},
                {"Nabi","I'll be counting on you Seol-jin. Flatten Arthur's nose for me!"},
                {"Narrator", "As Nabi declares her defeat and respect, she places her trust in Seol-jin - the Small Guild Union representative, Ryujin, also declared his support for him..."},
                {"Ryujin", "Alright you damn cowards, has the boy proven himself enough?"},
                {"Narrator", "But a lingering unease, can prove to be a detriment in turbulent times."},
                {"Ryujin", "If you're all satisfied, then I, as the representative of the Small Guild Union, place my trust in the warrior, Seol-Jin."},
                {"Ryujin", "Does anyone still object?"},
        };
        initStoryNode();

        DSC.diagVal1 = 0;

        DSC.updateChoices("...","I do.");
        DSC.updateCases("","ch1_1");
    }

    public void chapter1_1() {

        GameManager.setCurrentEnemy(new Seol_Jin_npc());
        Dialogue = new String[][]{
                {"Nabi", "And who do you think you are?"},
                {"Mysterious Person", "..."},
                {"Ryujin", "He was the 99th floor master before Avalon took control, " + user.name + "."},
                {user.name, "Glad to hear someone still remembers me, good to see you old friend."},
                {user.name, "Seol-jin, yes? I want to see how you move"},
        };
        initStoryNode();

        DSC.updateChoices("...","Fight Seol-jin");
        DSC.updateCases("","Battle");
    }

    public void chapter1A() {
        Dialogue = new String[][]{
                {"Nabi", "And who are you?"},
                {"test2", "mihim mihim"},
                {"test3", "rahar rahar"},
                {"test4", "hibana hibana"},
        };
        initStoryNode();

        DSC.updateChoices("","");
        DSC.updateCases("","");
    }
    public void chapter1B() {
        Dialogue = new String[][]{
                {"Nabi", "And who are you?"},
                {"test2", "mihim mihim"},
                {"test3", "rahar rahar"},
                {"test4", "hibana hibana"},
        };
        initStoryNode();

        DSC.updateChoices("","");
        DSC.updateCases("","");
    }


    public void chapter2 () {
        Dialogue = new String[][]{
                {"", ""},
                {"", ""},
                {"", ""},
                {"", ""},
        };
        initStoryNode();

        DSC.updateChoices("","");
        DSC.updateCases("","");
    }

    public void chapter3 () {
        Dialogue = new String[][]{
                {"", ""},
                {"", ""},
                {"", ""},
                {"", ""},
        };
        initStoryNode();

        DSC.updateChoices("","");
        DSC.updateCases("","");
    }
}

