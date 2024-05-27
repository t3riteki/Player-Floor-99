package player.game.player_floor99;

import player.game.player_floor99.game_objects.npc.Player;
import player.game.player_floor99.game_objects.npc.Entity;

public class BattleHandler {
    public BattleScreenController BSC;

    public BattleHandler(BattleScreenController bController) {
        this.BSC = bController;
    }

    Player user = new Player();
    Entity enemy = GameManager.getCurrentEnemy();

    int playerAtkDamage, playerAttack, playerHP, playerKarma, playerDamageReduction, enemyAttack ,enemyAtkDamage, enemyHP,enemyKarma, enemyDamageReduction;
    boolean playerDefend, enemyDefended;

    String currentTurn, nextTurn;

    /*-----------------Initializing Methods-------------------*/

    public void battleInit(){

        playerHP = user.calcHP();
        playerAttack = user.calcAttack();
        playerKarma = user.Karma;
        playerDamageReduction = (int)(((user.Defense)*0.85)*2.5);

        enemyHP = enemy.calcHP();
        enemyAttack = enemy.calcAttack();
        enemyKarma = enemy.Karma;
        enemyDamageReduction = (int)(((enemy.Defense)*0.85)*2.5);

        BSC.updatePlayerStatus(playerHP, playerKarma);
        BSC.updateEnemyStatus(enemyHP,enemyKarma);

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
        nextTurn = "Enemy";
        BSC.updateBattleDialogue("Your Turn");
        BSC.updateCases("Attack","Defend","Skill","Flee", "");
    }

    public void selectAction(String action){
        switch(action){
            case"Attack":PlayerAttack();break;
            case"Defend":PlayerDefend();break;
            case"Skill":PlayerSkill();break;
            case"Flee":PlayerFlee();break;
            case"nextTurn":nextTurn();break;
        }
    }

    public void finishTurn(){
        checkWinner();
        BSC.updateCases("","","","", "nextTurn");
    }

    public void nextTurn(){
        switch (nextTurn) {
            case "Player":
                PlayerTurn();
                break;
            case "Enemy":
                EnemyTurn();
                break;
        }
    }

    public void EnemyTurn (){
        nextTurn = "Player";
        BSC.updateCases("","","","","");
        NPCAttack();
    }


    public void checkWinner(){
        if (playerHP <= 0) {
            System.out.println(enemy.name + " wins!");
        }

        if (enemyHP <= 0) {
            System.out.println(user.name + " wins!");
        }
    }

    /*-----------------Action Methods-------------------*/

    private void PlayerAttack() {
        if (enemyDefended) {
            enemyDamageReduction= (int)(enemyDamageReduction * 3);

            playerAtkDamage = (int) Math.round(((Math.random() * (playerAttack - (playerAttack * 0.90))) + (playerAttack * 0.90)) - enemyDamageReduction);
            enemyHP -= playerAtkDamage;

            BSC.updateBattleDialogue(user.name+" "+user.power.attackDialogue);
            BSC.updatePlayerStatus(playerHP, playerKarma);
            BSC.updateEnemyStatus(enemyHP, enemyKarma);

            enemyDefended = false;
            enemyDamageReduction= (int)(enemyDamageReduction / 1.5);
            System.out.println(playerAtkDamage);
            finishTurn();
        }else {
            playerAtkDamage = (int) Math.round(((Math.random() * (playerAttack - (playerAttack * 0.90))) + (playerAttack * 0.90)) - enemyDamageReduction);
            enemyHP -= playerAtkDamage;

            BSC.updateBattleDialogue(user.name + " " + user.power.attackDialogue);
            BSC.updatePlayerStatus(playerHP, playerKarma);
            BSC.updateEnemyStatus(enemyHP, enemyKarma);

            enemyDefended = false;
            enemyDamageReduction = (int) (enemyDamageReduction / 1.5);
            System.out.println(playerAtkDamage);
            finishTurn();
        }
    }

    private void PlayerDefend() {
        playerDefend = true;
        BSC.updateBattleDialogue(user.name+" "+user.power.defendDialogue);
        finishTurn();
    }

    private void PlayerSkill() {
        System.out.println("Skill");

        finishTurn();
    }

    private void PlayerFlee() {
        System.out.println("Flee");

        finishTurn();
    }

    private void NPCAttack() {
        if (playerDefend) {
            playerDamageReduction= (int)(playerDamageReduction * 3);

            enemyAtkDamage = (int) Math.round(((Math.random() * (enemyAttack- (enemyAttack * 0.90))) + (enemyAttack * 0.90)) - playerDamageReduction);

            playerHP-= enemyAtkDamage;

            BSC.updateBattleDialogue(enemy.name+" "+enemy.power.attackDialogue);
            BSC.updatePlayerStatus(playerHP, playerKarma);
            BSC.updateEnemyStatus(enemyHP, enemyKarma);

            playerDefend = false;
            playerDamageReduction= (int)(playerDamageReduction / 1.5);
            System.out.println(enemyAtkDamage);
            finishTurn();
        } else {
            enemyAtkDamage = (int) Math.round(((Math.random() * (enemyAttack - (enemyAttack * 0.90))) + (enemyAttack * 0.90)) - playerDamageReduction);

            playerHP -= enemyAtkDamage;

            BSC.updateBattleDialogue(enemy.name + " " + enemy.power.attackDialogue);
            BSC.updatePlayerStatus(playerHP, playerKarma);
            BSC.updateEnemyStatus(enemyHP, enemyKarma);

            playerDefend = false;
            playerDamageReduction = (int) (playerDamageReduction / 1.5);
            System.out.println(enemyAtkDamage);
            finishTurn();
        }
    }

    private void NPCDefend() {
        enemyDefended = true;
        BSC.updateBattleDialogue(enemy.name+" "+enemy.power.defendDialogue);
        finishTurn();
    }

    private void NPCSkill() {
        finishTurn();
    }

    private void NPCFlee() {
        finishTurn();
    }
}
