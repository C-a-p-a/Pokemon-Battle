package no.uib.inf101.sem2.game.pokemon;

import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {

    private Battle battle;
    private UserFighter userFighter;

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
            if (battle.currentTurn()) {
                userFighter.pickAttack(1);
                return;
            }
        }
        if (key.getKeyCode() == KeyEvent.VK_2) {
            if (battle.currentTurn()) {
                userFighter.pickAttack(2);
                return;
            }
        }
        if (key.getKeyCode() == KeyEvent.VK_3) {
            if (battle.currentTurn()) {
                userFighter.pickAttack(3);
                return;
            }
        }
        if (key.getKeyCode() == KeyEvent.VK_4) {
            if (battle.currentTurn()) {
                userFighter.pickAttack(4);
                return;
            }
        }
    }

    /**
     * Ignore
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }

}
