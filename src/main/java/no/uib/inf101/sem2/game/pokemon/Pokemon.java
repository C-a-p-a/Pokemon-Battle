package no.uib.inf101.sem2.game.pokemon;

import java.util.List;
import java.util.Random;

public class Pokemon {

    private PokemonTypes type1;
    private PokemonTypes type2;

    private int maxHP;
    private int currentHp;

    private int attackStat;
    private int defenseStat;
    private int cooldown;

    private List<Attack> moves;
    private String name;

    /**
     * Creates a new Pokemon object with given attributes, names and attacks. By
     * default, the currentHP is set to maxHP
     * 
     * @param name        of pokemon
     * @param type1       PokemonTypes of pokemon
     * @param maxHP       maximum HP of pokemon
     * @param attackStat  not in use at the moment
     * @param defenseStat not in use at the moment
     * @param cooldown    the Pokemon with the lowest cooldown gets to start the
     *                    battle.
     * @param moves       List of available attacks a Pokemon has.
     */
    public Pokemon(String name, PokemonTypes type1, int maxHP, int attackStat, int defenseStat, int cooldown,
            List<Attack> moves) {
        this.name = name;
        this.type1 = type1;
        this.maxHP = maxHP;
        this.currentHp = maxHP;
        this.attackStat = attackStat;
        this.defenseStat = defenseStat;
        this.cooldown = cooldown;
        this.moves = moves;
    }

    /**
     * Returns the name of the trainer (human or AI), or name of the Pokemon.
     * Depending on
     * what context the method is called
     * 
     * @return name of Pokemon/trainer (String)
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return the PokemonTypes type of a Pokemon. Is later used to determine the
     * efficiency of attacks on different Pokemon.
     * If Attack type is very effective against a certain PokemonTypes, the attack
     * damage will be doubled. If the attack is not very effective, the damage will
     * be halved (-0.5x)
     * 
     * @return PokemonTypes type of the current Pokemon
     */
    public PokemonTypes getType() {
        return this.type1;
    }

    /**
     * Max HP is the maximum amount of HP a Pokemon has, and starts a battle with. *
     * 
     * @return maximum amount of HP (int)
     */
    public int getMaxHP() {
        return this.maxHP;
    }

    /**
     * Returns the current HP a Pokemon has. current HP will be reduced after being
     * attacked.
     * 
     * @return current HP (int)
     */
    public int getHP() {
        return this.currentHp;
    }

    /**
     * Attack stat is similar to defense stat, but with attacks. A large Pokemon
     * will deal more damage than a small Pokemon despite them performing the same
     * attack.
     * 
     * Attack stat is not currently in use, I will not remove it in case I want to
     * expand the game and add more features in the future.
     * 
     * @return
     */
    public int getAttackStat() {
        return this.attackStat;
    }

    /**
     * Defense stat is not in use, but the original plan was to determine how well a
     * Pokemon recieves an attack. For example, a small Pokemon has a lower defense
     * stat than a huge Pokemon, and therefore the small Pokemon will recieve more
     * damage from the same attack.
     * 
     * I won't remove the method/attribute in case I want to expand the game at a
     * later stage
     * 
     * @return
     */
    public int defenseStat() {
        return this.defenseStat;
    }

    /**
     * Returns the cooldown of a Pokemon.
     * Cooldown is used to determine who gets to attack first. The Pokemon with the
     * lowest cooldown gets to start.
     * 
     * @return cooldown
     */
    public int getCooldown() {
        return this.cooldown;
    }

    /**
     * Returns a list of available Attacks /moves a Pokemon has.
     * 
     * @return List<Attack> of available attacks a Pokemon has
     */
    public List<Attack> getMoves() {
        return this.moves;
    }

    /**
     * Took inspiration from a Stack Overflow thread;
     * https://stackoverflow.com/questions/1972392/pick-a-random-value-from-an-enum
     * Timestamp - 4.4.2025 @ 12:40
     * 
     * This method is not used at the moment, but I kept it in case I want to expand
     * the game later, where you can assign the attackers random Pokemon based on a
     * random Pokemon Type.
     * 
     * @return random PokemonTypes type
     */
    public static PokemonTypes randomPokemonType() {
        return PokemonTypes.values()[new Random().nextInt(PokemonTypes.values().length)];

    }

    /**
     * Subtracts the attack damage from the current hp.
     * If the attack int is greater than current HP int, check if Pokemon has
     * fainted.
     * 
     * @param damage
     */
    public void takeDamage(int damage) {
        if (currentHp < damage) {
            currentHp = currentHp - currentHp;
            hasFainted();
        } else {
            currentHp -= damage;
        }
    }

    /**
     * Checks if a Pokemon has HP 0 or below. If true, Pokemon has fainted and the
     * method returns True.
     * 
     * 
     * @return true if HP below/equal to 0, false if above 0
     */
    public boolean hasFainted() {
        if (currentHp <= 0) {
            return true;
        }
        return false;
    }

    /**
     * This method is not in use, but I kept it in case I wanted to add a "restart
     * battle" button, where I could restart the stats and battle.
     * 
     * Sets the currentHP to the maxHP
     */
    public void restoreHP() {
        currentHp = maxHP;
    }

    /**
     * Returns a random number in range 0-maxNumber
     * 
     * @param maxNumber
     * @return random int in range 0-maxNumber
     */
    public static int randomNumber(int maxNumber) {
        return (int) (Math.random() * (maxNumber - 1));
    }

}
