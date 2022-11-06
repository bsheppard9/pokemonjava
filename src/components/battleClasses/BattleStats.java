package components.battleClasses;


import components.Battle;
import components.battleClasses.pokemon.Pokemon;

import java.util.HashMap;
import java.util.Map;

import static components.Battle.foePokemon;
import static components.Battle.leadPokemon;
import static components.Battle.pokemonOpponent;

public class BattleStats {

    public static HashMap<Pokemon, Byte[]> stats;
    public static Map<Pokemon, Boolean[]> ongoingMove;

    /*
    Moves in list:

    0 - Bide
    1 - Counter
    2 - Dig
    3 - Hyper Beam
    4 - Fly
    5 - Focus Energy
    6 - Light Screen
    7 - Razor Wind
    8 - Reflect
    9 - Rest
    10 - Skull Bash
    11 - Sky Attack
    12 - Solarbeam

    This works for moves operating in a set number of turns
     */

    public static void setStats() {
        stats = new HashMap<>();
        ongoingMove = new HashMap<>();
        for (Pokemon pokemon : Battle.pokemonParty) {
            if (pokemon != null) {
                Byte[] battlestats = new Byte[] {0, 0, 0, 0, 0, 0};
                pokemon.setStats(battlestats[0], battlestats[1], battlestats[2],
                        battlestats[3], battlestats[4], battlestats[5]);
                stats.put(pokemon, battlestats);
                Boolean[] usedMove = new Boolean[13];
                for (int i = 0; i < 13; i++) {
                    usedMove[i] = false;
                }
                ongoingMove.put(pokemon, usedMove);
            }
        }
        for (Pokemon pokemon : pokemonOpponent) {
            if (pokemon != null) {
                Byte[] battlestats = new Byte[] {0, 0, 0, 0, 0, 0};
                pokemon.setStats(battlestats[0], battlestats[1], battlestats[2],
                        battlestats[3], battlestats[4], battlestats[5]);
                stats.put(pokemon, battlestats);
                Boolean[] usedMove = new Boolean[13];
                for (int i = 0; i < 13; i++) {
                    usedMove[i] = false;
                }
                ongoingMove.put(pokemon, usedMove);
            }
        }

    }

    //For attack, defense, special, and speed

    public static int getStats(Pokemon pokemon, int stat) {
        double factor = 1.0;
        double statistic = -1;
        if (stats.get(pokemon)[stat] >= 0) {
            if (stat == 0) {
                statistic = ((100 / 100.0) + ((stats.get(pokemon)[stat] * 50)
                        / 100.0));
                statistic *= pokemon.getStatistics().getAttack();
            } else if (stat == 1) {
                statistic = ((100 / 100.0) + ((stats.get(pokemon)[stat] * 50)
                        / 100.0));
                statistic *= pokemon.getStatistics().getDefense();
            } else if (stat == 2) {
                statistic = ((100 / 100.0) + ((stats.get(pokemon)[stat] * 50)
                        / 100.0));
                statistic *= pokemon.getStatistics().getSpecial();
            } else if (stat == 3) {
                statistic = ((100 / 100.0) + ((stats.get(pokemon)[stat] * 50)
                        / 100.0));
                statistic *= pokemon.getStatistics().getSpeed();
            }
        } else {
            if (stat == 0) {
                statistic =  (2.0 / (Math.abs(stats.get(pokemon)[stat]) + 2.0));
                statistic *= pokemon.getStatistics().getAttack();
            } else if (stat == 1) {
                statistic =  (2.0 / (Math.abs(stats.get(pokemon)[stat]) + 2.0));
                statistic *= pokemon.getStatistics().getDefense();
            } else if (stat == 2) {
                statistic =  (2.0 / (Math.abs(stats.get(pokemon)[stat]) + 2.0));
                statistic *= pokemon.getStatistics().getSpecial();
            } else if (stat == 3) {
                statistic =  (2.0 / (Math.abs(stats.get(pokemon)[stat]) + 2.0));
                statistic *= pokemon.getStatistics().getSpeed();
            }
        }

        if (pokemon.getStatistics().getIsBurned() && stat == 0) {
            factor = 0.5;
        } else if (pokemon.getStatistics().getIsParalyzed() && stat == 3) {
            factor = 0.25;
        }
        return (int) (statistic * factor);
    }

    //For accuracy and evasiveness
    public static double getAccStats(Pokemon pokemon, int stat) {
        double statistic = -1.0;
        if (stats.get(pokemon)[stat] >= 0) {
            if (stat == 4) {
                statistic = ((Math.abs(stats.get(pokemon)[stat]) + 3.0)
                        / 3.0);
            } else if (stat == 5) {
                statistic = (3.0 / (stats.get(pokemon)[stat] + 3.0));
            }
        } else {
            if (stat == 4) {
                statistic = (3.0 / ((Math.abs(stats.get(pokemon)[stat])
                        + 3.0)));
            } else if (stat == 5) {
                statistic = ((3.0 + Math.abs(stats.get(pokemon)[stat]))
                        / 3.0);
            }
        }
        return statistic;
    }

}
