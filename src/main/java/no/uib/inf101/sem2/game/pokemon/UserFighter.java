package no.uib.inf101.sem2.game.pokemon;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserFighter extends AbstractFighter {

    private Integer nextAttackIndex = null;

    public UserFighter(Pokemon pokemon, String name) {
        super(pokemon, name);

    }

    public String getName() {
        return name;
    }

    public Pokemon getPokemon() {
        return super.getPokemon();
    }

    public Attack pickAttack(int number) {
        List<Attack> availableMoves = activePokemon.getMoves();

        return availableMoves.get(number - 1);
    }

    public Attack getAttackByIndex() {
        int index = -1;
        List<Attack> moves = getPokemon().getMoves();
        if (index >= 0 && index < moves.size()) {
            return moves.get(index);
        }
        return null;
    }

    public void registerChosenAttack(int chosenIndex) {
        int index = chosenIndex - 1;
        List<Attack> moves = getPokemon().getMoves();

        if (index >= 0 && index < moves.size()) {
            this.nextAttackIndex = index;
        } else
            System.out.println("Invalid choice of index");
    }

    public Attack chooseAttack(Battle battleContext) {
        System.out.println("Your turn, " + getName());

        List<Attack> moves = getPokemon().getMoves();
        if (moves.isEmpty()) {
            System.out.println("No attacks to choose from!");
            return null;
        }

        System.out.println("Choose an available attack. Press 1 - " + moves.size());
        for (int i = 0; i < moves.size(); i++) {
            System.out.println(" " + (i + 1) + ": " +
                    moves.get(i).getName());
        }
        System.out.println("waiting for keyboard input...");

        this.nextAttackIndex = null;

        while (this.nextAttackIndex == null) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        int chosenIndex = this.nextAttackIndex;
        this.nextAttackIndex = null;

        if (chosenIndex >= 0 && chosenIndex < moves.size()) {
            return moves.get(chosenIndex);
        } else {
            System.out.println("error. invalid index");
            return moves.get(0);
        }
    }

}
