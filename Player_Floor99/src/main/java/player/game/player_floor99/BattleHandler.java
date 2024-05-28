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

    int playerAtkDamage, playerAttack, playerHP, playerHPMax,
            playerKarma, playerDamageReduction, enemyAttack ,
            enemyAtkDamage, enemyHP, enemyKarma, enemyDamageReduction,
            enemyHPmax;
            ;
    boolean playerDefend, enemyDefended;

    String currentTurn, nextTurn;

    /*-----------------Initializing Methods-------------------*/

    public void battleInit()throws IOException{

        playerHP = user.calcHP();
        playerHPMax = user.calcHP();
        user.setHP(playerHP);

        playerAttack = user.calcAttack();
        user.setAttack(playerAttack);

        playerKarma = user.Karma;
        user.setKarma(playerKarma);

        playerDamageReduction = (int)(((user.Defense)*0.85)*2.5);

        enemyHP = enemy.calcHP();
        enemyHPmax = enemy.calcHP();
        enemy.setHP(enemyHP);

        enemyAttack = enemy.calcAttack();
        enemy.setAttack(enemyAttack);

        enemyKarma = enemy.Karma;
        enemy.setKarma(enemyKarma);

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
        enemy.power.applyPassive(enemy);
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
        user.setHP(playerHP);
        user.setKarma(playerKarma);
        user.setAttack(playerAttack);
        user.power.applyPassive(user);
        user.power.applyPassive(user, playerHPMax);

        enemy.setHP(enemyHP);
        enemy.setAttack(enemyAttack);
        enemy.setKarma(enemyKarma);
        user.power.applyPassive(enemy);
        user.power.applyPassive(enemy,enemyHPmax);

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
        user.power.applyPassive(user);
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

    public int getAttackDamage(int baseAttack, int damageReduction){
        return (int) Math.round(((Math.random() * (baseAttack - (baseAttack * 0.90))) + (baseAttack * 0.90)) - damageReduction);
    }

    /*-----------------Action Methods-------------------*/

    private void PlayerAttack() throws IOException{
        playerAtkDamage = getAttackDamage(playerAttack, enemyDamageReduction);
        if (enemyDefended) {
            enemyHP -= (int)(playerAtkDamage*0.65);

            BSC.updateBattleDialogue(user.name+" "+user.power.attackDialogue);
            BSC.updatePlayerStatus(playerHP, playerKarma);
            BSC.updateEnemyStatus(enemyHP, enemyKarma);

            enemyAttack+= (int)(enemyAttack*0.125);
            enemyDefended = false;
            System.out.println(playerAtkDamage);
            finishTurn();
        }else {
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
        playerAtkDamage = getAttackDamage(playerAttack, enemyDamageReduction);
        int chance = (int) Math.round((Math.random()*10));
        int AtkDamage;
        if (user.Luck >= chance){
            AtkDamage = (int) (playerAtkDamage * (((user.getStrength() + user.getAgility())/1.5)/10));
            enemyHP -= AtkDamage;
            playerKarma -= (int)(AtkDamage / 4);
        } else {
            AtkDamage = playerAtkDamage * (((user.getStrength() + user.getAgility())/2)/10);
            enemyHP -= AtkDamage;
            playerKarma -= (int)(AtkDamage / 3);
        }

        BSC.updateBattleDialogue(user.name + " " + user.power.skillDialogue);
        BSC.updatePlayerStatus(playerHP, playerKarma);
        BSC.updateEnemyStatus(enemyHP, enemyKarma);
        finishTurn();
    }

    private void PlayerFlee() throws IOException{
        BSC.updateBattleDialogue(user.power.fleeDialogue);
        BSC.updatePlayerStatus(playerHP, playerKarma);
        BSC.updateEnemyStatus(enemyHP, enemyKarma);
        finishTurn();
    }

    private void NPCAttack() throws IOException{
        enemyAtkDamage = getAttackDamage(enemyAttack, playerDamageReduction);
        if (playerDefend) {
            playerHP-= (int)(enemyAtkDamage*0.5);

            BSC.updateBattleDialogue(enemy.name+" "+enemy.power.attackDialogue);
            BSC.updatePlayerStatus(playerHP, playerKarma);
            BSC.updateEnemyStatus(enemyHP, enemyKarma);

            playerAttack += (int)(playerAttack*0.125);
            playerDefend = false;
            System.out.println(enemyAtkDamage);
            finishTurn();
        } else {
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
        enemyAtkDamage = getAttackDamage(enemyAttack, playerDamageReduction);
        int chance = (int) Math.round((Math.random()*10));
        int AtkDamage;
        if (enemy.Luck >= chance){
            AtkDamage = (int) (enemyAtkDamage * (((enemy.getStrength() + enemy.getAgility())/1.5)/10));
            playerHP -= AtkDamage;
            enemyKarma -= Math.round(AtkDamage / 4);
        } else {
            AtkDamage = enemyAtkDamage * (((enemy.getStrength() + enemy.getAgility())/2)/10);
            enemyHP -= AtkDamage;
            enemyKarma -= Math.round(AtkDamage / 3);
        }

        BSC.updateBattleDialogue(enemy.name + " " + enemy.power.skillDialogue);
        BSC.updatePlayerStatus(playerHP, playerKarma);
        BSC.updateEnemyStatus(enemyHP, enemyKarma);
        finishTurn();
    }

    private void NPCFlee() throws IOException{
        BSC.updateBattleDialogue(enemy.power.fleeDialogue);
        BSC.updatePlayerStatus(playerHP, playerKarma);
        BSC.updateEnemyStatus(enemyHP, enemyKarma);
        finishTurn();
    }
}
