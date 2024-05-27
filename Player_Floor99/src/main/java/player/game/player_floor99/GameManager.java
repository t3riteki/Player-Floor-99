package player.game.player_floor99;

import player.game.player_floor99.game_objects.npc.Entity;

public class GameManager {
    public static Entity currentEnemy;
    public static String currentScene;
    public static String nextScene;
    public static Boolean SeolJinDefeated;
    public static Boolean NabiDefeated;
    public static Boolean LancelotDefeated;

    public static void setSeolJinDefeated(boolean truth){
        SeolJinDefeated = truth;
    }
    public static boolean getSeolJinDefeated(){
        return SeolJinDefeated;
    }

    public static void setNabiDefeated(boolean truth){
        NabiDefeated = truth;
    }
    public static boolean getNabiDefeated(){
        return NabiDefeated;
    }

    public static void setLancelotDefeated(boolean truth){
        LancelotDefeated = truth;
    }
    public static boolean getLancelotDefeated(){
        return LancelotDefeated;
    }


    public static void setNextScene(String scene){
        nextScene = scene;
    }

    public static String getNextScene(String scene){
        return nextScene;
    }

    public static void setCurrentScene(String scene){
        currentScene = scene;
    }

    public static String getCurrentScene(){
        return nextScene;
    }
    public static void setCurrentEnemy(Entity enemy){
        currentEnemy = enemy;
    }
    public static Entity getCurrentEnemy(){
        return currentEnemy;
    }
}