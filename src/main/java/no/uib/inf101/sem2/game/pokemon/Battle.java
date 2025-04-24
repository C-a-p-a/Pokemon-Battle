package no.uib.inf101.sem2.game.pokemon;

import java.util.List;
import java.util.Random;

public class Battle {

    private IFighter playerFighter;
    private IFighter opponentFighter;
    private IFighter currentPlayer;
    private IFighter otherPlayer;
    private UserFighter userFighter;
    private boolean battleOver = false;
    private Pokemon player1;
    private Pokemon player2;
    private Random aiRandom = new Random();
    private PokemonGUI gui;

    public Battle(IFighter player, IFighter opponent, PokemonGUI gui) {
        this.playerFighter = player;
        this.opponentFighter = opponent;
        this.gui = gui;

        if (this.gui == null) {
            throw new IllegalArgumentException("PokemonGui cannot be null");
        }

        player1 = playerFighter.getPokemon();
        player2 = opponentFighter.getPokemon();

        int p1Cooldown = player1.getCooldown();
        int p2Cooldown = player2.getCooldown();

        if (p1Cooldown >= p2Cooldown) {
            this.currentPlayer = this.playerFighter;
            this.otherPlayer = opponentFighter;
        } else
            this.currentPlayer = this.opponentFighter;
        this.otherPlayer = this.playerFighter;

        if (currentPlayer == playerFighter) {
            this.otherPlayer = opponentFighter;
        } else
            this.otherPlayer = playerFighter;

        this.battleOver = false;

    }

    public void startBattle() {
        if (battleOver) {
            gui.addMessage("==== COULD NOT START BATTLE. ====");

        }
        gui.addMessage("\n ===== BATTLE STARTING! =====");
        updateGuiStatus();
        runNextTurn();
    }

    private void runNextTurn() {
        if (battleOver) {
            return;
        }

        updateGuiStatus();

        if (currentTurn()) {
            gui.displayPlayerOptions(currentPlayer.getName(), currentPlayer.getPokemon().getMoves());
        } else if (!currentTurn()) {
            executeAiTurn();
        }
    }

    private void executeAiTurn() {
        if (battleOver || currentTurn()) {
            return;
        }

        gui.addMessage("\n" + currentPlayer.getName() + "'s turn");
        Attack chosenAttack = null;
        Pokemon attackerPokemon = currentPlayer.getPokemon();
        Pokemon defenderPokemon = otherPlayer.getPokemon();

        try {
            chosenAttack = currentPlayer.chooseAttack(this);

            if (!currentTurn()) {
                if (chosenAttack != null) {
                    int delayInMS = aiRandom.nextInt(1510) + 1000;
                    System.out.println(currentPlayer.getName() + " preparing attack...");
                    Thread.sleep(delayInMS);
                }
            }
        } catch (Exception e) {
            chosenAttack = null;
        }

        if (chosenAttack != null) {
            gui.addMessage(attackerPokemon.getName() + " used " + chosenAttack.getName() + " against "
                    + defenderPokemon.getName() + "!");

            int damage = calculateDamage(chosenAttack, attackerPokemon, defenderPokemon);

            defenderPokemon.takeDamage(damage);

            gui.addMessage(defenderPokemon.getName() + " took " + damage + "HP!");
        } else {
            gui.addMessage(currentPlayer.getName() + " chose not to attack!");
        }

        checkWinCondition();

        if (!battleOver) {
            switchTurn();
            runNextTurn();

        } else {
            gui.showWinner(generateWinnerMessage());
        }
    }

    /**
     * This method will be called by KeyListener when the player presses a button
     * (1-4).
     * The method will execute the chosen attack, and then execute the AI's attack.
     * 
     * @param attackIndex
     */

    public void playerAttackInput(int attackIndex) {
        if (battleOver || !currentTurn()) {
            System.out.println("It's not your turn yet!");
            return;
        }

        Pokemon attackerPokemon = playerFighter.getPokemon();
        Pokemon defenderPokemon = opponentFighter.getPokemon();

        Attack chosenAttack = null;

        List<Attack> moves = attackerPokemon.getMoves();
        if (attackIndex >= 0 && attackIndex < moves.size()) {
            chosenAttack = moves.get(attackIndex);
        }

        if (chosenAttack != null) {
            gui.addMessage(attackerPokemon.getName() + " used " + chosenAttack.getName() + " against "
                    + defenderPokemon.getName() + "! ");
            int damage = calculateDamage(chosenAttack, attackerPokemon, defenderPokemon);
            defenderPokemon.takeDamage(damage);
            gui.addMessage(defenderPokemon.getName() + " took " + damage + " HP!");

            checkWinCondition();
            if (!battleOver) {
                switchTurn();
                runNextTurn();
            } else {
                updateGuiStatus();
                gui.showWinner(generateWinnerMessage());
            }
        } else {
            gui.addMessage("Invalid attack choice! Please choose again!");
            gui.displayPlayerOptions(currentPlayer.getName(), attackerPokemon.getMoves());
        }

    }

    public void switchTurn() {
        IFighter temporary = this.currentPlayer;
        this.currentPlayer = this.otherPlayer;
        this.otherPlayer = temporary;

    }

    public int calculateDamage(Attack attack, Pokemon attacker, Pokemon defender) {
        return attack.getPower();

    }

    public void checkWinCondition() {
        if (currentPlayer.getPokemon().hasFainted() || otherPlayer.getPokemon().hasFainted()) {
            battleOver = true;
        }
    }

    public IFighter getCurrentPlayer() {
        return this.currentPlayer;
    }

    public IFighter getOtherPlayer() {
        return this.otherPlayer;
    }

    public boolean currentTurn() {
        if (currentPlayer == playerFighter) {
            return true;
        } else {
            return false;
        }
    }

    private void updateGuiStatus() {
        Pokemon p1 = playerFighter.getPokemon();
        Pokemon p2 = opponentFighter.getPokemon();

        gui.updateStatus(p1.getName(), p1.getHP(), p1.getMaxHP(), p2.getName(), p2.getHP(), p2.getMaxHP());
    }

    private String generateWinnerMessage() {
        Pokemon p1 = playerFighter.getPokemon();
        Pokemon p2 = opponentFighter.getPokemon();

        if (p2.hasFainted()) {
            return playerFighter.getName() + " and " + playerFighter.getPokemon().getName() + " has won! ";
        }

        if (p1.hasFainted()) {
            return opponentFighter.getName() + " and " + opponentFighter.getPokemon().getName() + " has won! ";

        } else {
            return "Unknown winner - error";
        }

    }

}
