package no.uib.inf101.sem2.game.pokemon;

import java.util.List;
import javax.swing.SwingUtilities;

/**
 * Main-class to start the Pokemon battle, along with the GUI
 * This class is repsonsible for:
 * 1. Creating the essential objects (Pokemons, trainers, attacks)
 * 2. Connecting the GUI with the KeyListener
 * 3. Connecting the battle with the GUI
 * 4. Starting the battle itself.
 */
public class Main {
    public static void main(String[] args) {
        // SwingUtilities.invokeLater is used in order to secure that different parts of
        // the code dont crash with each other. Secures safe updates of the GUI
        SwingUtilities.invokeLater(() -> {

            // Define attacks
            Attack Tackle = new Attack("Tackle", 20, PokemonTypes.NORMAL);
            Attack Bubble = new Attack("Bubble", 40, PokemonTypes.WATER);
            Attack Incinerate = new Attack("Incinerate", 35, PokemonTypes.FIRE);
            Attack RockSlide = new Attack("Rock Slide", 30, PokemonTypes.ROCK);
            Attack BugBuzz = new Attack("Bug Buzz", 50, PokemonTypes.BUG);
            Attack SeedBomb = new Attack("Seed Bomb", 25, PokemonTypes.GRASS);

            // initialize Rayquaza's attacks and the Pokemon itself
            List<Attack> rayquazaMoves = List.of(Incinerate, Tackle, BugBuzz, RockSlide);
            Pokemon Rayquaza = new Pokemon("Rayquaza", PokemonTypes.ELECTRIC, 175, 35, 40, 5, rayquazaMoves);

            // Initialize Giratina's attacks and the Pokemon itself
            List<Attack> giratinaMoves = List.of(SeedBomb, Bubble, RockSlide, Tackle);
            Pokemon Giratina = new Pokemon("Giratina", PokemonTypes.ROCK, 185, 35, 40, 10, giratinaMoves);

            // Initialize the players and what Pokemon they should use.
            UserFighter player = new UserFighter(Rayquaza, "Trainers Pokemon");
            OpponentAI playerAi = new OpponentAI(Giratina, "AI Pokemon");

            PokemonGUI gui = new PokemonGUI();

            Battle battle = new Battle(player, playerAi, gui);

            KeyListener keyListener = new KeyListener(battle, player);

            gui.addKeyListener(keyListener);

            battle.startBattle();
        });
    }
}
