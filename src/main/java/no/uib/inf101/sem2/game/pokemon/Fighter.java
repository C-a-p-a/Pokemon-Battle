package no.uib.inf101.sem2.game.pokemon;

public class Fighter extends AbstractFighter {

    String type;
    int maxHP;
    String name;
    int attackDamage;

    private Fighter(String type, int maxHP, String name, int attackDamage) {
        this.type = type;
        this.maxHP = maxHP;
        this.name = name;
        this.attackDamage = attackDamage;

    }

    private static Pokemon randomFighter() {
        Pokemon fighter = Pokemon.randomPokemon("Fire");
        return fighter;
    }

    @Override
    public void attack(Fighter pokemon) {
        pokemon.takeDamage(attackDamage);
    }
}
