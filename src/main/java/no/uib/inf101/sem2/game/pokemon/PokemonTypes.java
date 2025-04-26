package no.uib.inf101.sem2.game.pokemon;

public enum PokemonTypes {
    FIRE, WATER, GRASS, NORMAL, ROCK, BUG, ELECTRIC;

    /**
     * Calculates the efficiency of 'this' attacktype, vs 'other' / defenders type
     * If the attack is efficient, the method returns a double.
     * This double is then multiplied with the original attack damage.
     * 
     * Super efficient attack = 2.0 * originaldamage.
     *
     * @param other Defenders PokemonType
     * @return Efficiency (double)
     */
    public double isEffectiveAgainst(PokemonTypes other) {
        switch (this) {
            case FIRE:
                if (other == GRASS || other == BUG || other == ROCK)
                    return 2.0;
                if (other == WATER || other == FIRE)
                    return 0.5;
                return 1.0;

            case WATER:
                if (other == FIRE || other == ROCK)
                    return 2.0;
                if (other == WATER || other == GRASS)
                    return 0.5;
                return 1.0;

            case GRASS:
                if (other == WATER || other == ROCK)
                    return 2.0;
                if (other == FIRE || other == GRASS || other == BUG)
                    return 0.5;
                return 1.0;

            case ELECTRIC:
                if (other == WATER)
                    return 2.0;
                if (other == GRASS || other == ELECTRIC || other == ROCK)
                    return 0.5;
                return 1.0;

            case ROCK:
                if (other == FIRE || other == BUG)
                    return 2.0;
                if (other == WATER || other == GRASS)
                    return 0.5;

                return 1.0;

            case BUG:
                if (other == GRASS)
                    return 2.0;

                if (other == FIRE || other == ROCK)
                    return 0.5;

                return 1.0;

            case NORMAL:

                if (other == ROCK)
                    return 0.5;

                return 1.0;

            default:
                return 1.0;
        }
    }
}