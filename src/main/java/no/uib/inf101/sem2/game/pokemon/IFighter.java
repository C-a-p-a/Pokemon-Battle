package no.uib.inf101.sem2.game.pokemon;

/**
 * Represents a fighter (Both AI and human) in a Pokemon battle.
 * Sets the most basic elements a fighter must be able to perform in the span of
 * a battle, and also getters to get information about the fighters
 * 
 * This interface is extended to UserFighter.java and OpponentAI.java
 */
public interface IFighter {

    /**
     * This method is only used in OpponentAI.java in order to select an attack to
     * perform.
     * 
     * This method will be combined with a random number method.
     * 
     * @param battleContext
     * @return Attack selected randomly
     */
    public Attack chooseAttack(Battle battleContext);

    /**
     * Returns the Pokemon-object which the current fighter uses in the battle.
     * 
     * @return the active Pokemon
     */
    public Pokemon getPokemon();

    /**
     * Returns the name of the active fighter (Trainer if human or "AI" if its the
     * computer)
     * 
     * @return the name of the fighter (String)
     */
    public String getName();

}
