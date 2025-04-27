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

        // Check that pokemon takes damage
        int originalHp = pikachu.getHP();
        pikachu.takeDamage(30);
        assertEquals(originalHp - 30, pikachu.getHP());

        // Check if pokemon faints if hp=0
        pikachu.takeDamage(70);
        assertTrue(pikachu.hasFainted());

        // Check for negative hp
        pikachu.takeDamage(10);
        assertEquals(pikachu.getHP(), 0);

        pikachu.restoreHP();
        assertEquals(pikachu.getMaxHP(), pikachu.getHP());

    }

    @Test
    void getAttackStatTest() {
        // Verify that the attack stat is correctly set by the constructor
        assertEquals(10, pikachu.getAttackStat(), "Attack stat should be 10");

        // Check that the attack stat is not null or invalid
        assertNotEquals(0, pikachu.getAttackStat(), "Attack stat should not be 0");
        assertTrue(pikachu.getAttackStat() > 0, "Attack stat should be greater than 0");
    }

    @Test
    void getDefenseStatTest() {
        // Verify that the defense stat is correct
        assertEquals(10, pikachu.defenseStat(), "Defense stat should be 10");

        // Make sure the defense stat is not null or invalid
        assertNotEquals(0, pikachu.defenseStat(), "Defense stat should not be 0");
        assertTrue(pikachu.defenseStat() > 0, "Defense stat should be greater than 0");
    }
}