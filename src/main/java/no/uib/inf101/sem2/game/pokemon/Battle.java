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
    private boolean battleOver = false;
    private Pokemon player1;
    private Pokemon player2;
    private Random aiRandom = new Random();
    private PokemonGUI gui;
    private Timer aiActionTimer;
    private boolean actionInProgress = false;

    /**
     * Creates a new battle between two fighters and connects them to the GUI.
     * 
     * The Pokemon with the lowest cooldown gets to start attacking. If the cooldown
     * is equal, the user/player gets to start.
     * 
     * @param player
     * @param opponent
     * @param gui
     */
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

    /**
     * Checks if the battle is over, and if not - starts the battle between the two
     * fighters.
     * Updates the Gui status and runs the next turn
     **/
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

    /**
     * Switches who the currentPlayer is. currentPlayer is who gets to attack.
     * After an attack has been executed, this method is called in order to switch
     * turns.
     */
    public void switchTurn() {
        IFighter temporary = this.currentPlayer;
        this.currentPlayer = this.otherPlayer;
        this.otherPlayer = temporary;

    }

    /**
     * Estimates amount of damage an attack will do, based on how effective the
     * types are against each other.
     * 
     * @param attack   - attack used
     * @param attacker - the pokemon attacking
     * @param defender - the pokemon defending
     * @return - estimated damage (int)
     */
    public int calculateDamage(Attack attack, Pokemon attacker, Pokemon defender) {
        int power = attack.getPower();
        PokemonTypes attackType = attack.getAttackType();
        PokemonTypes defendType = defender.getType();

        double effective = 1.0;

        effective = attackType.isEffectiveAgainst(defendType);

        if (effective > 1.0) {
            gui.addMessage("The attack is SUPER effective");
        } else if (effective < 1.0) {
            gui.addMessage("The attack was not very effective...");
        }

        int estimatedDamage = (int) Math.round(power * effective);

        if (estimatedDamage < 0) {
            estimatedDamage = 0;
        }
        return estimatedDamage;

    }

    /**
     * Checks if any of the fighters/pokemon have fainted (0 HP).
     * If they have fainted, set battleOver to true.
     * 
     */
    public void checkWinCondition() {
        if (currentPlayer.getPokemon().hasFainted() || otherPlayer.getPokemon().hasFainted()) {
            battleOver = true;
        }
    }

    /**
     * Returns who's turn it is to attack.
     * 
     * @return the current attacking player
     */
    public IFighter getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Returns the defending player
     * 
     * @return the player currently defending an attack
     */
    public IFighter getOtherPlayer() {
        return this.otherPlayer;
    }

    /**
     * This method checks if it is the players/human's turn to attack or not.
     * 
     * @return boolean true if it is human's turn to attack - false if AI's turn
     */
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
