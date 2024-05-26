package player.game.player_floor99.game_objects;

import player.game.player_floor99.game_objects.powers.Imaginary_Mass_power;
import player.game.player_floor99.game_objects.powers.Power;

public class Player {
    public String name = "Yama";
    public double Luck;
    public int Attack, HP,
            Strength = 15,
            Defense = 15,
            Agility = 15,
            Mana = 200;
    public Power power = new Imaginary_Mass_power();

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