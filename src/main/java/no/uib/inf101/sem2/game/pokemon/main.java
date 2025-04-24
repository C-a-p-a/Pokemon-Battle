package no.uib.inf101.sem2.game.pokemon;

import java.util.List;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Attack Tackle = new Attack("Tackle", 20, PokemonTypes.NORMAL);
            Attack Bubble = new Attack("Bubble", 40, PokemonTypes.WATER);
            Attack Incinerate = new Attack("Incinerate", 35, PokemonTypes.FIRE);
            Attack RockSlide = new Attack("Rock Slide", 30, PokemonTypes.ROCK);
            Attack BugBuzz = new Attack("Bug Buzz", 50, PokemonTypes.BUG);
            Attack SeedBomb = new Attack("Seed Bomb", 25, PokemonTypes.GRASS);

            List<Attack> electrivireMoves = List.of(Incinerate, Tackle, BugBuzz, RockSlide);
            Pokemon Electrivire = new Pokemon("Electrivire", PokemonTypes.ELECTRIC, 175, 35, 40, 5, electrivireMoves);

            List<Attack> giratinaMoves = List.of(SeedBomb, Bubble, RockSlide, Tackle);
            Pokemon Giratina = new Pokemon("Giratina", PokemonTypes.ROCK, 185, 35, 40, 10, giratinaMoves);

            UserFighter player = new UserFighter(Electrivire, "Trainers Pokemon");
            OpponentAI playerAi = new OpponentAI(Giratina, "AI Pokemon");

            PokemonGUI gui = new PokemonGUI();

            Battle battle = new Battle(player, playerAi, gui);

            KeyListener keyListener = new KeyListener(battle, player);

            gui.addKeyListener(keyListener);

            battle.startBattle();
        });
    }
}
