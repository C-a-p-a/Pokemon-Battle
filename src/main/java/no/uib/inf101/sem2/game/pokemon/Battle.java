package no.uib.inf101.sem2.game.pokemon;

import java.util.List;

public class Battle {

    private IFighter playerFighter;
    private IFighter opponentFighter;
    private IFighter currentPlayer;
    private IFighter otherPlayer;
    private boolean battleOver = false;
    private Pokemon player1;
    private Pokemon player2;

    public Battle(IFighter player, IFighter opponent) {
        this.playerFighter = player;
        this.opponentFighter = opponent;

        player1 = playerFighter.getPokemon();
        player2 = opponentFighter.getPokemon();
        int p1Cooldown = player1.getCooldown();
        int p2Cooldown = player2.getCooldown();

        if (p1Cooldown >= p2Cooldown) {
            this.currentPlayer = this.playerFighter;
        } else
            this.currentPlayer = this.opponentFighter;

        if (currentPlayer == playerFighter) {
            this.otherPlayer = opponentFighter;
        } else
            this.otherPlayer = playerFighter;

        this.battleOver = false;

    }

    public void setupPokemon() {
        Attack Tackle = new Attack("Tackle", 20, PokemonTypes.NORMAL);
        Attack Bubble = new Attack("Bubble", 40, PokemonTypes.WATER);
        Attack BugBuzz = new Attack("Bug Buzz", 50, PokemonTypes.BUG);
        Attack Incinerate = new Attack("Incinerate", 35, PokemonTypes.FIRE);
        Attack SeedBomb = new Attack("Seed Bomb", 25, PokemonTypes.GRASS);
        Attack RockSlide = new Attack("Rock Slide", 30, PokemonTypes.ROCK);

        List<Attack> bulbasaurMoves = List.of(SeedBomb, Tackle);
        Pokemon Bulbasaur = new Pokemon("Bulbasaur", PokemonTypes.GRASS, 110, 40, 40, 10, bulbasaurMoves);

        List<Attack> squirtleMoves = List.of(Bubble, Tackle);
        Pokemon Squirtle = new Pokemon("Squirtle", PokemonTypes.WATER, 120, 35, 50, 10, squirtleMoves);
    }

    public void startBattle() {
        while (!battleOver) {
            displayStatus();

            if (currentPlayer == playerFighter) {
                otherPlayer = opponentFighter;
            } else
                otherPlayer = playerFighter;

            executeTurn();
            checkWinCondition();

        }
        displayWinner();
    }

    public void executeTurn() {
        if (battleOver) {
            return;
        }
        Attack chosenAttack = currentPlayer.chooseAttack(this);

        if (chosenAttack != null) {
            int damage = calculateDamage(chosenAttack, currentPlayer.getPokemon(), otherPlayer.getPokemon());

            otherPlayer.getPokemon().takeDamage(damage);
            System.out.println(currentPlayer.getName() + " used " + chosenAttack + " on " + otherPlayer.getName()
                    + " took " + damage + " amount of damage!" + otherPlayer.getName()
                    + " is now left with " + otherPlayer.getPokemon().getHP() + "/"
                    + otherPlayer.getPokemon().getMaxHP());

            checkWinCondition();
            switchTurn();
        } else
            throw new NullPointerException("Chosen attack cannot be null");
    }

    public void switchTurn() {
        if (this.currentPlayer == this.playerFighter) {
            this.currentPlayer = this.opponentFighter;
        } else {
            this.currentPlayer = this.playerFighter;
        }

    }

    public int calculateDamage(Attack attack, Pokemon attacker, Pokemon defender) {
        return attack.getPower();

    }

    public void checkWinCondition() {
        if (currentPlayer.getPokemon().hasFainted() || otherPlayer.getPokemon().hasFainted()) {
            battleOver = true;
        }
    }

    public void printBattleStatus() {
        if (battleOver) {
            System.out.println("The battle is over!");
        } else {
            System.out.println("The battle is still ongoing!");
        }
    }

    public void displayWinner() {
        if (otherPlayer.getPokemon().hasFainted()) {
            System.out.println(currentPlayer.getName() + "has won!");
        } else if (currentPlayer.getPokemon().hasFainted()) {
            System.out.println(otherPlayer.getName() + "has won!");
        } else
            System.out.println("No winner yet!");
    }

    public void displayStatus() {
        System.out.println(currentPlayer.getName() + " vs " + otherPlayer.getName());
        System.out.println(currentPlayer.getName() + " is currently on " + currentPlayer.getPokemon().getHP() + "/"
                + currentPlayer.getPokemon().getMaxHP() + ".");
        System.out.println(otherPlayer.getName() + " is currently on " + otherPlayer.getPokemon().getHP() + "/"
                + otherPlayer.getPokemon().getMaxHP() + ".");
    }

}
