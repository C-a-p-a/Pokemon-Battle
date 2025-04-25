package no.uib.inf101.sem2.game.pokemon;

public abstract class AbstractFighter implements IFighter {

    protected String name;
    public Pokemon activePokemon;

    public AbstractFighter(Pokemon pokemon, String pokeName) {
        this.activePokemon = pokemon;
        this.name = pokeName;
    }

    public String getName() {
        return this.name;
    }

    public Pokemon getPokemon() {
        return activePokemon;
    }

    public abstract Attack chooseAttack(Battle battleContext);

}
