package player.game.player_floor99.game_objects.npc;

public class Lancelot_npc extends NPC {
    public Lancelot_npc() {
        name = "Seol-jin";
        Luck = 30;
        Attack = this.calcAttack();
        HP = this.calcHP();
        Strength = 17;
        Defense = 17;
        Agility = 11;
        Mana = 250;
    }
}
