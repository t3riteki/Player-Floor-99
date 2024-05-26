package player.game.player_floor99.game_objects.npc;

import player.game.player_floor99.game_objects.powers.Power;

public class NPC {
    public String name;
    public double Luck;
    public int Strength, Defense, Agility, Attack,HP, Mana;
    public Power power;

    public int calcAttack(){
        double baseDamage = 5.5*(Strength * 0.85 + Agility * 0.15);
        Attack = (int) Math.round(Math.random() * ((baseDamage - (baseDamage * 0.90)) + (baseDamage * 0.90)));
        return Attack;
    }

    public int calcHP(){
        HP = (int) Math.round(100 + 10*(Defense*0.85 + Strength*0.15));
        return HP;
    }

}
