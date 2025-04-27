package no.uib.inf101.sem2.game.pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FighterInitTest {

    private Pokemon pikachu;
    private Pokemon opponent;
    private Attack attack;
    private IFighter fighter;
    private AbstractFighter absFighter;
    private UserFighter userFighter;
    private OpponentAI aiOpponent;

    @BeforeEach
    void setUp() {
        Attack thunderbolt = new Attack("Thunderbolt", 50, PokemonTypes.ELECTRIC);
        List<Attack> pikachuMoves = List.of(thunderbolt);
        pikachu = new Pokemon("Pikachu", PokemonTypes.ELECTRIC, 100, 10, 10, 1, pikachuMoves);

        userFighter = new UserFighter(pikachu, "Trainer");
        opponent = new Pokemon("Bulbasaur", PokemonTypes.GRASS, 100, 10, 10, 2, pikachuMoves);
        aiOpponent = new OpponentAI(opponent, "AI");
        Battle battle = new Battle(aiOpponent, aiOpponent, null);
    }

    // check that constructor initializes values correctly
    @Test
    void getAttributes() {
        assertEquals("Trainer", userFighter.getName());
        assertEquals(pikachu, userFighter.getPokemon());

        assertEquals("AI", aiOpponent.getName());
        assertEquals(opponent, aiOpponent.getPokemon());

    }

}
