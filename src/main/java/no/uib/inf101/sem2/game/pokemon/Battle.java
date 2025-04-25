package no.uib.inf101.sem2.game.pokemon;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;

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
    private Timer aiActionTimer;
    private PokemonTypes types;
    private boolean actionInProgress = false;

    public Battle(IFighter player, IFighter opponent, PokemonGUI gui) {
        this.playerFighter = player;
        this.opponentFighter = opponent;
        this.gui = gui;

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
            actionInProgress = false;
            gui.displayPlayerOptions(currentPlayer.getName(), currentPlayer.getPokemon().getMoves());
        } else if (!currentTurn()) {
            prepareAiAttack();
        }
    }

    private void prepareAiAttack() {
        if (battleOver || currentTurn()) {
            return;
        }
        actionInProgress = true;

        final Attack chosenAttack;
        Pokemon attackerPokemon = currentPlayer.getPokemon();

        try {
            chosenAttack = currentPlayer.chooseAttack(this);
        } catch (Exception e) {
            gui.addMessage("error during AI attack");
            actionInProgress = false;
            switchTurn();
            runNextTurn();
            return;
        }
        gui.addMessage(currentPlayer.getName() + " is preparing an attack...");

        ActionListener executeAttack = event -> {
            executeAiAttack(chosenAttack, attackerPokemon);
        };
        int delayInMS = aiRandom.nextInt(1500) + 1500;
        aiActionTimer = new Timer(delayInMS, executeAttack);
        aiActionTimer.setRepeats(false);
        aiActionTimer.start();

    }

    private void executeAiAttack(Attack chosenAttack, Pokemon attackerPokemon) {
        if (battleOver) {
            actionInProgress = false;
            return;
        }
        Pokemon defenderPokemon = otherPlayer.getPokemon();

        gui.addMessage(attackerPokemon.getName() + " used " + chosenAttack.getName() + " against "
                + defenderPokemon.getName() + "! ");
        int damage = calculateDamage(chosenAttack, attackerPokemon, defenderPokemon);
        defenderPokemon.takeDamage(damage);

        gui.addMessage(defenderPokemon.getName() + " lost " + damage + " HP");
        updateGuiStatus();

        ActionListener afterAiAttack = event -> {
            checkWinCondition();
            if (!battleOver) {
                switchTurn();
                runNextTurn();
            } else {
                actionInProgress = false;
                gui.showWinner(generateWinnerMessage());
            }
        };
        Timer afterAiTimer = new Timer(2500, afterAiAttack);
        afterAiTimer.setRepeats(false);
        afterAiTimer.start();
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
            gui.addMessage("Wait for your turn");
            return;
        }

        if (actionInProgress) {
            gui.addMessage("Nice try! You have already attacked this turn.");
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
            actionInProgress = true;
            gui.addMessage(attackerPokemon.getName() + " used " + chosenAttack.getName() + " against "
                    + defenderPokemon.getName() + "! ");
            int damage = calculateDamage(chosenAttack, attackerPokemon, defenderPokemon);

            defenderPokemon.takeDamage(damage);
            gui.addMessage(defenderPokemon.getName() + " lost " + damage + " HP!");
            updateGuiStatus();

            ActionListener afterPlayerAttack = event -> {
                checkWinCondition();
                if (!battleOver) {
                    switchTurn();
                    runNextTurn();
                } else {
                    actionInProgress = false;
                    gui.showWinner(generateWinnerMessage());
                }
            };
            Timer afterPlayerTimer = new Timer(1750, afterPlayerAttack);
            afterPlayerTimer.setRepeats(false);
            afterPlayerTimer.start();

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
            return playerFighter.getName() + "'s " + playerFighter.getPokemon().getName() + " has won! ";
        }

        if (p1.hasFainted()) {
            return opponentFighter.getName() + "'s' " + opponentFighter.getPokemon().getName() + " has won! ";

        } else {
            return "Unknown winner - error";
        }

    }

}
