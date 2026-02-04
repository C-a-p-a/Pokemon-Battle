# Pokemon Battle

A 1v1 Pokemon battle simulator with GUI, developed as a learning project for INF101 (Semester 2) at the University of Bergen. The project focuses on Object-Oriented Programming principles and Java development.

## Overview

This application simulates a turn-based Pokemon battle between a human player and an AI opponent. Players control Rayquaza while battling against an AI-controlled Giratina, complete with animated sprites, health bars, and a battle log.

## Features

- **Turn-based Combat System**: Strategic 1v1 Pokemon battles
- **Type Effectiveness**: Pokemon types interact with multipliers (2x super effective, 0.5x not very effective)
- **Interactive GUI**: Real-time battle visualization with animated GIF sprites
- **AI Opponent**: Simple AI that randomly selects attacks
- **Multiple Attack Types**: Each Pokemon has 4 unique attacks with different types and damage values
- **Battle Log**: Track all actions and damage dealt during the battle
- **Dynamic Health Bars**: Visual representation of remaining HP

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- Internet connection (required for loading sprites and background images)

## Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd Pokemon-Battle
```

2. Build the project using Maven:
```bash
mvn clean install
```

## Running the Game

### Using Maven:
```bash
mvn exec:java -Dexec.mainClass="no.uib.inf101.sem2.game.pokemon.Main"
```

### Using the JAR file:
```bash
java -jar target/INF101Sem2-1.0-SNAPSHOT.jar
```

### From IDE:
Run the [Main.java](src/main/java/no/uib/inf101/sem2/game/pokemon/main.java) class directly.

## How to Play

1. When the game starts, you'll see both Pokemon on screen with their health bars
2. Press keys **1-4** to select an attack:
   - Each number corresponds to one of your Pokemon's four attacks
   - Each attack has a different type (Water, Fire, Rock, Bug, etc.)
3. After you attack, the AI automatically takes its turn
4. The battle continues until one Pokemon reaches 0 HP
5. The winner is announced in the battle log

### Type Effectiveness System

- **Super Effective (2x damage)**: Water vs Fire, Fire vs Grass, etc.
- **Not Very Effective (0.5x damage)**: Fire vs Water, Grass vs Fire, etc.
- **Normal Effectiveness (1x damage)**: All other combinations

## Project Structure

```
src/
├── main/java/no/uib/inf101/sem2/game/pokemon/
│   ├── Main.java                 # Application entry point
│   ├── Battle.java              # Battle logic and turn management
│   ├── PokemonGUI.java          # GUI rendering and display
│   ├── Pokemon.java             # Pokemon entity class
│   ├── Attack.java              # Attack properties and behavior
│   ├── PokemonTypes.java        # Type definitions and effectiveness
│   ├── UserFighter.java         # Player-controlled fighter
│   ├── OpponentAI.java          # AI-controlled fighter
│   ├── IFighter.java            # Fighter interface
│   ├── AbstractFighter.java     # Base fighter implementation
│   └── KeyListener.java         # Keyboard input handling
└── test/java/no/uib/inf101/sem2/game/pokemon/
    ├── AttackTest.java          # Attack mechanics tests
    ├── BattleTest.java          # Battle logic tests
    ├── FighterInitTest.java     # Fighter initialization tests
    ├── PokemonHPTest.java       # HP calculation tests
    ├── PokemonTest.java         # Pokemon entity tests
    └── PokemonTypesTest.java    # Type effectiveness tests
```

## Testing

Run all tests using Maven:
```bash
mvn test
```

Run mutation testing with PIT:
```bash
mvn test pitest:mutationCoverage
```

## Technologies Used

- **Language**: Java 17
- **Build Tool**: Maven
- **GUI Framework**: Java Swing
- **Testing**: JUnit 5
- **Mutation Testing**: PIT (pitest-maven)

## Key OOP Concepts Demonstrated

- **Encapsulation**: Private fields with public getters/setters
- **Inheritance**: AbstractFighter class extended by UserFighter and OpponentAI
- **Polymorphism**: IFighter interface implemented by different fighter types
- **Abstraction**: Separation of battle logic, GUI, and fighter behavior

## Important Notes

### Internet Connection Required
The application loads sprites and background images from URLs. An active internet connection is required for proper GUI rendering.

### External Resources
This project uses GIF sprites and background images sourced from the internet for educational purposes only. All rights belong to their respective owners. This is a non-commercial learning project. See [PokemonGUI.java](src/main/java/no/uib/inf101/sem2/game/pokemon/PokemonGUI.java) for specific attributions.

## Video Demonstration

Watch the project demonstration on YouTube:

[Pokemon Battle Demo](https://youtu.be/nOXgQaRhUc8)

## Customization

You can easily customize the Pokemon and their attributes by modifying [Main.java](src/main/java/no/uib/inf101/sem2/game/pokemon/main.java):

- Change Pokemon names, types, and stats
- Create new attacks with different damage and types
- Adjust HP values and other battle parameters

## Acknowledgments

- Inspired by Lab 2 from INF101, Spring 2025 at the University of Bergen
- Created as a learning project to practice Object-Oriented Programming principles
- Pokemon and related characters are property of Nintendo/Game Freak/The Pokemon Company

## License

This is an educational project created for learning purposes at the University of Bergen. Not intended for commercial use.
