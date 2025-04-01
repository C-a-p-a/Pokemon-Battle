package no.uib.inf101.sem2.game.pokemon;

public enum PokemonTypes {
    FIRE, WATER, GRASS, NORMAL, ROCK, BUG;

    public boolean isEffectiveAgainst(PokemonTypes other) {

        switch (this) {
            case FIRE:
                return (other == GRASS);
            case WATER:
                return (other == FIRE);
            case GRASS:
                return (other == WATER);
            case ROCK:
                return (other == FIRE);
            case BUG:
                return (other == GRASS);
            default:
                return false;
        }

    }
}
