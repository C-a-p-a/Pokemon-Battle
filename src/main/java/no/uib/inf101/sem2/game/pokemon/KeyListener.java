package no.uib.inf101.sem2.game.pokemon;

import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {

    private Battle battle;
    private UserFighter userFighter;
    int attackIndex = -1;

    public KeyListener(Battle battle, UserFighter fighter) {
        this.battle = battle;
        this.userFighter = fighter;
    }

    /**
     * Ignore
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

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
     * Ignore
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }

}
