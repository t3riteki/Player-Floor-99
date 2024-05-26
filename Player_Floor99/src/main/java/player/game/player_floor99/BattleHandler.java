package player.game.player_floor99;

import player.game.player_floor99.game_objects.Player;
import player.game.player_floor99.game_objects.npc.NPC;

public class BattleHandler {
    public BattleScreenController BSC;

    public BattleHandler(BattleScreenController bController) {
        this.BSC = bController;
    }

    Player user = new Player();
    NPC enemy = GameManager.getCurrentEnemy();

    int playerAtkDamage, playerHP, playerMana, enemyAttack, enemyHP,enemyMana;

    String BattleText, currentTurn, action;

    Boolean BattleFinished, playerTurn;

    /*-----------------Initializing Methods-------------------*/

    public void battleInit(){
        playerAtkDamage = (int) Math.round(user.calcAttack() - ((enemy.Defense)*0.85)*2.5);
        playerHP = user.calcHP();
        playerMana = user.Mana;

        enemyAttack = (int) Math.round(enemy.calcAttack() - ((user.Defense)*0.85)*2.5);
        enemyHP = enemy.calcHP();
        enemyMana = enemy.Mana;

        BSC.updatePlayerStatus(playerHP, playerMana);
        BSC.updateEnemyStatus(enemyHP,enemyMana);

        checkFirstTurn();
    }

    public void checkFirstTurn(){
        if (user.Agility > enemy.Agility){
            currentTurn = "Player";
        }
        else if (user.Agility < enemy.Agility){
            currentTurn = "Enemy";
        }

        switchTurn();
    }

    public void switchTurn () {
        switch (currentTurn) {
            case "Player":
                PlayerTurn();
                break;
            case "Enemy":
                EnemyTurn();
                break;
        }
    }

    public void PlayerTurn() {
        BSC.updateBattleDialogue("Your Turn");
        BSC.updateCases("Attack","Defend","Skill","Flee");
    }

    public void selectAction(String action){
        switch(action){
            case"Attack":PlayerAttack();break;
            case"Defend":PlayerDefend();break;
            case"Skill":PlayerSkill();break;
            case"Flee":PlayerFlee();break;
        }
    }

    public void EnemyTurn (){
        System.out.println("EnemyTurn");
    }


    public void checkWinner(int healthPlayer, int healthNPC){
        if (healthPlayer <= 0) {

        }

        if (healthNPC <= 0) {

        }
    }

    /*-----------------Action Methods-------------------*/

    private void PlayerAttack() {
        enemyHP-=playerAtkDamage;
        BSC.updateBattleDialogue(user.power.attackDialogue);
        BSC.updateEnemyStatus(enemyHP, enemyMana);
        EnemyTurn();
    }

    private void PlayerDefend() {
        System.out.println("Defend");
    }

    private void PlayerSkill() {
        System.out.println("Skill");
    }

    private void PlayerFlee() {
        System.out.println("Flee");
    }

    private void NPCAttack() {
        PlayerTurn();
    }

    private void NPCDefend() {
        PlayerTurn();
    }

    private void NPCSkill() {
        PlayerTurn();
    }

    private void NPCFlee() {
        PlayerTurn();
    }
}
