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

    public void setStrength(int strength){
        this.Strength = strength;
    }
    public int getStrength(){
        return Strength;
    }

    public void setDefense(int defense){
        this.Defense = defense;
    }
    public int getDefense(){
        return Defense;
    }

    public void setAgility(int agility){
        this.Agility = agility;
    }
    public int getAgility(){
        return Agility;
    }

    public void setLuck(int luck){
        this.Luck = luck;
    }
    public double getLuck(){
        return Luck;
    }

    public void setHP(int hp){
        this.HP = hp;
    }
    public int getHP(){
        return HP;
    }

    public void setAttack(int attack){
        this.Attack = attack;
    }

    public int getAttack(){
        return Attack;
    }

    public void setKarma(int karma){
        this.Karma = karma;
    }
    public int getKarma(){
        return Karma;
    }

}
