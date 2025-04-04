package no.uib.inf101.sem2.game.pokemon;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class PokemonTest {

    @Test
    void randomTypeTest() {
        PokemonTypes rand = Pokemon.randomPokemonType();
        PokemonTypes rand1 = Pokemon.randomPokemonType();
        System.out.println(rand);
        System.out.println(rand1);
        assertNotEquals(rand.hashCode(), rand1.hashCode());

    }

    @Test
    void randomPokemonTest() {
        Pokemon poke1 = Pokemon.randomPokemon(Pokemon.randomPokemonType());
        Pokemon poke2 = Pokemon.randomPokemon(Pokemon.randomPokemonType());

        assertNotEquals(poke1, poke2);
    }

}
