package no.uib.inf101.sem2.game.pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Pokemon {

    private int maxHP;
    private PokemonTypes type;
    private int attack;
    private int currentHp;
    private String name;
    private int random;

    public Pokemon(PokemonTypes type, String name, int hp) {
        this.type = type;
        this.name = name;
        this.maxHP = hp;
        this.currentHp = hp;
        this.random = randomNumber(2);
    }

    public static Pokemon randomPokemon(PokemonTypes type) {
        type = randomPokemonType();
        int random = randomNumber(2);
        if (PokemonTypes.values) {
            switch (type) {
                case WATER:

                    if (random == 1) {
                        Pokemon blastoise = new Pokemon(PokemonTypes.WATER, "Blastoise", 150);
                        return blastoise;
                    }

                    else if (random == 2) {
                        Pokemon magikarp = new Pokemon(PokemonTypes.WATER, "Magikarp", 25);
                        return magikarp;
                    }

                case FIRE:
                    if (random == 1) {
                        Pokemon charizard = new Pokemon(PokemonTypes.FIRE, "Charizard", 150);
                        return charizard;
                    }

            }
        } else
            throw new IllegalArgumentException("Invalid type! Try again " + type);
        return null;

    }

    /**
     * Took inspiration from a Stack Overflow thread;
     * https://stackoverflow.com/questions/1972392/pick-a-random-value-from-an-enum
     * Timestamp - 4.4.2024 @ 12:40
     * 
     * @return
     */
    public static PokemonTypes randomPokemonType() {
        return PokemonTypes.values()[new Random().nextInt(PokemonTypes.values().length)];

    }

    public static int randomNumber(int maxNumber) {
        return (int) (Math.random() * (maxNumber - 1));
    }

}
