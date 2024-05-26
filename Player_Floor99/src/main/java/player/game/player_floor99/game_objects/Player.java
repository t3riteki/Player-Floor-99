package player.game.player_floor99.game_objects;

public class Player {
    public String name;
    public double Luck;
    public int PAttack, HP,
            Strength = 15,
            Defense = 15,
            Agility = 15,
            Mana = 200;

    public void calcPAttack(){
        double baseDamage = (Strength * 85 + Agility * 0.25);
        PAttack = (int) Math.round(Math.random() * ((baseDamage - (baseDamage * 0.90)) + (baseDamage * 90)));
    }

    public void calcHP(){
        HP = (int) Math.round(100 + 10*(Defense*0.85 + Strength*0.25));
    }


}
