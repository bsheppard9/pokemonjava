package components;

import components.battleClasses.*;
import components.battleClasses.pokemon.Pokemon;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static components.Battle.*;
import static components.battleClasses.BattleStats.getAccStats;

/*
Moves to test:
Reflect
Light Screen
 */


public class Attack {

    static Map<Pokemon, Move> lastMoveUsed = new HashMap<>();
    public static int damage;
    static Map<Move, Boolean[]> turnPassed = new HashMap<>();
    private static int boundCounter;

    public static Pokemon[] attack(Pokemon pokemon, Pokemon opposingPokemon, int index) {
        Move move;
        /*
        NOTE:
        The pokemon's nickname is on the first line
        the attack is on the second line
        */

        if (usingMove.containsValue(Move.FIRE_SPIN)) {
            if (usingMove.get(pokemon) != null) {
                if (usingMove.get(pokemon).getName().equals("Fire Spin")) {
                    if (opposingPokemon.equals(foePokemon)) {
                        System.out.print("Enemy ");
                    }
                    System.out.println(pokemon.getPokemonName() + "'s attack continues!");
                    boundCounter--;
                    if (boundCounter == 0) {
                        usingMove.remove(pokemon, Move.FIRE_SPIN);
                        speedOrder.remove(opposingPokemon);
                    }
                    opposingPokemon.getStatistics().setCurrentHealthPoints(damage);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {}
                    if (opposingPokemon.getStatistics().getIsFainted()) {
                        if (opposingPokemon.equals(foePokemon)) {
                            System.out.print("Enemy ");
                        }
                        System.out.println(opposingPokemon.getPokemonName()
                                + " fainted!");
                        if (usingMove.containsValue(Move.RAGE)) {
                            if (usingMove.get(opposingPokemon) != null) {
                                if (usingMove.get(opposingPokemon).getName().equals("Rage")) {
                                    usingMove.remove(opposingPokemon, Move.RAGE);
                                }
                            }
                        }
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {}
                    }
                    return new Pokemon[] {pokemon, opposingPokemon};
                }
            } else if(usingMove.get(opposingPokemon) != null) {
                if(usingMove.get(opposingPokemon).getName().equals("Fire Spin")) {
                    return new Pokemon[] {pokemon, opposingPokemon};
                }
            } else if(usingMove.get(pokemon) == null && usingMove.get(opposingPokemon) == null) {
                usingMove.remove(Move.FIRE_SPIN);
                return new Pokemon[] {pokemon, opposingPokemon};
            }
        }

        if (pokemon.getStatistics().getIsConfused()) {
            /*if (pokemon.equals(foePokemon)) {
                System.out.print("Enemy ");
            }
            System.out.println(pokemon.getPokemonName() + "'s confused!");
            int rand = new Random().nextInt(100) + 1;
            if (rand <= 50) {
                System.out.println("It hurt itself in its confusion!");
                return new Pokemon[] {pokemon, opposingPokemon};
            }//NEED TO ACCOUNT FOR NUMBER OF TURNS*/
        } else if (pokemon.getStatistics().getIsFlinching()) {
            if (pokemon.equals(foePokemon)) {
                System.out.print("Enemy ");
            }
            System.out.println(pokemon.getPokemonName() + " flinched!");
            pokemon.getStatistics().setIsFlinching(false);
            return new Pokemon[] {pokemon, opposingPokemon};
        } else if (pokemon.getStatistics().getIsFrozen()) {
            if (pokemon.equals(foePokemon)) {
                System.out.print("Enemy ");
            }
            System.out.println(pokemon.getPokemonName() + "'s frozen solid!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {}
            return new Pokemon[] {pokemon, opposingPokemon};
        } else if (pokemon.getStatistics().getIsParalyzed()) {
            int rand = new Random().nextInt(100) + 1;
            if (rand <= 25) {
                if (pokemon.equals(foePokemon)) {
                    System.out.print("Enemy ");
                }
                System.out.println(pokemon.getPokemonName() + "'s paralyzed!"
                        + " It can't move!");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {}
                return new Pokemon[] {pokemon, opposingPokemon};
            }
        }
        if (index == -1) {
            move = Move.STRUGGLE; //THIS NEEDS TO BE TESTED
        } else {
            move = pokemon.getMoveset()[index];
        }
        damage = 0;
        boolean used = false;
        if (move.getEffect().equals("Two-Turn")) {
            if (!turnPassed.containsKey(move)) {
                turnPassed.put(move, new Boolean[]{true, false});
                usingMove.put(pokemon, move);
            } else {
                used = turnPassed.get(move)[0];
                turnPassed.replace(move, new Boolean[]{true, true});
                usingMove.remove(pokemon, move);
            }
            boolean moveCompleted = MoveEffects.twoTurnEffects(pokemon, move,
                    used);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {}
            if (!moveCompleted) {
                return new Pokemon[] {pokemon, opposingPokemon};
            }
        } else if (move.getEffect().equals("Three-Turn")) {
            if (turnPassed.containsKey(move)) {
                if (turnPassed.get(move)[1] == true) {

                } else if (turnPassed.get(move)[0] == true) {

                }
            } else {
                turnPassed.put(move, new Boolean[] {true, false});
            }
        }

        if (!move.getEffect().equals("Two-Turn") && !move.getEffect().equals(
                "Three-Turn")) {
            if (pokemon.equals(foePokemon)) {
                System.out.print("Enemy ");
            }
            System.out.println(pokemon.getNickname() + " used " + move.getName()
                    + "!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {}
        }
        int brightpowder = 0;
        double threshold = move.getAccuracy() * (getAccStats(pokemon, 4))
                * (getAccStats(opposingPokemon, 5));
        boolean attackHits = (new Random().nextDouble() * 100 ) < threshold
                - brightpowder;
        pokemon.useMove(index, 1);
        if (attackHits) {
            if (move.getCategory().equals("Physical") || move.getCategory()
                    .equals("Special")) {
                double effectiveness = Calculations.typeMatchup(opposingPokemon,
                        move);
                double criticalHitFactor = BattleStats.ongoingMove.get(pokemon)[5] ? 4 : 1;
                double criticalHit = new Random().nextDouble() <= ((pokemon
                        .getStatistics().getBaseSpeed() / 512.0) / criticalHitFactor)
                        ? (2 * pokemon.getStatistics().getLevel() + 5) / (double)
                        (pokemon.getStatistics().getLevel() + 5) : 1.0;
                if (effectiveness == 0) {
                    System.out.println("It doesn't effect " + opposingPokemon
                            .getNickname() + "...");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {}
                } else {
                    damage = (int) Math.round(Calculations.calculateDamage(
                            pokemon, opposingPokemon, move, criticalHit)
                            * effectiveness);
                    opposingPokemon.getStatistics().setCurrentHealthPoints(damage);
                }
                if (criticalHit > 1) {
                    System.out.println("Critical hit!");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {}
                }
                if (effectiveness > 1) {
                    System.out.println("It's super effective!");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {}
                } else if (effectiveness < 1 && effectiveness > 0) {
                    System.out.println("It's not very effective...");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {}
                }
                if (move.getName().equals("Rage") && !usingMove.containsValue(
                        Move.RAGE)) {
                    usingMove.put(pokemon, Move.RAGE);
                } else if (move.getName().equals("Rage") && usingMove.containsValue(
                        Move.RAGE)) {
                    if (usingMove.get(pokemon) == null) {
                        usingMove.put(pokemon, Move.RAGE);
                    }
                }
                if (usingMove.containsValue(Move.RAGE)) {
                    if (usingMove.get(opposingPokemon) != null) {
                        if (usingMove.get(opposingPokemon).getName().equals(
                                "Rage") && attackHits && effectiveness > 0) {
                            if (opposingPokemon.equals(foePokemon)) {
                                System.out.print("Enemy ");
                            }
                            System.out.println(opposingPokemon
                                    .getPokemonName() + "'s rage is building!");
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {}
                            Byte[] stats1 = BattleStats.stats.get(pokemon);
                            Byte[] stats2 = BattleStats.stats.get(
                                    opposingPokemon);
                            Byte[][] newStats = ChangeStatistics.changeStats(
                                    pokemon, Move.RAGE, opposingPokemon, stats1,
                                    stats2);
                            BattleStats.stats.replace(pokemon, BattleStats
                                    .stats.get(pokemon), newStats[0]);
                            BattleStats.stats.replace(opposingPokemon,
                                    BattleStats.stats.get(pokemon), newStats[1]);
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {}
                        }
                    }
                }
                if (move.getEffect().equals("Recoils")) {
                    pokemon.getStatistics().setCurrentHealthPoints(Calculations
                            .calculateRecoilDamage(move, damage));
                    if (pokemon.equals(foePokemon)) {
                        System.out.print("Enemy ");
                    }
                    System.out.println(opposingPokemon.getPokemonName() + "'s"
                            + " hit with recoil!");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {}
                    if (pokemon.getStatistics().getIsFainted()) {
                        if (pokemon.equals(foePokemon)) {
                            System.out.print("Enemy ");
                        }
                        System.out.println(opposingPokemon.getPokemonName()
                                + " fainted!");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {}
                    }
                } else if(move.getEffect().equals("Statistic-Self")) {
                    Byte[] stats1 = BattleStats.stats.get(pokemon);
                    Byte[] stats2 = BattleStats.stats.get(
                            opposingPokemon);
                    Byte[][] newStats = ChangeStatistics.changeStats(
                            pokemon, move, opposingPokemon, stats1,
                            stats2);
                    BattleStats.stats.replace(pokemon, BattleStats
                            .stats.get(pokemon), newStats[0]);
                    BattleStats.stats.replace(opposingPokemon,
                            BattleStats.stats.get(pokemon), newStats[1]);
                }

                if (!opposingPokemon.getStatistics().getIsFainted()) {
                    if (move.getEffect().equals("Binding")) {
                        if (opposingPokemon.equals(foePokemon)) {
                            System.out.print("Enemy ");
                        }
                        System.out.println(opposingPokemon.getPokemonName() + " can't move!");
                        usingMove.put(pokemon, Move.FIRE_SPIN);
                        double percent = new Random().nextDouble();
                        if (percent <= 0.375) {boundCounter = 2;}
                        else if(percent >= 0.376 && percent <= 0.75) {boundCounter = 3;}
                        else if(percent >= 0.751 && percent <= 0.875) {boundCounter = 4;}
                        else {boundCounter = 5;}
                    } else if (move.getEffect().equals("Burns")) {
                        opposingPokemon.getStatistics().setIsBurned(MoveEffects
                            .inflictingBurn(move, opposingPokemon));
                    } else if (move.getEffect().equals("Confuses")) {

                    } else if (move.getEffect().equals("Flinching")) {
                        opposingPokemon.getStatistics().setIsFlinching(MoveEffects
                            .causeFlinching(move));
                    } else if (move.getEffect().equals("Freezes")) {

                    } else if (move.getEffect().equals("Paralyzes")) {
                        opposingPokemon.getStatistics().setIsParalyzed(MoveEffects
                                .inflictingParalysis(move, opposingPokemon));
                    } else if (move.getEffect().equals("Poisons")) {
                        opposingPokemon.getStatistics().setIsPoisioned(MoveEffects
                                .inflicingPoison(move, opposingPokemon));
                    } else if (move.getEffect().equals("Sleep")) {

                    } else if (move.getEffect().equals("Statistic-Opp")) {
                        Byte[] stats1 = BattleStats.stats.get(pokemon);
                        Byte[] stats2 = BattleStats.stats.get(
                                opposingPokemon);
                        Byte[][] newStats = ChangeStatistics.changeStats(
                                pokemon, move, opposingPokemon, stats1,
                                stats2);
                        BattleStats.stats.replace(pokemon, BattleStats
                                .stats.get(pokemon), newStats[0]);
                        BattleStats.stats.replace(opposingPokemon,
                                BattleStats.stats.get(pokemon), newStats[1]);
                    }
                } else {
                    if (opposingPokemon.equals(foePokemon)) {
                        System.out.print("Enemy ");
                    }
                    System.out.println(opposingPokemon.getPokemonName()
                            + " fainted!");
                    if (usingMove.containsValue(Move.RAGE)) {
                        if (usingMove.get(opposingPokemon) != null) {
                            if (usingMove.get(opposingPokemon).getName().equals("Rage")) {
                                usingMove.remove(opposingPokemon, Move.RAGE);
                            }
                        }
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {}
                }
            } else if (move.getCategory().equals("Status")) {
                if (move.getEffect().equals("Burns")) {

                } else if (move.getEffect().equals("Confuses")) {

                } else if (move.getEffect().equals("Freezes")) {

                } else if (move.getEffect().equals("New Move")) {
                    if (move.getName().equals("Mirror Move")) {
                        return attack(pokemon, opposingPokemon, index);
                    }
                } else if (move.getEffect().equals("Paralyzes")) {
                    opposingPokemon.getStatistics().setIsParalyzed(MoveEffects
                            .inflictingParalysis(move, opposingPokemon));
                } else if (move.getEffect().equals("Poisons")) {
                    opposingPokemon.getStatistics().setIsPoisioned(MoveEffects
                            .inflicingPoison(move, opposingPokemon));
                } else if (move.getEffect().equals("Sleep")) {

                } else if (move.getEffect().equals("Switched")) {
                    if (opponent == null) {
                        double percent = new Random().nextDouble();
                        //Chance of failing is Floor(T / 4) / (T + U + 1); T is target's
                        //level, U is user's level
                        int T = opposingPokemon.getStatistics().getLevel();
                        int U = pokemon.getStatistics().getLevel();
                        double thresh = Math.floor(T / 4) / (T + U + 1.0);
                        if (percent >= thresh) {
                            battleIsOver = true;
                        } else {
                            System.out.println("But it failed!");
                        }
                    } else {
                        System.out.println("But it failed!");
                    }
                } else if (move.getEffect().equals("Temporary")) {
                    MoveEffects.ongingEffects(pokemon, move);
                } else if (move.getName().equals("Leech Seed")) {
                    if (!usingMove.containsValue(Move.LEECH_SEED)) {
                        if (opposingPokemon.equals(foePokemon)) {
                            System.out.print("Enemy ");
                        }
                        System.out.println(opposingPokemon.getPokemonName()
                                + " was seeded!");
                        usingMove.put(foePokemon, move);
                    } else {
                        if (usingMove.get(opposingPokemon) != null) {
                            if (usingMove.get(opposingPokemon).getName().equals(
                                    "Leech Seed")) {
                                System.out.println("But it failed!");
                            } else {
                                if (opposingPokemon.equals(foePokemon)) {
                                    System.out.print("Enemy ");
                                }
                                System.out.println(opposingPokemon.getPokemonName()
                                        + " was seeded!");
                                usingMove.put(foePokemon, move);
                            }
                        } else {
                            if (opposingPokemon.equals(foePokemon)) {
                                System.out.print("Enemy ");
                            }
                            System.out.println(opposingPokemon.getPokemonName()
                                    + " was seeded!");
                            usingMove.put(foePokemon, move);
                        }
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {}
                } else {
                    Byte[] stats1 = BattleStats.stats.get(pokemon);
                    Byte[] stats2 = BattleStats.stats.get(opposingPokemon);
                    Byte[][] newStats = ChangeStatistics.changeStats(pokemon, move,
                            opposingPokemon, stats1, stats2);
                    BattleStats.stats.replace(pokemon, BattleStats.stats.get(pokemon),
                            newStats[0]);
                    BattleStats.stats.replace(opposingPokemon, BattleStats.stats
                            .get(pokemon), newStats[1]);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {}
                }
            }
        } else {
            System.out.println(pokemon.getNickname() + "'s attack missed!");
            if (move.getName().equals("Rage")) {
                usingMove.remove(pokemon, Move.RAGE);
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {

            }
        }
        //if the move is 2-turn, delay this effect
        lastMoveUsed.clear();
        lastMoveUsed.put(pokemon, move);
        return new Pokemon[] {pokemon, opposingPokemon};
    }

    public static int getDamage() {
        return damage;
    }
}
