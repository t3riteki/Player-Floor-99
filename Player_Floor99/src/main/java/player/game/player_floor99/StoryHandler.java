package player.game.player_floor99;

public class StoryHandler {
    int EndingValue = 5;
    private DialogueScreenController DSC;
    public StoryHandler(DialogueScreenController dialogueScreenController) {
        this.DSC = dialogueScreenController;
    }

    public void selectPos(String choice) {
        switch (choice) {
            case "Ending":
                ending();
                break;
        }
    }
    public void testcase(){
        DSC.updateCharacterName("test");
        DSC.updateCharacterDialogue("Test Dialogue: Good heavens!, if I was put in a room with Dhamer and Guo given a \nflintlock pistol with 2 rounds. You can be sure that three loads will hit that bloody spy!");
    }

    public void ending(){
        if (EndingValue <= 20){
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
