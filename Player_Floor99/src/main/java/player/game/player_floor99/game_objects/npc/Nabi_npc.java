package player.game.player_floor99.game_objects.npc;

import player.game.player_floor99.game_objects.powers.Malice_power;

public class Nabi_npc extends Entity {
    public Nabi_npc() {
        name = "Nabi";
        Luck = 6;
        Attack = this.calcAttack();
        HP = this.calcHP();
        Strength = 17;
        Defense = 12;
        Agility = 16;
        Karma = 150;
        power = new Malice_power();
    }

}
