package no.uib.inf101.sem2.game.pokemon;

import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {

    private Battle battle;
    private UserFighter userFighter;
    int attackIndex = -1;

    /**
     * Listens for keyboard-inputs from the user during a Pokemon battle to choose
     * an attack.
     * Only listens to buttons 1-4.
     * 
     * After a button is pressed, the number is sent to Battle in order to handle
     * the attack.
     * 
     * 
     * @param battle
     * @param fighter
     */
    public KeyListener(Battle battle, UserFighter fighter) {
        this.battle = battle;
        this.userFighter = fighter;
    }

    /**
     * Ignore // not used
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * This method checks if the user/human presses a button (1-4)
     * Then, sets the attackIndex to a number 0-3 based on what button the user
     * pressed.
     * 
     * attackIndex is then sent to playerAttackInput which handles the number and
     * indexes through a list of available moves and gets the attack for the active
     * Pokemon.
     * 
     * 
     * @param key
     */
    @Override
    public void keyPressed(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_1) {
            attackIndex = 0;
        }
        if (key.getKeyCode() == KeyEvent.VK_2) {
            attackIndex = 1;
        }
        if (key.getKeyCode() == KeyEvent.VK_3) {
            attackIndex = 2;
        }
        if (key.getKeyCode() == KeyEvent.VK_4) {
            attackIndex = 3;
        }

        if (attackIndex != -1) {
            battle.playerAttackInput(attackIndex);
        }
        attackIndex = -1;

    }

    /**
     * Ignore // not used
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }

}
