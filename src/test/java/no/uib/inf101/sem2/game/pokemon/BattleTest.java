package no.uib.inf101.sem2.game.pokemon;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BattleTest {

    private IFighter player1;
    private IFighter player2;
    private Pokemon Testomon;
    private Pokemon otherTestomon;
    private Battle battle;

    @BeforeEach
    void setUp() {

        Testomon = new Pokemon("Testomon", PokemonTypes.NORMAL, 150, 50, 50, 5, Collections.emptyList());
        otherTestomon = new Pokemon("otherTestomon", PokemonTypes.NORMAL, 150, 50, 50, 10, Collections.emptyList());

        player1 = new OpponentAI(Testomon, "Test1");
        player2 = new OpponentAI(otherTestomon, "Test2");

        IFighter original = player1;
        IFighter other = player2;

        battle = new Battle(player1, player2);

    }

    /**
     * Tests if the current player and the other player is the same.
     */
    @Test
    void currentAndOtherPlayer() {

        assertNotEquals(battle.getCurrentPlayer(), battle.getOtherPlayer());

    }

    /**
     * Tests wether an attack returns the expected amount of damage
     */
    @Test
    void calculateDamageReturnsPower() {

        Attack testAttack = new Attack("Power punch", 45, PokemonTypes.WATER);

        int expectedDamage = 45;

        int actualDamage = battle.calculateDamage(testAttack, otherTestomon, Testomon);

        assertEquals(expectedDamage, actualDamage);
    }
}
