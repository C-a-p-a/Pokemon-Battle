package no.uib.inf101.sem2.game.pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import org.junit.jupiter.api.Test;

public class PokemonHPTest {
    /***
     * @Test
     *       Tests that when a Pokemon takes damage, it loses the same amount of HP
     */
    @Test
    void testTakeDamageReducesHP() {
        Pokemon testPokemon = new Pokemon("Testimon", PokemonTypes.WATER, 100, 50, 50, 0, Collections.emptyList());

        int originalHP = testPokemon.getHP();
        int maxHP = testPokemon.getMaxHP();

        int damage = 30;

        testPokemon.takeDamage(damage);

        assertEquals(originalHP - damage, testPokemon.getHP());
        assertFalse(testPokemon.hasFainted());
    }

    /**
     * @Test
     *       If a pokemon reaches 0 HP, it should faint
     */
    @Test
    void takeDamageUntilFaint() {
        Pokemon testPokemon = new Pokemon("Testimon", PokemonTypes.WATER, 100, 50, 50, 0, Collections.emptyList());

        int originalHP = testPokemon.getHP();

        int damage = 200;

        testPokemon.takeDamage(damage);

        assertTrue(testPokemon.hasFainted());
        assertEquals(0, testPokemon.getHP());
    }

}
