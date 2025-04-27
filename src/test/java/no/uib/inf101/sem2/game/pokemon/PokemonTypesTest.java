package no.uib.inf101.sem2.game.pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PokemonTypesTest {

    private PokemonTypes ref;
    private Pokemon pikachu;

    @Test
    void testEffective() {

        assertEquals(2, PokemonTypes.FIRE.isEffectiveAgainst(PokemonTypes.GRASS));

        assertEquals(2, PokemonTypes.WATER.isEffectiveAgainst(PokemonTypes.FIRE));

        assertEquals(0.5, PokemonTypes.FIRE.isEffectiveAgainst(PokemonTypes.WATER));

        assertEquals(1, PokemonTypes.ROCK.isEffectiveAgainst(PokemonTypes.ROCK));

        // null test
        assertEquals(1, PokemonTypes.BUG.isEffectiveAgainst(null));

    }

}
