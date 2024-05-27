package player.game.player_floor99;

import player.game.player_floor99.game_objects.npc.Entity;

public class GameManager {
    public static Entity currentEnemy;
    public static String nextScene;

    public static void setNextSceneScene(String scene){
        nextScene = scene;
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



/*

5 MILLION KA ING ANA

 */