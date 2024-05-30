package player.game.player_floor99;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import player.game.player_floor99.game_objects.npc.*;

import java.io.IOException;

public class StoryHandler {
    private DialogueScreenController DSC;
    private Stage stage;

    public StoryHandler(DialogueScreenController dController) {
        this.DSC = dController;
    }

    Player user = new Player();
    public Entity enemy;

    String currentSpeaker, currentDialogue;
    String[][] Dialogue;

    int cnIndex = 0, cdIndex = 1, dialIndex = 0, dialogueSize;

    /*-----------------Initializing Methods-------------------*/

    public void selectPos(String choice) throws IOException {
        switch (choice) {
            case "Battle":
                Battle();
                break;
            case "ch1":
                chapter1();
                break;
            case "ch1a":
                chapter1a();
                break;
            case "ch2":
                chapter2();
                break;
            case "ch2a":
                chapter2a();
                break;
            case "ch2b":
                chapter2b();
                break;
            case "ch3":
                chapter3();
                break;
        }
    }

    public void gameInit() throws IOException{
        switch (GameManager.nextScene) {
            case "ch1":
                chapter1();
                break;
            case "ch1a":
                chapter1a();
                break;
            case "ch2":
                chapter2();
                break;
            case "ch2a":
                chapter2a();
                break;
            case "ch2b":
                chapter2b();
                break;
            case "ch2b_1":
                chapter2b_1();
                break;
            case "ch3":
                chapter3();
                break;
            case "exitProgram":
                exitProgram();
                break;
        }
    }

    public void Battle() throws IOException {
        DSC.switchToBattle();
    }

    public String nextSpeaker(String[][] dialogue) {
        currentSpeaker = dialogue[dialIndex][cnIndex];
        return currentSpeaker;
    }

    public String nextDialogue(String[][] dialogue) {
        currentDialogue = dialogue[dialIndex][cdIndex];
        checkDialogue(Dialogue);
        return currentDialogue;
    }
//
    public void checkDialogue(String[][] array) {
        dialogueSize = array.length;

        if (dialIndex == dialogueSize) {
            DSC.showChoiceBox();
        }

        if (dialIndex > dialogueSize - 1) {
            cnIndex = 0;
            cdIndex = 1;
            dialIndex = 0;

            DSC.disableNextDialogue = true;
        }
    }

    public void initStoryNode() {
        DSC.disableNextDialogue = false;
        DSC.updateCharacterName(Dialogue[dialIndex][cnIndex]);
        DSC.updateCharacterDialogue(Dialogue[dialIndex][cdIndex]);
        DSC.updateCharacterImage(Dialogue[dialIndex][cnIndex]);
        DSC.hideChoiceBox();
        DSC.diagVal1 = 1;
        DSC.diagVal2 = 1;

        dialIndex++;
    }

    public void exitProgram() throws IOException{
        DSC.switchTitleScreen();
    }

    /*-----------------Story Methods-------------------*/

    public void chapter1() {
        Dialogue = new String[][]{
                {"Narrator", "As everyone questions Seol-Jin's worthiness, the battle for the players' representative has begun!"},
                {"Narrator", "Watching the spectacle of a fight between a boy chosen by the Tower's God and a woman hailed for her strength and malice..."},
                {"Narrator", "The crowd roars as the fight abruptly ends... in a conversation?"},
                {"Mysterious Person", "It's getting rowdy..."},
                {"Nabi", "I've taken a liking to you"},
                {"Seol-Jin", "!"},
                {"Nabi", "I'll be counting on you Seol-jin. Flatten Arthur's nose for me!"},
                {"Narrator", "As Nabi declares her defeat and respect, she places her trust in Seol-jin - the Small Guild Union representative, Ryujin, also declared his support for him. However..."},
                {"Ryujin", "Alright you damn cowards, has the boy proven himself enough?"},
                {"Narrator", "A lingering unease, can prove to be a detriment in turbulent times."},
                {"Ryujin", "If you're all satisfied, then I, as the representative of the Small Guild Union, place my trust in the warrior, Seol-Jin."},
                {"Ryujin", "Does anyone still object?"},
        };
        initStoryNode();

        DSC.diagVal1 = 0;

        DSC.updateChoices("...", "I do.");
        DSC.updateCases("", "ch1a");
    }

