package no.uib.inf101.sem2.game.pokemon;

public class Fighter extends AbstractFighter {

    String type;
    int maxHP;
    int currentHP;
    String name;
    int attackDamage;

    private Fighter(String type, int maxHP, String name, int attackDamage) {
        this.type = type;
        this.maxHP = maxHP;
        this.name = name;
        this.attackDamage = attackDamage;

    }

    @Override
    public int getHP() {
        return currentHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return this.type;
    }

    private static Pokemon randomFighter() {
        Pokemon fighter = Pokemon.randomPokemon(PokemonTypes.FIRE);
        return fighter;
    }

    @Override
    public void attack(Fighter pokemon) {
        pokemon.takeDamage(attackDamage);
    }
}
