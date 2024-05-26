package player.game.player_floor99;
import player.game.player_floor99.game_objects.Player;

public class StoryHandler {
    private DialogueScreenController DSC;
    public StoryHandler(DialogueScreenController dialogueScreenController) {
        this.DSC = dialogueScreenController;
    }

    int EndingValue = 5;
    Player user = new Player();

    String currentSpeaker,currentDialogue;
    String[][] Dialogue;

    int cnIndex = 0, cdIndex = 1, dialIndex = 0, dialogueSize;

    /*-----------------Initializing Nodes-------------------*/

    public void selectPos(String choice) {
        switch (choice) {
            case "ch1A1":chapter1A1();break;
            case "ch2":chapter2();break;
            case "Ending":ending();break;
        }
    }
    public void gameInit(){
        user.Strength = 15;
        user.Defense = 15;
        user.Agility = 15;
        user.Luck = 2.5;

        user.HP = (int) Math.round(100 + 10*(user.Defense*0.85 + user.Strength*0.25));
        double baseDamage = (user.Strength * 85 + user.Agility * 0.25);
        user.PAttack = (int) Math.round(Math.random() * ((baseDamage - (baseDamage * 0.90)) + (baseDamage * 90)));

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
        }
    }

    public void initStoryNode(){
        DSC.updateCharacterName(Dialogue[dialIndex][cnIndex]);
        DSC.updateCharacterDialogue(Dialogue[dialIndex][cdIndex]);

        dialIndex++;

        DSC.hideChoiceBox();
    }

    /*-----------------Story Nodes-------------------*/

    public void chapter1() {
        Dialogue = new String[][]{
                {"test1", "maharlika maharlika"},
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

    public void ending () {
        if (EndingValue <= 20) {
            DSC.updateCharacterName("");
            DSC.updateCharacterDialogue("");

            DSC.hideChoiceBox();
        } else if (EndingValue >= 5) {
            DSC.updateCharacterName("");
            DSC.updateCharacterDialogue("");

            DSC.hideChoiceBox();
        } else {
            DSC.updateCharacterName("");
            DSC.updateCharacterDialogue("");

            DSC.hideChoiceBox();
        }
    }

    }
