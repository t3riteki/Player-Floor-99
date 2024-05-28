package player.game.player_floor99.game_objects.powers;

import player.game.player_floor99.game_objects.npc.Entity;

public class Excalibur_power extends Power {
    public Excalibur_power(){
        attackDialogue = "sends out slashes all around!";
        defendDialogue = "manifests excalibur into a Greatsword to defend!";
        skillDialogue = "concentrates their karma in to excalibur, and slashes space!";
        fleeDialogue = "fleeing is not an option! I have to prove my self";
    }

    @Override
    public void applyPassive(Entity user) {
        int userStrength = (user.Strength +=(int)Math.round((user.getKarma()*0.50))/10);
        user.setStrength(userStrength);
    }
}
