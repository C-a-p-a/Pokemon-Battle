package no.uib.inf101.sem2.game.pokemon;

public class Attack {
    String name;
    int power;
    PokemonTypes type;

    public Attack(String name, int power, PokemonTypes type) {
        this.name = name;
        this.power = power;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public int getPower() {
        return this.power;
    }

    public PokemonTypes getAttackType() {
        return this.type;
    }
}
