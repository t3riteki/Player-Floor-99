package player.game.player_floor99.game_objects.powers;

import player.game.player_floor99.game_objects.npc.Entity;

public abstract class Power {
    public String attackDialogue, defendDialogue, skillDialogue, fleeDialogue;
    public Entity user;
    public void applyPassive(){
    }
    public void applyTraits(){
    }
}
