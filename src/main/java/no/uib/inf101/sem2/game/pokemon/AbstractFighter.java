package no.uib.inf101.sem2.game.pokemon;

public abstract class AbstractFighter implements IFighter {

    /**
     * Took inspiration from W3Schools;
     * https://www.w3schools.com/java/java_abstract.asp
     * 01.04.2025
     * 
     * @param pokemon
     * @param name
     */

    private String name;
    public Pokemon activePokemon;

    public AbstractFighter(Pokemon pokemon, String pokeName) {
        this.activePokemon = pokemon;
        this.name = pokeName;
    }

    public Pokemon getPokemon() {
        return activePokemon;
    }

    public abstract Attack chooseAttack(Battle battleContext);

}
