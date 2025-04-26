package no.uib.inf101.sem2.game.pokemon;

import java.util.List;

public class OpponentAI extends AbstractFighter {
    /**
     * Defines the opponent/AI Pokemon and the name for the fighter/user.
     * Uses AbstractFighters pokemon and name field
     * 
     * @param pokemon
     * @param name
     */
    public OpponentAI(Pokemon pokemon, String name) {
        super(pokemon, name);
    }

    /**
     * Returns the name of the current fighter (in this case, AI)
     * 
     * @return name of the fighter (string)
     */
    @Override
    public String getName() {
        return super.getName();
    }

    /**
     * This method selects what attack the AI should do. Based on the List aiMoves
     * which contains the available moves for the AI, the method also calls
     * randomNumber() in order to get a random number.
     * This random number is then used to index through the list and select an
     * attack at randomNumber index.
     * 
     * @return an attack based on a random index in the list
     */
    @Override
    public Attack chooseAttack(Battle battleContext) {
        List<Attack> aiMoves = activePokemon.getMoves();
        int maxNumberChoice = activePokemon.getMoves().size();

        return aiMoves.get(randomNumber(maxNumberChoice - 1));

    }

    /**
     * Returns a random number in the range 0-maxNumber
     * 
     * This method is used in chooseAttack() in order to select a random attack.
     * 
     * @param maxNumber
     * @return
     */
    public static int randomNumber(int maxNumber) {
        return (int) (Math.random() * (maxNumber));
    }
}
