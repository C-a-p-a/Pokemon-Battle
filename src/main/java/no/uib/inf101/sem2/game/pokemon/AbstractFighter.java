package no.uib.inf101.sem2.game.pokemon;

public abstract class AbstractFighter implements IFighter {

    protected String name;
    public Pokemon activePokemon;

    public AbstractFighter(Pokemon pokemon, String pokeName) {
        this.activePokemon = pokemon;
        this.name = pokeName;
    }

    /**
     * Returns the name of Trainer or Pokemon, depending on what context
     * 
     * @return name (string)
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the currently active Pokemon
     * 
     * @return
     */
    public Pokemon getPokemon() {
        return activePokemon;
    }

    /**
     * This method is used in order to select what attack to execute.
     * OpponentAI.java uses this method
     * 
     * @param battleContext
     * @return a selected attack
     */
    public abstract Attack chooseAttack(Battle battleContext);

}
