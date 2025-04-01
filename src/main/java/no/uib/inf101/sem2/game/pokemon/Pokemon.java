package no.uib.inf101.sem2.game.pokemon;

import java.util.ArrayList;
import java.util.Arrays;

public class Pokemon {

    private int maxHP;
    private String type;
    private int attack;
    private int currentHp;
    private String name;
    private int random;

    private Pokemon(String type, String name, int hp) {
        this.type = type;
        this.name = name;
        this.maxHP = hp;
        this.currentHp = hp;
        this.random = randomNumber(2);
    }

    public static Pokemon randomPokemon(String type) {

        ArrayList<String> pokemonTypes = new ArrayList<>(Arrays.asList("Water", "Rock", "Grass", "Fire", "Bug"));
        int random = randomNumber(2);
        if (pokemonTypes.contains(type)) {
            switch (type) {
                case "Water":

                    if (random == 1) {
                        Pokemon blastoise = new Pokemon("Water", "Blastoise", 150);
                        return blastoise;
                    }

                    else if (random == 2) {
                        Pokemon magikarp = new Pokemon("Water", "Magikarp", 25);
                        return magikarp;
                    }

                case "Fire":
                    if (random == 1) {
                        Pokemon charizard = new Pokemon("Fire", "Charizard", 150);
                        return charizard;
                    }

            }
        } else
            throw new IllegalArgumentException("Invalid type! Try again " + type);
        return null;

    }

    public static int randomNumber(int maxNumber) {
        return (int) (Math.random() * (maxNumber - 1));
    }

    private String getName() {
        return this.name;
    }

    private String getType() {
        return this.type;
    }

}
