package player.game.player_floor99.game_objects.powers;

import player.game.player_floor99.game_objects.npc.Entity;

public class Malice_power extends Power {
    public int Strength, Defense;
    public Malice_power(){
        attackDialogue = "throws a barrage of karma-infused punches!";
        defendDialogue = "solidifies their karma!";
        skillDialogue = "manifests their karma into their fists throwing an Explosive Punch!";
        fleeDialogue = "Why should I flee?";
    }
    public void applyPassive(Entity user, int percentHP) {
        int userStrength = user.getStrength() + (int)((percentHP/10)*0.75);
        user.setStrength(userStrength);
        int userDefense = user.getDefense() + (int)((percentHP/10)*0.75);
        user.setDefense(userDefense);
    }
}
