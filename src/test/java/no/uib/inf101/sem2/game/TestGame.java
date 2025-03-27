package no.uib.inf101.sem2.game;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.game.games.ConnectFour;
import no.uib.inf101.sem2.game.games.TicTacToe;
import no.uib.inf101.sem2.player.Player;
import no.uib.inf101.sem2.player.ai.DumbPlayer;
import no.uib.inf101.sem2.terminal.TerminalGraphics;

class TestGame {

	@Test
	void testDumbPlayerCanPlay() {
		Player p1 = new DumbPlayer('X');
		Player p2 = new DumbPlayer('O');
		Game<?> game = new TicTacToe(new TerminalGraphics(), p1, p2);
		game.run();
		assertTrue(game.gameOver());

		game = new ConnectFour(new TerminalGraphics(), p1, p2);
		game.run();
		assertTrue(game.gameOver());
	}

}
