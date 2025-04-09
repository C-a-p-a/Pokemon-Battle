package no.uib.inf101.sem2.game.pokemon;

import java.util.Arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Pokemon {

    private PokemonTypes type1;
    private PokemonTypes type2;

    private int maxHP;
    private int currentHp;

    private int attackStat;
    private int defenseStat;
    private int cooldown;

    private List<Attack> moves;
    private String name;

    public Pokemon(String name, PokemonTypes type1, int maxHP, int attackStat, int defenseStat, int cooldown,
            List<Attack> moves) {
        this.name = name;
        this.type1 = type1;
        this.maxHP = maxHP;
        this.currentHp = maxHP;
        this.attackStat = attackStat;
        this.defenseStat = defenseStat;
        this.cooldown = cooldown;
        this.moves = moves;
    }

    public String getName() {
        return this.name;
    }

    public PokemonTypes getType() {
        return this.type1;
    }

    public int getMaxHP() {
        return this.maxHP;
    }

    public int getAttackStat() {
        return this.attackStat;
    }

    public int defenseStat() {
        return this.defenseStat;
    }

    public int getCooldown() {
        return this.cooldown;
    }

    public List<Attack> getMoves() {
        return this.moves;
    }

    /**
     * Took inspiration from a Stack Overflow thread;
     * https://stackoverflow.com/questions/1972392/pick-a-random-value-from-an-enum
     * Timestamp - 4.4.2025 @ 12:40
     * 
     * @return
     */
    public static PokemonTypes randomPokemonType() {
        return PokemonTypes.values()[new Random().nextInt(PokemonTypes.values().length)];

    }

    public void takeDamage(int damage) {
        if (currentHp < damage) {
            // pokemon dead
        } else {
            currentHp -= damage;
        }
    }

    public boolean hasFainted() {
        if (currentHp <= 0) {
            return true;
        }
        return false;
    }

    public void restoreHP() {
        currentHp = maxHP;
    }

    public static int randomNumber(int maxNumber) {
        return (int) (Math.random() * (maxNumber - 1));
    }

}
