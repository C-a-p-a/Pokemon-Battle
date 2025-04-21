package no.uib.inf101.sem2.game.pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AttackTest {

    @Test
    void testAttackGetters() {

        String expectedName = "Quick Attack";
        int expectedPower = 40;
        PokemonTypes expectedType = PokemonTypes.NORMAL;
        Attack attack = new Attack(expectedName, expectedPower, expectedType);

        assertEquals(expectedName, attack.getName());
        assertEquals(expectedType, attack.getAttackType());
        assertEquals(expectedPower, attack.getPower());
    }
}
