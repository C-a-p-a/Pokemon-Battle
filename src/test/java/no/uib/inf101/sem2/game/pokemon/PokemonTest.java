package no.uib.inf101.sem2.game.pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PokemonTest {

    private Pokemon pikachu;
    Attack attack;

    @BeforeEach
    void setUp() {
        Attack thunderbolt = new Attack("Thunderbolt", 50, PokemonTypes.ELECTRIC);
        List<Attack> pikachuMoves = List.of(thunderbolt);
        pikachu = new Pokemon("Pikachu", PokemonTypes.ELECTRIC, 100, 10, 10, 1, pikachuMoves);

    }

    // check if constructor sets attributes correctly
    @Test
    void constructorTest() {
        assertEquals("Pikachu", pikachu.getName());
        assertEquals(50, pikachu.getMoves().get(0).getPower());
        assertEquals(PokemonTypes.ELECTRIC, pikachu.getType());
        assertEquals(pikachu.getMaxHP(), pikachu.getHP());

        assertNotNull(pikachu.getMoves());
    }

    @Test
    void takeDamageAndFaint() {

        // check that pokemon takes damage
        int originalHp = pikachu.getHP();
        pikachu.takeDamage(30);
        assertEquals(originalHp - 30, pikachu.getHP());

        // check if pokemon faints if hp=0
        pikachu.takeDamage(70);
        assertTrue(pikachu.hasFainted());

        // check for negative hp
        pikachu.takeDamage(10);
        assertEquals(pikachu.getHP(), 0);

        pikachu.restoreHP();
        assertEquals(pikachu.getMaxHP(), pikachu.getHP());

    }

}
