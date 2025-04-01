package no.uib.inf101.sem2.game.pokemon;

public class UserFighter extends AbstractFighter {

    private UserFighter(String type, int maxHP, String name, int attackDamage) {
        this.type = type;
        this.maxHP = maxHP;
        this.name = name;
        this.attackDamage = attackDamage;

    }

    @Override
    public void attack(Fighter pokemon) {
        pokemon.takeDamage(attackDamage);
    }

}
