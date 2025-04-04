package no.uib.inf101.sem2.game.pokemon;

public abstract class AbstractFighter implements IFighter {

    /**
     * Took inspiration from W3Schools;
     * https://www.w3schools.com/java/java_abstract.asp
     * 01.04.2025
     * 
     * @param type
     * @param maxHP
     * @param name
     * @param attack
     */

    int maxHP;
    String type;
    int currentHP;
    String name;
    int attackDamage;

    public int getHP() {
        return this.currentHP;
    }

    public String getType() {
        return this.type;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getAttack() {
        return this.attackDamage;
    }

    public boolean isAlive() {
        if (currentHP >= 0) {
            return false;
        }
        return true;
    }

    public int takeDamage(int damage) {
        return this.currentHP -= damage;
    }

    public void attack(Fighter pokemon) {
        pokemon.takeDamage(attackDamage);
    }

}
