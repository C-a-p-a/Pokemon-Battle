package no.uib.inf101.sem2.game.pokemon;

import java.util.List;

public class Main {
    public static void main(String[] args) {

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

        Pokemon Giratina = new Pokemon("Giratina", PokemonTypes.ROCK, 150, 35, 35, 10, squirtleMoves);
        Pokemon Electrivire = new Pokemon("Electrivire", PokemonTypes.FIRE, 150, 40, 40, 10, bulbasaurMoves);

        UserFighter player = new UserFighter(Electrivire, "Trainers Pokemon");
        OpponentAI playerAi = new OpponentAI(Giratina, "AI Pokemon");

        Battle battle = new Battle(player, playerAi);
        battle.startBattle();
    }
}
