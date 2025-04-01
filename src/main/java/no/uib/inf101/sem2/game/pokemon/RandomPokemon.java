package no.uib.inf101.sem2.game.pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomPokemon {

    /**
     * Took inspiration from:
     * Coderanch.com - 31.03
     * https://coderanch.com/t/579785/java/Iterate-HashMap-type-HashMap-String
     * 
     * @return
     */
    public String randomPokemon() {
        ArrayList<String> rockPokemon = new ArrayList<>(Arrays.asList("Cubone", "Dugtrio"));
        ArrayList<String> bugPokemon = new ArrayList<>(Arrays.asList("Scyther", "Victreebell"));
        ArrayList<String> waterPokemon = new ArrayList<>(Arrays.asList("Magikarp"));
        Map<String, List<String>> pokemonMap = new HashMap<String, List<String>>();
        pokemonMap.put("Rock", rockPokemon);
        pokemonMap.put("Bug", bugPokemon);
        pokemonMap.put("Water", waterPokemon);

        for (String type : pokemonMap.keySet()) {
            for (String value : pokemonMap.get(type)) {
                return value;
            }
        }

        return "Iteration failed. Please check RandomPokemon.java";
    }

}
