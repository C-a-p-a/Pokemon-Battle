package no.uib.inf101.sem2.game.pokemon;

import java.util.List;

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

        Attack chosen = aiMoves.get(maxNumberChoice - 1);

        if (chosen != null) {
            try {
                int delayInMS = randomNumber(2000) + 3000;
                System.out.println(getName() + " gjør klart angripet sitt... ");
                Thread.sleep(delayInMS);
            } catch (InterruptedException e) {
                System.out.println("The AI got disturbed while preparing its attack!");
                Thread.currentThread().interrupt();

            }

        }

        return aiMoves.get(randomNumber(maxNumberChoice - 1));

    }

    public static int randomNumber(int maxNumber) {
        return (int) (Math.random() * (maxNumber));
    }
}
