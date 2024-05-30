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
        System.out.println("Battle Init");
        BSC.updateEnemyImage(enemy.name);

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
        System.out.println("CFTurn");
        if (user.Agility > enemy.Agility){
            currentTurn = "Player";
        }
        else if (user.Agility < enemy.Agility){
            currentTurn = "Enemy";
        }
        switchTurn();
    }

    public void switchTurn () throws IOException{
        System.out.println("switch Turn");
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
        System.out.println("PTurn");
        enemy.power.applyPassive(enemy);
        nextTurn = "Enemy";
        BSC.updateBattleDialogue("Your Turn");
        BSC.updateCases("Attack","Defend","Skill","Flee", "");
    }

    public void selectAction(String action) throws IOException{
        System.out.println("Sel act");
        switch(action){
            case"Attack":PlayerAttack();break;
            case"Defend":PlayerDefend();break;
            case"Skill":PlayerSkill();break;
            case"Flee":PlayerFlee();break;
            case"nextTurn":nextTurn();break;
        }
    }

    public void finishTurn() throws IOException {
        System.out.println("Fturn");

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
        BSC.updateCases("","","","", "nextTurn");

        checkWinner();
    }

    public void nextTurn()throws IOException{
        System.out.println("nxtturn");
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
        System.out.println("ETurn");
        user.power.applyPassive(user);
        nextTurn = "Player";
        BSC.updateCases("","","","","nextTurn");
        String action = Integer.toString((int) Math.round(Math.random()*4));
        switch (action){
            case"1": NPCAttack();break;
            case"2": NPCDefend();break;
            case"3": NPCSkill();break;
            case"4": NPCFlee();break;
        }
    }

    public void checkWinner() throws IOException {
        System.out.println("check winner");
        if (playerHP <= 0) {
            System.out.println(enemy.name+" wins!");
            switch(enemy.name){
                case"Seol-jin": GameManager.setSeolJinDefeated(false);break;
                case"Nabi": GameManager.setNabiDefeated(false);break;
                case"Lancelot": GameManager.setLancelotDefeated(false);break;
            }
            BSC.switchToDialogue();
        }

        if (enemyHP <= 0) {
            System.out.println(user.name+" wins!");
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
        System.out.println("PAttack");
        if (enemyDefended) {
            System.out.println("EyesDef");
            enemyHP -= (int)(playerAtkDamage*0.65);
            enemyAttack+= (int)(enemyAttack*0.125);
            enemyDefended = false;

            BSC.updateBattleDialogue(user.name+" "+user.power.attackDialogue);
            BSC.updatePlayerStatus(playerHP, playerKarma);
            BSC.updateEnemyStatus(enemyHP, enemyKarma);

            System.out.println(playerAtkDamage);
            finishTurn();
        }
        else {
            enemyHP -= playerAtkDamage;
            System.out.println("EnoDef");

            BSC.updateBattleDialogue(user.name+" "+user.power.attackDialogue);
            BSC.updatePlayerStatus(playerHP, playerKarma);
            BSC.updateEnemyStatus(enemyHP, enemyKarma);

            System.out.println(playerAtkDamage);
            finishTurn();
        }
    }

    private void PlayerDefend() throws IOException{
        playerDefend = true;
        System.out.println("PDef");
        BSC.updateBattleDialogue(user.name+" "+user.power.defendDialogue);
        finishTurn();
    }

    private void PlayerSkill() throws IOException{
        playerAtkDamage = getAttackDamage(playerAttack, enemyDamageReduction);
        int chance = (int) Math.floor((Math.random()*10)+1);
        int AtkDamage;
        System.out.println(chance);

        if (user.Luck >= chance){
            AtkDamage = (int) (playerAtkDamage * (((user.getStrength() + user.getAgility())/5)/10)+1);
            System.out.println("PinChance");
            if (enemyDefended) {
                System.out.println("EyesDef");
                enemyHP -= (int)(AtkDamage*0.65);
                enemyAttack+= (int)(enemyAttack*0.125);
                playerKarma -= (int)Math.round(AtkDamage / 4);
                enemyDefended = false;

                BSC.updateBattleDialogue(user.name + " " + user.power.skillDialogue);
                BSC.updatePlayerStatus(playerHP, playerKarma);
                BSC.updateEnemyStatus(enemyHP, enemyKarma);
                finishTurn();
            }
            else {
                enemyHP -= AtkDamage;
                playerKarma -= (int)Math.round(AtkDamage / 4);
                System.out.println("EnoDef");

                BSC.updateBattleDialogue(user.name + " " + user.power.skillDialogue);
                BSC.updatePlayerStatus(playerHP, playerKarma);
                BSC.updateEnemyStatus(enemyHP, enemyKarma);
                finishTurn();
            }

        } else {
            AtkDamage = playerAtkDamage * (int)((((user.getStrength() + user.getAgility())/5.5)/10)+1);
            System.out.println("PoutChance");
            if (enemyDefended) {
                System.out.println("EyesDef");
                enemyHP -= (int)(AtkDamage*0.65);
                enemyAttack+= (int)(enemyAttack*0.125);
                playerKarma -= (int)Math.round(AtkDamage / 3);
                enemyDefended = false;

                BSC.updateBattleDialogue(user.name + " " + user.power.skillDialogue);
                BSC.updatePlayerStatus(playerHP, playerKarma);
                BSC.updateEnemyStatus(enemyHP, enemyKarma);
                finishTurn();
            }
            else {
                System.out.println("EnoDef");
                enemyHP -= AtkDamage;
                playerKarma -= (int)Math.round(AtkDamage / 3);

                BSC.updateBattleDialogue(user.name + " " + user.power.skillDialogue);
                BSC.updatePlayerStatus(playerHP, playerKarma);
                BSC.updateEnemyStatus(enemyHP, enemyKarma);
                finishTurn();
            }
        }
    }

    private void PlayerFlee() throws IOException{
        System.out.println("Pflee");
        BSC.updateBattleDialogue(user.power.fleeDialogue);
        BSC.updatePlayerStatus(playerHP, playerKarma);
        BSC.updateEnemyStatus(enemyHP, enemyKarma);
        finishTurn();
    }

    private void NPCAttack() throws IOException{
        System.out.println("EAttack");
        enemyAtkDamage = getAttackDamage(enemyAttack, playerDamageReduction);

        if (playerDefend) {
            System.out.println("PyesDef");
            playerHP-= (int)(enemyAtkDamage*0.65);
            playerAttack += (int)(playerAttack*0.125);
            playerDefend = false;

            BSC.updateBattleDialogue(enemy.name + " " + enemy.power.attackDialogue);
            BSC.updatePlayerStatus(playerHP, playerKarma);
            BSC.updateEnemyStatus(enemyHP, enemyKarma);

            System.out.println(enemyAtkDamage);
            finishTurn();
        }

        else {
            System.out.println("PnoDef");
            playerHP -= enemyAtkDamage;

            BSC.updateBattleDialogue(enemy.name + " " + enemy.power.attackDialogue);
            BSC.updatePlayerStatus(playerHP, playerKarma);
            BSC.updateEnemyStatus(enemyHP, enemyKarma);

            System.out.println(enemyAtkDamage);
            finishTurn();
        }
    }

    private void NPCDefend() throws IOException{
        System.out.println("EDef");
        enemyDefended = true;
        BSC.updateBattleDialogue(enemy.name+" "+enemy.power.defendDialogue);
        finishTurn();
    }

    private void NPCSkill() throws IOException{
        System.out.println("ESkill");
        enemyAtkDamage = getAttackDamage(enemyAttack, playerDamageReduction);
        int chance = (int) Math.round((Math.random()*10));
        int AtkDamage;

        System.out.println(chance);

        if (enemy.Luck >= chance){
            AtkDamage = (int) (enemyAtkDamage * (((enemy.getStrength() + enemy.getAgility())/1.5)/10));
            System.out.println("EinChance");
            if (playerDefend) {
                System.out.println("PyesDef");
                playerHP -= (int)(AtkDamage*0.65);
                playerAttack+= (int)(playerAttack*0.125);
                playerDefend = false;

                System.out.println(AtkDamage);

                enemyKarma -= Math.round(AtkDamage/4);
                BSC.updateBattleDialogue(enemy.name + " " + enemy.power.skillDialogue);
                BSC.updatePlayerStatus(playerHP, playerKarma);
                BSC.updateEnemyStatus(enemyHP, enemyKarma);
                finishTurn();
            }
            else {
                System.out.println("PnoDef");
                playerHP -= AtkDamage;
                enemyKarma -= Math.round(AtkDamage/4);

                System.out.println(AtkDamage);

                BSC.updateBattleDialogue(enemy.name + " " + enemy.power.skillDialogue);
                BSC.updatePlayerStatus(playerHP, playerKarma);
                BSC.updateEnemyStatus(enemyHP, enemyKarma);
                finishTurn();
            }

        } else {
            AtkDamage = enemyAtkDamage * (((enemy.getStrength() + enemy.getAgility())/2)/10);
            System.out.println("EoutChance");
            if (playerDefend) {
                System.out.println("PyesDef");
                playerHP -= (int)(AtkDamage*0.65);
                playerAttack+= (int)(playerAttack*0.125);
                playerDefend = false;

                System.out.println(AtkDamage);

                enemyKarma -= Math.round(AtkDamage/3);
                BSC.updateBattleDialogue(enemy.name + " " + enemy.power.skillDialogue);
                BSC.updatePlayerStatus(playerHP, playerKarma);
                BSC.updateEnemyStatus(enemyHP, enemyKarma);
                finishTurn();
            }
            else {
                System.out.println("PnoDef");
                playerHP -= AtkDamage;
                enemyKarma -= Math.round(AtkDamage/3);

                System.out.println(AtkDamage);

                BSC.updateBattleDialogue(enemy.name + " " + enemy.power.skillDialogue);
                BSC.updatePlayerStatus(playerHP, playerKarma);
                BSC.updateEnemyStatus(enemyHP, enemyKarma);
                finishTurn();
            }
        }
    }

    private void NPCFlee() throws IOException{
        System.out.println("EFlee");
        BSC.updateBattleDialogue(enemy.power.fleeDialogue);
        BSC.updatePlayerStatus(playerHP, playerKarma);
        BSC.updateEnemyStatus(enemyHP, enemyKarma);
        finishTurn();
    }
}
