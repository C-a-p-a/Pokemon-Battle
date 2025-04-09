package no.uib.inf101.sem2.game.pokemon;

public abstract class AbstractFighter implements IFighter {

    /**
     * Took inspiration from W3Schools;
     * https://www.w3schools.com/java/java_abstract.asp
     * 01.04.2025
     * 
     * @param type
     * @param maxHP
     * @param name
     * @param attack
     */

    String name;
    protected Pokemon activePokemon;

    public AbstractFighter(Pokemon pokemon, String name) {
        this.activePokemon = pokemon;
        this.name = name;
    }

    public Pokemon getPokemon() {
        return activePokemon;
    }

    public abstract void chooseAttack();

}
