package no.uib.inf101.sem2.game.pokemon;

public enum PokemonTypes {
    FIRE, WATER, GRASS, NORMAL, ROCK, BUG;

    /**
     * Keeps track of what types are effective against each other.
     * 2 = VERY EFFECTIVE!
     * 1 = NORMAL
     * 0.5 = NOT VERY EFFECTIVE...
     * 
     * This function is used with attack(), in order to multiply the damage
     * according to the table of effectiveness.
     * If Water type attacks Fire, the damage is multiplied by 2x.
     * 
     * @param other
     * @return double efficiency-multiplier
     */
    public double isEffectiveAgainst(PokemonTypes other) {

        switch (this) {
            case FIRE:
                if (other == GRASS) {
                    return 2;
                }
                if (other == WATER) {
                    return 0.5;
                }
                if (other == GRASS) {
                    return 0.5;
                }
                if (other == ROCK) {
                    return 0.5;
                }
                if (other == BUG) {
                    return 2;
                } else
                    return 1;

            case WATER:
                if (other == FIRE) {
                    return 2;
                }
                if (other == WATER) {
                    return 0.5;
                }
                if (other == GRASS) {
                    return 0.5;
                }
                if (other == GRASS) {
                    return 2;
                } else
                    return 1;

            case GRASS:
                if (other == WATER) {
                    return 2;
                }
                if (other == FIRE) {
                    return 0.5;
                }
                if (other == GRASS) {
                    return 0.5;
                }
                if (other == ROCK) {
                    return 2;
                }
                if (other == BUG) {
                    return 0.5;
                } else
                    return 1;

            case ROCK:
                if (other == FIRE) {
                    return 2;
                }
                if (other == BUG) {
                    return 1;
                } else
                    return 1;

            case BUG:
                if (other == GRASS) {
                    return 2;
                }
                if (other == FIRE) {
                    return 0.5;
                }

            case NORMAL:
                return 1;

            default:
                return 1;
        }

    }
}
