package no.uib.inf101.sem2.game.pokemon;

public class Pokemon {

    private int hp;
    private int type;
    private int attack;
    private int currentHp;

    private Pokemon(int type) {
        this.type = type;
    }

    public static Pokemon randomPokemon(int type) {
        return null;
    }

}
