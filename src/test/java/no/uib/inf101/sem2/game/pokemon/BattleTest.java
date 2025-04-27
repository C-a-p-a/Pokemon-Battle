package no.uib.inf101.sem2.game.pokemon;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BattleTest {

    private Pokemon playerPokemon;
    private Pokemon opponentPokemon;
    private Pokemon thirdPokemon;
    private UserFighter playerFighter;
    private OpponentAI opponentFighterAI;
    private OpponentAI thirdFighter;
    private Attack playerAttack;
    private Attack opponentAttack;
    private PokemonGUI gui;
    private Battle battle;
    private Battle battle2;

    @BeforeEach
    void setUp() {
        playerAttack = new Attack("Punch", 40, PokemonTypes.NORMAL);
        opponentAttack = new Attack("Bite", 30, PokemonTypes.NORMAL);

        playerPokemon = new Pokemon("Pikachu", PokemonTypes.ELECTRIC, 100, 10, 10, 1, List.of(playerAttack));
        opponentPokemon = new Pokemon("Bulbasaur", PokemonTypes.GRASS, 100, 10, 10, 2, List.of(opponentAttack));

        playerPokemon.restoreHP();
        opponentPokemon.restoreHP();

        playerFighter = new UserFighter(playerPokemon, "Trainer");
        opponentFighterAI = new OpponentAI(opponentPokemon, "AI");

        battle = new Battle(playerFighter, opponentFighterAI, null);

    }

    /**
     * Tests if the current player and the other player is the same.
     */
    @Test
    void currentAndOtherPlayer() {
        assertNotEquals(battle.getCurrentPlayer(), battle.getOtherPlayer());

        battle.switchTurn();

        assertNotEquals(battle.getCurrentPlayer(), battle.getOtherPlayer());

    }

    /**
     * Tests wether an attack returns the expected amount of damage
     */
    @Test
    void calculateDamageReturnsPower() {

        Attack testAttack = new Attack("Power punch", 45, PokemonTypes.WATER);

        int expectedDamage = 40;

        int actualDamage = battle.calculateDamage(playerAttack, playerPokemon, opponentPokemon);

        assertEquals(expectedDamage, actualDamage);
    }

    @Test
    void faintAfterAttacked() {
        Attack powerful = new Attack("Ultra attack", 250, PokemonTypes.NORMAL);

        int opponentHP = opponentPokemon.getHP();
        // check that the attack should be enough to faint the opponent
        assertTrue(powerful.getPower() > opponentPokemon.getHP());

        opponentPokemon.takeDamage(powerful.getPower());

        assertTrue(opponentPokemon.hasFainted());
        assertEquals(0, opponentPokemon.getHP());
    }

    @Test
    void battleInitializationTest() {
        // Tests that the battle initializes correctly with the player and opponent
        assertEquals(playerFighter, battle.getCurrentPlayer());
        assertEquals(opponentFighterAI, battle.getOtherPlayer());
        assertFalse(battle.battleOver);
    }

    @Test
    void battleWithLowerCooldownTest() {
        // Test that the player with the lower cooldown starts
        playerPokemon = new Pokemon("Charmander", PokemonTypes.FIRE, 100, 10, 10, 1, List.of(playerAttack));
        opponentPokemon = new Pokemon("Squirtle", PokemonTypes.WATER, 100, 10, 10, 2, List.of(opponentAttack));

        playerFighter = new UserFighter(playerPokemon, "Trainer");
        opponentFighterAI = new OpponentAI(opponentPokemon, "AI");

        battle = new Battle(playerFighter, opponentFighterAI, gui);

        assertEquals(playerFighter, battle.getCurrentPlayer());
        assertEquals(opponentFighterAI, battle.getOtherPlayer());
    }

    @Test
    void battleWithEqualCooldownTest() {
        // Test that the player starts when cooldowns are equal
        playerPokemon = new Pokemon("Eevee", PokemonTypes.NORMAL, 100, 10, 10, 2, List.of(playerAttack));
        opponentPokemon = new Pokemon("Meowth", PokemonTypes.NORMAL, 100, 10, 10, 2, List.of(opponentAttack));

        playerFighter = new UserFighter(playerPokemon, "Trainer");
        opponentFighterAI = new OpponentAI(opponentPokemon, "AI");

        battle = new Battle(playerFighter, opponentFighterAI, gui);

        assertEquals(playerFighter, battle.getCurrentPlayer());
        assertEquals(opponentFighterAI, battle.getOtherPlayer());
    }

    @Test
    void battleWithHigherCooldownTest() {
        // Test that the opponent starts when their cooldown is lower
        playerPokemon = new Pokemon("Pidgey", PokemonTypes.WATER, 100, 10, 10, 3, List.of(playerAttack));
        opponentPokemon = new Pokemon("Rattata", PokemonTypes.NORMAL, 100, 10, 10, 1, List.of(opponentAttack));

        playerFighter = new UserFighter(playerPokemon, "Trainer");
        opponentFighterAI = new OpponentAI(opponentPokemon, "AI");

        battle = new Battle(playerFighter, opponentFighterAI, gui);

        assertEquals(opponentFighterAI, battle.getCurrentPlayer());
        assertEquals(playerFighter, battle.getOtherPlayer());
    }

    @Test
    void checkWinConditionPlayerFaints() {
        // Simulate player fainting
        playerPokemon.takeDamage(playerPokemon.getHP());
        assertTrue(playerPokemon.hasFainted());

        battle.checkWinCondition();

        assertTrue(battle.battleOver);
    }

    @Test
    void checkWinConditionOpponentFaints() {
        // Simulate opponent fainting
        opponentPokemon.takeDamage(opponentPokemon.getHP());
        assertTrue(opponentPokemon.hasFainted());

        battle.checkWinCondition();

        assertTrue(battle.battleOver);
    }

    @Test
    void checkWinConditionNoFaint() {
        // Make sure no one has fainted
        assertFalse(playerPokemon.hasFainted());
        assertFalse(opponentPokemon.hasFainted());

        battle.checkWinCondition();

        assertFalse(battle.battleOver);
    }

    @Test
    void currentTurnPlayerTurnTest() {
        // Make sure its the player's turn initially
        assertTrue(battle.currentTurn());
    }

    @Test
    void currentTurnOpponentTurnTest() {
        // Switch turn to opponent and make sure it's no longer the player's turn
        battle.switchTurn();
        assertFalse(battle.currentTurn());
    }

}
