package no.uib.inf101.sem2.game.pokemon;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserFighter extends AbstractFighter {

    private Scanner inputScanner;

    public UserFighter(Pokemon pokemon, String name) {
        super(pokemon, name);
        this.inputScanner = new Scanner(System.in);

    }

    public String getName() {
        return name;
    }

    public Pokemon getPokemon() {
        return super.getPokemon();
    }

    @Override
    public Attack chooseAttack(Battle battleContext) {
        System.out.println("Choose an attack for " + activePokemon.getName());

        List<Attack> availableMoves = activePokemon.getMoves();

        for (int i = 0; i < availableMoves.size(); i++) {
            System.out.println((i + 1) + ": " + availableMoves.get(i).getName());
        }

        int attackChoice = readUserInput(availableMoves.size());

        if (attackChoice >= 1 && attackChoice <= availableMoves.size()) {
            return availableMoves.get(attackChoice);
        } else {
            System.out
                    .println("Oh no! Invalid choice! " + activePokemon.getName() + "uses the first available attack.");
            return availableMoves.get(attackChoice);
        }
    }

    /**
     * Artificial Intelligence assisted me in creating this method, because I'm not
     * very familiar
     * with using Scanner.
     * I made sure to thoroughly read the code and understand it, and why
     * the method works.
     * 
     * This method reads user input in order to select an attack that the Pokemon
     * should do.
     * The user selects from a list of available attacks the Pokemon can do.
     * 
     * The int the user provides, is then stored in order to index through a list at
     * a later time. For example, if the user types '1', they get the first
     * attack/element in the list. If the user provides an invalid integer or a
     * differernt type (for example a string), they get an InputMismatchException
     * along with an explanation.
     * 
     * @param maxChoice
     * @return
     */
    private int readUserInput(int maxChoice) {
        int choice = -1;

        while (choice < 1 || choice > maxChoice) {
            System.out.print("Enter a choice between 1 - " + maxChoice);
            System.out.println();

            try {
                String line = inputScanner.nextLine();

                choice = Integer.parseInt(line);

                if (choice < 1 || choice > maxChoice) {
                    System.out.println("Invalid choice. Pick a number between 1 and " + maxChoice);
                    System.out.println();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid. Please enter a number (int) between 1 and " + maxChoice);
                System.out.println();
                inputScanner.next();
                choice = -1;
            }
        }
        return choice - 1;
    }

    public Attack pickAttack(int number) {
        List<Attack> availableMoves = activePokemon.getMoves();

        return availableMoves.get(number);
    }

}
