package player.game.player_floor99.game_objects.npc;

import player.game.player_floor99.game_objects.powers.Monster_Eater_Power;

public class Lancelot_npc extends Entity {
    public Lancelot_npc() {
        name = "Lancelot";
        Luck = 30;
        Attack = this.calcAttack();
        HP = this.calcHP();
        Strength = 20;
        Defense = 35;
        Agility = 14;
        Karma = 1000;
        power = new Monster_Eater_Power();
    }
}
