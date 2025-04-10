package no.uib.inf101.sem2.game.pokemon;

import java.util.List;

public class OpponentAI extends AbstractFighter {

    private Pokemon pokemon;
    private String name;

    public OpponentAI(Pokemon pokemon, String name) {
        super(pokemon, name);
        this.pokemon = pokemon;
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Attack chooseAttack(Battle battleContext) {
        List<Attack> aiMoves = activePokemon.getMoves();
        int maxNumberChoice = activePokemon.getMoves().size();

        return aiMoves.get(randomNumber(maxNumberChoice));

    }

    public static int randomNumber(int maxNumber) {
        return (int) (Math.random() * (maxNumber - 1));
    }
}
