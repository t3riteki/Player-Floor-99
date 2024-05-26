package player.game.player_floor99;

import player.game.player_floor99.game_objects.npc.NPC;

public class GameManager {
    public static NPC currentEnemy;
    public static String nextScene;

    public static void setNextSceneScene(String scene){
        nextScene = scene;
    }
    public static String getCurrentScene(){
        return nextScene;
    }
    public static void setCurrentEnemy(NPC enemy){
        currentEnemy = enemy;
    }
    public static NPC getCurrentEnemy(){
        return currentEnemy;
    }
}



/*

5 MILLION KA ING ANA

 */