package player.game.player_floor99.game_objects.npc;

import player.game.player_floor99.game_objects.powers.Power;

public class Entity {
    public String name;
    public double Luck;
    public int Strength, Defense, Agility, Attack,HP, Karma;
    public Power power;

    public int calcAttack(){
        Attack = (int) (5.5*(Strength * 0.85 + Agility * 0.15));
        return Attack;
    }

    public int calcHP(){
        HP = (int) Math.round(100 + 10*(Defense*0.85 + Strength*0.15));
        return HP;
    }

}
