package player.game.player_floor99;

import player.game.player_floor99.game_objects.npc.Player;
import player.game.player_floor99.game_objects.npc.Entity;

import java.io.IOException;

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

    public void battleInit()throws IOException{

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

    public void checkFirstTurn()throws IOException{
        if (user.Agility > enemy.Agility){
            currentTurn = "Player";
        }
        else if (user.Agility < enemy.Agility){
            currentTurn = "Enemy";
        }

        switchTurn();
    }

    public void switchTurn () throws IOException{
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

    public void selectAction(String action) throws IOException{
        switch(action){
            case"Attack":PlayerAttack();break;
            case"Defend":PlayerDefend();break;
            case"Skill":PlayerSkill();break;
            case"Flee":PlayerFlee();break;
            case"nextTurn":nextTurn();break;
        }
    }

    public void finishTurn() throws IOException {
        checkWinner();
        BSC.updateCases("","","","", "nextTurn");
    }

    public void nextTurn()throws IOException{
        switch (nextTurn) {
            case "Player":
                PlayerTurn();
                break;
            case "Enemy":
                EnemyTurn();
                break;
        }
    }

    public void EnemyTurn ()throws IOException{
        nextTurn = "Player";
        BSC.updateCases("","","","","");
        NPCAttack();
    }

    public void checkWinner() throws IOException {
        if (playerHP < 0) {
            switch(enemy.name){
                case"Seol-jin": GameManager.setSeolJinDefeated(false);break;
                case"Nabi": GameManager.setNabiDefeated(false);break;
                case"Lancelot": GameManager.setLancelotDefeated(false);break;
            }
            BSC.switchToDialogue();
        }

        if (enemyHP < 0) {
            System.out.println("You win");
            switch(enemy.name) {
                case "Seol-jin":
                    GameManager.setSeolJinDefeated(true);break;
                case "Nabi":
                    GameManager.setNabiDefeated(true);break;
                case "Lancelot":
                    GameManager.setLancelotDefeated(true);break;
            }
            BSC.switchToDialogue();
        }
    }

    /*-----------------Action Methods-------------------*/

    private void PlayerAttack() throws IOException{
        if (enemyDefended) {

            playerAtkDamage = (int) Math.round(((Math.random() * (playerAttack - (playerAttack * 0.90))) + (playerAttack * 0.90)) - enemyDamageReduction);
            enemyHP -= (int)(playerAtkDamage*0.65);

            BSC.updateBattleDialogue(user.name+" "+user.power.attackDialogue);
            BSC.updatePlayerStatus(playerHP, playerKarma);
            BSC.updateEnemyStatus(enemyHP, enemyKarma);

            enemy.Strength+=(int) Math.round(enemy.Strength*2);

            enemyAttack+= (int)(enemyAttack*0.125);
            enemyDefended = false;
            System.out.println(playerAtkDamage);
            finishTurn();
        }else {
            playerAtkDamage = (int) Math.round(((Math.random() * (playerAttack - (playerAttack * 0.90))) + (playerAttack * 0.90)) - enemyDamageReduction);
            enemyHP -= playerAtkDamage;

            BSC.updateBattleDialogue(user.name + " " + user.power.attackDialogue);
            BSC.updatePlayerStatus(playerHP, playerKarma);
            BSC.updateEnemyStatus(enemyHP, enemyKarma);

            System.out.println(playerAtkDamage);
            finishTurn();
        }
    }

    private void PlayerDefend() throws IOException{
        playerDefend = true;
        BSC.updateBattleDialogue(user.name+" "+user.power.defendDialogue);
        finishTurn();
    }

    private void PlayerSkill() throws IOException{
        System.out.println("Skill");

        finishTurn();
    }

    private void PlayerFlee() throws IOException{
        BSC.updateBattleDialogue(user.power.fleeDialogue);
        BSC.updatePlayerStatus(playerHP, playerKarma);
        BSC.updateEnemyStatus(enemyHP, enemyKarma);
        finishTurn();
    }

    private void NPCAttack() throws IOException{
        if (playerDefend) {
            enemyAtkDamage = (int) Math.round(((Math.random() * (enemyAttack- (enemyAttack * 0.90))) + (enemyAttack * 0.90)) - playerDamageReduction);

            playerHP-= (int)(enemyAtkDamage*0.5);

            BSC.updateBattleDialogue(enemy.name+" "+enemy.power.attackDialogue);
            BSC.updatePlayerStatus(playerHP, playerKarma);
            BSC.updateEnemyStatus(enemyHP, enemyKarma);

            playerAttack += (int)(playerAttack*0.25);
            playerDefend = false;
            System.out.println(enemyAtkDamage);
            finishTurn();
        } else {
            enemyAtkDamage = (int) Math.round(((Math.random() * (enemyAttack - (enemyAttack * 0.90))) + (enemyAttack * 0.90)) - playerDamageReduction);

            playerHP -= enemyAtkDamage;

            BSC.updateBattleDialogue(enemy.name + " " + enemy.power.attackDialogue);
            BSC.updatePlayerStatus(playerHP, playerKarma);
            BSC.updateEnemyStatus(enemyHP, enemyKarma);

            System.out.println(enemyAtkDamage);
            finishTurn();
        }
    }

    private void NPCDefend() throws IOException{
        enemyDefended = true;
        BSC.updateBattleDialogue(enemy.name+" "+enemy.power.defendDialogue);
        finishTurn();
    }

    private void NPCSkill() throws IOException{
        finishTurn();
    }

    private void NPCFlee() throws IOException{
        BSC.updateBattleDialogue(enemy.power.fleeDialogue);
        BSC.updatePlayerStatus(playerHP, playerKarma);
        BSC.updateEnemyStatus(enemyHP, enemyKarma);
        finishTurn();
    }
}
