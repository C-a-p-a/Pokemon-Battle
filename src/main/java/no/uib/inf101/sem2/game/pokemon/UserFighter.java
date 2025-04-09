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
    public Attack chooseAttack(Battle battleContext) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'chooseAttack'");
    }

}