    public void chapter1a() {
        GameManager.setNextScene("ch2");
        GameManager.setCurrentEnemy(new Seol_Jin_npc());
        Dialogue = new String[][]{
                {"Nabi", "And who do you think you are?"},
                {"Mysterious Person", "..."},
                {"Ryujin", "He was the 99th floor master before Avalon took control, " + user.name + "."},
                {user.name, "Glad to hear someone still remembers me, good to see you old friend."},
                {user.name, "Seol-jin, yes? I want to see how you move"},
        };
        initStoryNode();

        DSC.updateChoices("...", "Fight Seol-jin");
        DSC.updateCases("", "Battle");
    }


    public void chapter2() {
        if (GameManager.getSeolJinDefeated()) {
            chapter2a();
        } else if (!GameManager.getSeolJinDefeated()) {
            chapter2b();
        }
    }

    public void chapter2a(){
        GameManager.setNextScene("ch3");
        GameManager.setCurrentEnemy(new Lancelot_npc());
        Dialogue = new String[][]{
                {"Seol-Jin", "I can't believe I have to use Merlin's karma..."},
                {"Narrator", "You see a strange red karma start to envelope the boy's body"},
                {user.name, "Behave yourself, I concede. I acknowledge your strength."},
                {user.name, "I guess I'll be placing my bets on you as well."},
                {"Lancelot","So you're that dumb God's vessel?"},
                {"Seol-Jin","That karma!"},
                {"Lancelot","You look pretty weak...?"},
                {user.name, "And who are you??"},
                {"Lancelot", "..."},
                {"Narrator", "You notice the wake of bodies behind him"}
        };
        initStoryNode();

        DSC.updateChoices("...","Attack Lancelot");
        DSC.updateCases("","Battle");
    }

    public void chapter2b(){
        GameManager.setNextScene("ch2b_1");
        GameManager.setCurrentEnemy(new Nabi_npc());
        Dialogue = new String[][]{
                {"Seol-Jin", "I can't continue fighting you..."},
                {user.name, "You look down on me boy?"},
                {"Seol-Jin", "..."},
                {user.name, "If you don't have the guts to finish me off, then you don't deserve anyone's respect!"},
                {"Seol-Jin", "..."},
                {"Nabi", "You damn geezer!, if you still aren't satisfied..."},
                {"Nabi", "I'll be the one beating the snot out of you!"},
        };
        initStoryNode();

        DSC.updateChoices("...","Come at me then");
        DSC.updateCases("","Battle");
    }
    public void chapter2b_1() {
        GameManager.setNextScene("ch3");
        GameManager.setCurrentEnemy(new Lancelot_npc());
        Dialogue = new String[][]{
                {user.name, "fine, I yield. I didn't know you felt so strongly."},
                {"Seol-Jin", "Nabi, are you alright?"},
                {user.name, "I guess I'll be placing my bets on you as well Seol-jin"},
                {"Seol-Jin", "!"},
                {"Lancelot", "So you're that damn God's vessel?"},
                {"Seol-Jin", "That karma!"},
                {"Lancelot", "You look pretty weak...?"},
                {user.name, "And who are you??"},
                {"Lancelot", "..."},
                {"Narrator", "You notice the wake of bodies behind him"}
        };
        initStoryNode();

        DSC.updateChoices("...", "Attack Lancelot");
        DSC.updateCases("", "Battle");
    }

    public void chapter3 () {
        Dialogue = new String[][]{
                {"Narrator", "As the battle raged on, a feeling of hopelessness and dread lingers in his mind"},
                {user.name, "Shit, what is this?"},
                {"Narrator", "And a lingering unease, can prove to be a detriment in turbulent times."},
                {"Narrator", "One slip can cost a life"},
                {"Lancelot", "Gotcha"},
                {user.name, "Shit!"},
                {"Narrator", "The sound of flesh ripping fills the battlefield"},
                {"Narrator", "The cold starts to envelope your body"},
                {"Seol-Jin", "YAMAAAAAA!"},
                {user.name, "That karma... merlin"}
        };
        initStoryNode();

        DSC.updateChoices("...","The End");
        DSC.updateCases("","exitProgram");
    }
}


