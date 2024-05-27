package player.game.player_floor99.game_objects.npc;

import player.game.player_floor99.game_objects.powers.Excalibur_power;

public class Seol_Jin_npc extends Entity {
    public Seol_Jin_npc (){
         name = "Seol-jin";
         Luck = 30;
         Attack = this.calcAttack();
         HP = this.calcHP();
         Strength = 17;
         Defense = 17;
         Agility = 11;
         Karma = 300;
         power = new Excalibur_power();
    }
}
