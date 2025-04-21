package no.uib.inf101.sem2.game.pokemon;

import java.util.List;
import java.util.Random;

public class OpponentAI extends AbstractFighter {

    public OpponentAI(Pokemon pokemon, String name) {
        super(pokemon, name);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public Attack chooseAttack(Battle battleContext) {
        List<Attack> aiMoves = activePokemon.getMoves();
        int maxNumberChoice = activePokemon.getMoves().size();

        return aiMoves.get(randomNumber(maxNumberChoice));

    }

    public static int randomNumber(int maxNumber) {
        return (int) (Math.random() * (maxNumber));
    }
}
