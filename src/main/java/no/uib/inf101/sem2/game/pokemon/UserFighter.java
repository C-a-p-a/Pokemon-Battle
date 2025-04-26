package no.uib.inf101.sem2.game.pokemon;

import java.util.List;

public class UserFighter extends AbstractFighter {
    /**
     * Initializes the user-fighter.
     * What Pokemon the user-fighter should use, and what the User-Fighter should be
     * called.
     * 
     * @param pokemon
     * @param name
     */
    public UserFighter(Pokemon pokemon, String name) {
        super(pokemon, name);

    }

    /**
     * Returns the name of the trainer/user-fighter or the Pokemon, depending on
     * what context the method is called in.
     * 
     * @return name (string)
     */
    public String getName() {
        return super.name;
    }

    /**
     * Returns the current Pokemon the trainer uses.
     * 
     * @returnn Pokemon current pokemon.
     */
    public Pokemon getPokemon() {
        return super.getPokemon();
    }

    /**
     * METHOD IS NOT USED
     */
    @Override
    public Attack chooseAttack(Battle battleContext) {
        return null;
    }

}
