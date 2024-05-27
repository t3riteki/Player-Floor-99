package player.game.player_floor99.game_objects.npc;

import player.game.player_floor99.game_objects.powers.Imaginary_Mass_power;
import player.game.player_floor99.game_objects.powers.Power;

public class Player extends Entity{
    public Player(){
        name = "Yama";
        Luck = 40;
        Attack = this.calcAttack();
        HP = this.calcHP();
        Strength = 15;
        Defense = 15;
        Agility = 15;
        Karma = 200;
        power = new Imaginary_Mass_power();
    }

}
