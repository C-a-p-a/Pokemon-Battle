package no.uib.inf101.sem2.game.pokemon;

public interface IFighter {

    public int getHP();

    public String getType();

    public int getMaxHP();

    public String getAttack();

    public int takeDamage(int damage);

    public void attack(Fighter pokemon);

    public boolean isAlive();

}
