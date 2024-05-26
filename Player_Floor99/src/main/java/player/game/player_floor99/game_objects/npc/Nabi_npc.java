package player.game.player_floor99.game_objects.npc;

public class Nabi_npc extends NPC {
    public Nabi_npc() {
        name = "Nabi";
        Luck = 10;
        Attack = this.calcAttack();
        HP = this.calcHP();
        Strength = 17;
        Defense = 12;
        Agility = 16;
        Mana = 150;
    }

}
