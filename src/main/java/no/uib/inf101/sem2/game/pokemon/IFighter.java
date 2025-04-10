package no.uib.inf101.sem2.game.pokemon;

public interface IFighter {

    public Attack chooseAttack(Battle battleContext);

    public Pokemon getPokemon();

    public String getName();

}
