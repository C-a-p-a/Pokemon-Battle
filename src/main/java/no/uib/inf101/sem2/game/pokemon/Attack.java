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

    /**
     * Returns the name used in the constructor
     * 
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the Power of the Pokemon
     * 
     * @return
     */
    public int getPower() {
        return this.power;
    }

    /**
     * Returns the type of the attack. Is later used to calculate the efficiency of
     * the attack against its opponent.
     * 
     * @return
     */
    public PokemonTypes getAttackType() {
        return this.type;
    }

}
