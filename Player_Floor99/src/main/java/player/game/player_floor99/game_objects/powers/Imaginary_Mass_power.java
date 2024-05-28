package player.game.player_floor99.game_objects.powers;

import player.game.player_floor99.game_objects.npc.Entity;

public class Imaginary_Mass_power extends Power{
    public Imaginary_Mass_power (){
        attackDialogue = "dashes in to punch with the imaginary weight of a mountain!";
        defendDialogue = "blocks changing his arms' imaginary mass!";
        skillDialogue = "concentrating a large portion of their karma into their hands, they clap...";
        fleeDialogue = "Fear is the mind killer. I must not flee";
    }
    @Override
    public void applyPassive(Entity user){
        int userStrength = (user.Strength +=(int)Math.round((user.getKarma()*0.30)/10));
        user.setStrength(userStrength);

        int userDefense = (user.Defense += (int)Math.round(user.getKarma()*0.30));
        user.setDefense(userDefense);
    }
}
