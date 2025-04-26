package no.uib.inf101.sem2.game.pokemon;

public class Attack {
    String name;
    int power;
    PokemonTypes type;

    /**
     * Recieves parameters name,power,type.
     * This defines what kind of attack a pokemon should have and what attributes
     * the attack has.
     * 
     * @param name
     * @param power
     * @param type
     */
    public Attack(String name, int power, PokemonTypes type) {
        this.name = name;
        this.power = power;
        this.type = type;
    }

    /**
     * Returns the attack name recieved in the constructor
     * 
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the attacking power of the Pokemon
     * 
     * @return power (int)
     */
    public int getPower() {
        return this.power;
    }

    /**
     * Returns the type of the attack. Is later used to calculate the efficiency of
     * the attack against its opponent.
     * 
     * @return PokemonTypes attack type
     */
    public PokemonTypes getAttackType() {
        return this.type;
    }

}
