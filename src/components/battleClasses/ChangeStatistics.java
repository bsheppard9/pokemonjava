package components.battleClasses;

import components.battleClasses.pokemon.Pokemon;

import java.util.Random;

public class ChangeStatistics {

    public static Byte[][] changeStats(Pokemon pokemon, Move move, Pokemon opposingPokemon,
                            Byte[] stats1, Byte[] stats2) {
        Byte[][] stats = new Byte[1][2];
        String name = move.getName();
        double rand;
        if (name.equals("Bubble")) {
            rand = new Random().nextDouble();
            if (rand <= 0.332) {
                if (stats2[3] > -6) {
                    System.out.println(opposingPokemon.getPokemonName() + "'s speed"
                            + " fell!");
                    int num = stats2[3] - 1;
                    stats2[3] = new Byte("" + num);
                    stats = new Byte[][]{stats1, stats2};
                }
            }
        } else if (name.equals("Bulk Up")) {
            if (stats1[0] < 6) {
                System.out.println(pokemon.getPokemonName() + "'s attack rose!");
                int num = stats1[0] + 1;
                stats1[0] = new Byte("" + num);
                stats = new Byte[][]{stats1, stats2};
            } else {
                System.out.println("Nothing happened!");
                stats = new Byte[][]{stats1, stats2};
            }
            if (stats1[1] < 6) {
                System.out.println(pokemon.getPokemonName() + "'s defense rose!");
                int num = stats1[1] + 1;
                stats1[1] = new Byte("" + num);
                stats = new Byte[][]{stats1, stats2};
            } else {
                System.out.println("Nothing happened!");
                stats = new Byte[][]{stats1, stats2};
            }
        } else if (name.equals("Charm")) {
            if (stats2[0] >= -4) {
                System.out.println(opposingPokemon.getPokemonName() + "'s attack"
                        + " greatly fell!");
                int num = stats2[0] - 2;
                stats2[0] = new Byte("" + num);
                stats = new Byte[][]{stats1, stats2};
            } else if (stats2[0] >= -5) {
                System.out.println(opposingPokemon.getPokemonName() + "'s attack"
                        + " fell!");
                int num = stats2[0] - 1;
                stats2[0] = new Byte("" + num);
                stats = new Byte[][]{stats1, stats2};
            } else {
                System.out.println("Nothing happened!");
                stats = new Byte[][]{stats1, stats2};
            }
        } else if (name.equals("Defense Curl")) {
            if (stats1[1] < 6) {
                System.out.println(pokemon.getPokemonName() + "'s defense rose!");
                int num = stats1[1] + 1;
                stats1[1] = new Byte("" + num);
                stats = new Byte[][]{stats1, stats2};
            } else {
                System.out.println("Nothing happened!");
                stats = new Byte[][]{stats1, stats2};
            }
        } else if (name.equals("Double Team")) {
            if (stats1[5] < 6) {
                System.out.println(pokemon.getPokemonName() + "'s evasiveness rose!");
                int num = stats1[5] + 1;
                stats1[5] = new Byte("" + num);
                stats = new Byte[][]{stats1, stats2};
            } else {
                System.out.println("Nothing happened!");
                stats = new Byte[][]{stats1, stats2};
            }
        } else if (name.equals("Growl")) {
            if (stats2[0] > -6) {
                System.out.println(opposingPokemon.getPokemonName() + "'s attack fell!");
                int num = stats2[0] - 1;
                stats2[0] = new Byte("" + num);
                stats = new Byte[][]{stats1, stats2};
            } else {
                System.out.println("Nothing happened!");
                stats = new Byte[][]{stats1, stats2};
            }
        } else if (name.equals("Growth")) { //Update later for GEN II and onward
            /*if (stats1[0] < 6) {
                System.out.println(pokemon.getPokemonName() + "'s attack rose!");
                stats1[0] += 1;
                stats = new byte[][]{stats1, stats2};
            } else {
                System.out.println(pokemon.getPokemonName() + "'s attack won't go higher.");
                stats = new byte[][]{stats1, stats2};
            }*/
            if (stats1[2] < 6) {
                System.out.println(pokemon.getPokemonName() + "'s special rose!");
                int num = stats1[2] + 1;
                stats1[2] = new Byte("" + num);
                stats = new Byte[][]{stats1, stats2};
            } else {
                System.out.println("Nothing happened!");
                stats = new Byte[][]{stats1, stats2};
            }
        } else if (name.equals("Harden")) {
            if (stats1[1] < 6) {
                System.out.println(pokemon.getPokemonName() + "'s defense rose!");
                int num = stats1[1] + 1;
                stats1[1] = new Byte("" + num);
                stats = new Byte[][]{stats1, stats2};
            } else {
                System.out.println("Nothing happened!");
                stats = new Byte[][]{stats1, stats2};
            }
        } else if (name.equals("Howl")) {
            if (stats1[0] < 6) {
                System.out.println(pokemon.getPokemonName() + "'s attack rose!");
                int num = stats1[0] + 1;
                stats1[0] = new Byte("" + num);
                stats = new Byte[][]{stats1, stats2};
            } else {
                System.out.println("Nothing happened!");
                stats = new Byte[][]{stats1, stats2};
            }
        } else if (name.equals("Leer")) {
            if (stats2[1] > -6) {
                System.out.println(opposingPokemon.getPokemonName() + "'s defense fell!");
                int num = stats2[1] - 1;
                stats2[1] = new Byte("" + num);
                stats = new Byte[][]{stats1, stats2};
            } else {
                System.out.println("Nothing happened!");
                stats = new Byte[][]{stats1, stats2};
            }
        } else if (name.equals("Meditate")) {
            if (stats1[0] < 6) {
                System.out.println(pokemon.getPokemonName() + "'s attack rose!");
                int num = stats1[0] + 1;
                stats1[0] = new Byte("" + num);
                stats = new Byte[][]{stats1, stats2};
            } else {
                System.out.println("Nothing happened!");
                stats = new Byte[][]{stats1, stats2};
            }
        } else if (name.equals("Rage")) {
            if (stats2[0] < 6) {
                System.out.println(opposingPokemon.getPokemonName() + "'s attack rose!");
                int num = stats2[0] + 1;
                stats2[0] = new Byte("" + num);
                stats = new Byte[][]{stats1, stats2};
            }/* else {
                System.out.println("Nothing happened!");
                stats = new Byte[][]{stats1, stats2};
            }*/
        } else if (name.equals("Sand Attack")) {
            if (stats2[4] > -6) {
                System.out.println(opposingPokemon.getPokemonName() + "'s accuracy fell!");
                int num = stats2[4] - 1;
                stats2[4] = new Byte("" + num);
                stats = new Byte[][]{stats1, stats2};
            } else {
                System.out.println("Nothing happened!");
                stats = new Byte[][]{stats1, stats2};
            }
        } else if (name.equals("Smokescreen")) {
            if (stats2[4] > -6) {
                System.out.println(opposingPokemon.getPokemonName() + "'s accuracy fell!");
                int num = stats2[4] - 1;
                stats2[4] = new Byte("" + num);
                stats = new Byte[][]{stats1, stats2};
            } else {
                System.out.println("Nothing happened!");
                stats = new Byte[][]{stats1, stats2};
            }
        } else if (name.equals("String Shot")) {
            if (stats2[3] > -6) {
                System.out.println(opposingPokemon.getPokemonName() + "'s speed fell!");
                int num = stats2[3] - 1;
                stats2[3] = new Byte("" + num);
                stats = new Byte[][]{stats1, stats2};
            } else {
                System.out.println("Nothing happened!");
                stats = new Byte[][]{stats1, stats2};
            }
        } else if (name.equals("Sweet Scent")) {
            if (stats1[5] < 6) {
                System.out.println(pokemon.getPokemonName() + "'s evasiveness rose!");
                int num = stats1[5] + 1;
                stats1[5] = new Byte("" + num);
                stats = new Byte[][]{stats1, stats2};
            } else {
                System.out.println("Nothing happened!");
                stats = new Byte[][]{stats1, stats2};
            }
        } else if (name.equals("Tail Whip")) {
            if (stats2[1] > -6) {
                System.out.println(opposingPokemon.getPokemonName() + "'s defense fell!");
                int num = stats2[1] - 1;
                stats2[1] = new Byte("" + num);
                stats = new Byte[][]{stats1, stats2};
            } else {
                System.out.println("Nothing happened!");
                stats = new Byte[][]{stats1, stats2};
            }
        } else if (name.equals("Withdraw")) {
            if (stats1[1] < 6) {
                System.out.println(pokemon.getPokemonName() + "'s defense rose!");
                int num = stats1[1] + 1;
                stats1[1] = new Byte("" + num);
                stats = new Byte[][]{stats1, stats2};
            } else {
                System.out.println("Nothing happened!");
                stats = new Byte[][]{stats1, stats2};
            }
        }
        return stats;
    }
}
