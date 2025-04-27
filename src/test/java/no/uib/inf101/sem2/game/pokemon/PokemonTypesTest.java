
package no.uib.inf101.sem2.game.pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PokemonTypesTest {

    @Test
    void testFireTypeEffectiveness() {
        assertEquals(2.0, PokemonTypes.FIRE.isEffectiveAgainst(PokemonTypes.GRASS));
        assertEquals(2.0, PokemonTypes.FIRE.isEffectiveAgainst(PokemonTypes.BUG));
        assertEquals(2.0, PokemonTypes.FIRE.isEffectiveAgainst(PokemonTypes.ROCK));
        assertEquals(0.5, PokemonTypes.FIRE.isEffectiveAgainst(PokemonTypes.WATER));
        assertEquals(0.5, PokemonTypes.FIRE.isEffectiveAgainst(PokemonTypes.FIRE));
        assertEquals(1.0, PokemonTypes.FIRE.isEffectiveAgainst(PokemonTypes.NORMAL));
    }

    @Test
    void testWaterTypeEffectiveness() {
        assertEquals(2.0, PokemonTypes.WATER.isEffectiveAgainst(PokemonTypes.FIRE));
        assertEquals(2.0, PokemonTypes.WATER.isEffectiveAgainst(PokemonTypes.ROCK));
        assertEquals(0.5, PokemonTypes.WATER.isEffectiveAgainst(PokemonTypes.WATER));
        assertEquals(0.5, PokemonTypes.WATER.isEffectiveAgainst(PokemonTypes.GRASS));
        assertEquals(1.0, PokemonTypes.WATER.isEffectiveAgainst(PokemonTypes.NORMAL));
    }

    @Test
    void testGrassTypeEffectiveness() {
        assertEquals(2.0, PokemonTypes.GRASS.isEffectiveAgainst(PokemonTypes.WATER));
        assertEquals(2.0, PokemonTypes.GRASS.isEffectiveAgainst(PokemonTypes.ROCK));
        assertEquals(0.5, PokemonTypes.GRASS.isEffectiveAgainst(PokemonTypes.FIRE));
        assertEquals(0.5, PokemonTypes.GRASS.isEffectiveAgainst(PokemonTypes.GRASS));
        assertEquals(0.5, PokemonTypes.GRASS.isEffectiveAgainst(PokemonTypes.BUG));
        assertEquals(1.0, PokemonTypes.GRASS.isEffectiveAgainst(PokemonTypes.NORMAL));
    }

    @Test
    void testElectricTypeEffectiveness() {
        assertEquals(2.0, PokemonTypes.ELECTRIC.isEffectiveAgainst(PokemonTypes.WATER));
        assertEquals(0.5, PokemonTypes.ELECTRIC.isEffectiveAgainst(PokemonTypes.GRASS));
        assertEquals(0.5, PokemonTypes.ELECTRIC.isEffectiveAgainst(PokemonTypes.ELECTRIC));
        assertEquals(0.5, PokemonTypes.ELECTRIC.isEffectiveAgainst(PokemonTypes.ROCK));
        assertEquals(1.0, PokemonTypes.ELECTRIC.isEffectiveAgainst(PokemonTypes.NORMAL));
    }

    @Test
    void testRockTypeEffectiveness() {
        assertEquals(2.0, PokemonTypes.ROCK.isEffectiveAgainst(PokemonTypes.FIRE));
        assertEquals(2.0, PokemonTypes.ROCK.isEffectiveAgainst(PokemonTypes.BUG));
        assertEquals(0.5, PokemonTypes.ROCK.isEffectiveAgainst(PokemonTypes.WATER));
        assertEquals(0.5, PokemonTypes.ROCK.isEffectiveAgainst(PokemonTypes.GRASS));
        assertEquals(1.0, PokemonTypes.ROCK.isEffectiveAgainst(PokemonTypes.ROCK));
    }

    @Test
    void testBugTypeEffectiveness() {
        assertEquals(2.0, PokemonTypes.BUG.isEffectiveAgainst(PokemonTypes.GRASS));
        assertEquals(0.5, PokemonTypes.BUG.isEffectiveAgainst(PokemonTypes.FIRE));
        assertEquals(0.5, PokemonTypes.BUG.isEffectiveAgainst(PokemonTypes.ROCK));
        assertEquals(1.0, PokemonTypes.BUG.isEffectiveAgainst(PokemonTypes.NORMAL));
    }

    @Test
    void testNormalTypeEffectiveness() {
        assertEquals(0.5, PokemonTypes.NORMAL.isEffectiveAgainst(PokemonTypes.ROCK));
        assertEquals(1.0, PokemonTypes.NORMAL.isEffectiveAgainst(PokemonTypes.FIRE));
        assertEquals(1.0, PokemonTypes.NORMAL.isEffectiveAgainst(PokemonTypes.WATER));
        assertEquals(1.0, PokemonTypes.NORMAL.isEffectiveAgainst(PokemonTypes.GRASS));
    }

    @Test
    void testNullTypeEffectiveness() {
        assertEquals(1.0, PokemonTypes.BUG.isEffectiveAgainst(null));
        assertEquals(1.0, PokemonTypes.FIRE.isEffectiveAgainst(null));
        assertEquals(1.0, PokemonTypes.WATER.isEffectiveAgainst(null));
    }

}
