package components.battleClasses;

import components.battleClasses.pokemon.Pokemon;

import java.util.Random;

import static components.Battle.foePokemon;
import static components.battleClasses.BattleStats.ongoingMove;
import static components.Battle.usingMove;


public class MoveEffects {
    public static boolean inflictingBurn(Move move, Pokemon opposingPokemon) {
        String name = move.getName();
        int randomNumber = new Random().nextInt(10);
        switch (name) { //Ember, fire blast, fire punch, flamethrower,
            case "Ember":
            case "Fire Punch":
            case "Flamethrower":
                if (randomNumber == 0 && !opposingPokemon.getStatistics()
                        .getIsBurned()) {
                    System.out.println(opposingPokemon.getPokemonName()
                            + " was burned!");
                    //BattleStatus.setAfflictedPokemon(opposingPokemon, 2, 1);
                    return true;
                }
                break;
            case "Fire Blast":
                if (randomNumber >= 0 && randomNumber <= 2 && !opposingPokemon
                        .getStatistics().getIsBurned()) {
                    System.out.println(opposingPokemon.getPokemonName()
                            + " was burned!");
                    //BattleStatus.setAfflictedPokemon(opposingPokemon, 2, 1);
                    return true;
                }
                break;
        }
        return false;
    }

    public static boolean inflictingConfusion(Move move, Pokemon opposingPokemon) {
        String name = move.getName();
        int turns = new Random().nextInt(4) + 1;
        int randomNumber = new Random().nextInt(10); //Add Dizzy Punch in Gen II
        switch (name) {
            case "Confuse Ray":
                if (!opposingPokemon.getStatistics().getIsConfused()) {
                    System.out.println(opposingPokemon.getPokemonName()
                            + " became confused!");
                    //BattleStatus.setAfflictedPokemon(opposingPokemon, 3, turns);
                    return true;
                } else {
                    System.out.println(opposingPokemon.getPokemonName()
                            + " is already confused!");
                }
                break;
            case "Confusion":
            case "Psybeam":
                if (randomNumber == 0 && !opposingPokemon.getStatistics()
                        .getIsConfused()) {
                    System.out.println(opposingPokemon.getPokemonName()
                            + " became confused!");
                    //BattleStatus.setAfflictedPokemon(opposingPokemon, 3, turns);
                    return true;
                } else if (opposingPokemon.getStatistics().getIsConfused()) {
                    System.out.println(opposingPokemon.getPokemonName()
                            + " is already confused!");
                }
                break;
        }
        return false;
    }

    public static boolean inflictingFreezing(Move move, Pokemon opposingPokemon) {
        String name = move.getName();
        int randomNumber = new Random().nextInt(10);
        switch (name) {
            case "Blizzard":
            case "Ice Beam":
            case "Ice Punch":
                if (randomNumber == 0 & !opposingPokemon.getStatistics()
                        .getIsFrozen()) {
                    System.out.println(opposingPokemon.getPokemonName()
                            + " was frozen!");
                    //BattleStatus.setAfflictedPokemon(opposingPokemon, 4, 1);
                    return true;
                }
                break;
        }
        return false;
    }

    public static boolean inflictingParalysis(Move move, Pokemon opposingPokemon) {
        String name = move.getName();
        int randomNumber = new Random().nextInt(10);
        switch (name) {
            case "Body Slam":
            case "Lick":
            case "Thunder":
                if (randomNumber >= 0 && randomNumber <= 2 && !opposingPokemon
                        .getStatistics().getIsParalyzed()) {
                    System.out.println(opposingPokemon.getPokemonName()
                            + "'s paralyzed! It may not attack!");
                    //BattleStatus.setAfflictedPokemon(opposingPokemon, 5, 1);
                    return true;
                }
                break;
            case "Glare":
            case "Stun Spore":
            case "Thunder Wave":
                if (!opposingPokemon.getStatistics().getIsParalyzed()) {
                    System.out.println(opposingPokemon.getPokemonName()
                            + "'s paralyzed! It may not attack!");
                    //BattleStatus.setAfflictedPokemon(opposingPokemon, 5, 1);
                    return true;
                } else if (opposingPokemon.getStatistics().getIsParalyzed()) {
                    System.out.println(opposingPokemon.getPokemonName()
                            + " is already paralyzed!");
                }
                break;

            case "Thunder Punch":
            case "Thunder Shock":
            case "Thunderbolt":
                if (randomNumber == 0 && !opposingPokemon.getStatistics()
                        .getIsParalyzed()) {
                    System.out.println(opposingPokemon.getPokemonName()
                            + "'s paralyzed! It may not attack!");
                    //BattleStatus.setAfflictedPokemon(opposingPokemon, 5, 1);
                    return true;
                }
                break;
        }
        return false;
    }

    public static boolean inflicingPoison(Move move, Pokemon opposingPokemon) {
        String name = move.getName();
        int randomNumber = new Random().nextInt(10);
        Type[] types = opposingPokemon.getType();
        boolean isAPoisonType = false;
        for (Type t : types) {
            isAPoisonType = t == Type.POISON ? true : isAPoisonType;
        }
        switch (name) {
            case "Poison Gas":
            case "PoisonPowder":
                if (!opposingPokemon.getStatistics().getIsPoisoned()
                        && !opposingPokemon.getStatistics().getIsAsleep()
                        && !opposingPokemon.getStatistics().getIsBurned()
                        && !opposingPokemon.getStatistics().getIsFrozen()
                        && !opposingPokemon.getStatistics().getIsParalyzed()
                        && !isAPoisonType) {
                    System.out.println(opposingPokemon.getPokemonName()
                            + " was poisoned!");
                    //BattleStatus.setAfflictedPokemon(opposingPokemon, 6, -1);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {}
                    return true;
                } else if (opposingPokemon.getStatistics().getIsAsleep()
                        || opposingPokemon.getStatistics().getIsBurned()
                        || opposingPokemon.getStatistics().getIsFrozen()
                        || opposingPokemon.getStatistics().getIsParalyzed()) {
                    System.out.println("But it failed!");
                } else if (opposingPokemon.getStatistics().getIsPoisoned()
                        || opposingPokemon.getStatistics().getIsBadlyPoisoned()) {
                    System.out.println(opposingPokemon.getPokemonName()
                            + " is already poisoned!");
                } else if (isAPoisonType) {
                    System.out.println("It didn't affect " + opposingPokemon
                            .getPokemonName() + "!");
                }
                break;
            case "Poison Sting":
            case "Sludge":
                if (randomNumber >= 0 && randomNumber <= 2 && !opposingPokemon
                        .getStatistics().getIsPoisoned() && !isAPoisonType
                        && !opposingPokemon.getStatistics().getIsAsleep()
                        && !opposingPokemon.getStatistics().getIsBurned()
                        && !opposingPokemon.getStatistics().getIsFrozen()
                        && !opposingPokemon.getStatistics().getIsParalyzed()) {
                    System.out.println(opposingPokemon.getPokemonName()
                            + " was poisoned!");
                    //BattleStatus.setAfflictedPokemon(opposingPokemon, 6, -1);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {}
                    return true;
                }
                break;
            case "Smog":
                if (randomNumber >= 0 && randomNumber <= 3 && !opposingPokemon
                        .getStatistics().getIsPoisoned() && !isAPoisonType
                        && !opposingPokemon.getStatistics().getIsAsleep()
                        && !opposingPokemon.getStatistics().getIsBurned()
                        && !opposingPokemon.getStatistics().getIsFrozen()
                        && !opposingPokemon.getStatistics().getIsParalyzed()) {
                    System.out.println(opposingPokemon.getPokemonName()
                            + " was poisoned!");
                    //BattleStatus.setAfflictedPokemon(opposingPokemon, 6, -1);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {}
                    return true;
                }
                break;
            case "Toxic":
                if (!opposingPokemon.getStatistics().getIsPoisoned()
                        && !opposingPokemon.getStatistics().getIsAsleep()
                        && !opposingPokemon.getStatistics().getIsBurned()
                        && !opposingPokemon.getStatistics().getIsFrozen()
                        && !opposingPokemon.getStatistics().getIsParalyzed()
                        && !isAPoisonType) {
                    System.out.println(opposingPokemon.getPokemonName()
                            + " was badly poisoned!");
                    //BattleStatus.setAfflictedPokemon(opposingPokemon, 6, -1);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {}
                    return true;
                } else if (opposingPokemon.getStatistics().getIsAsleep()
                        || opposingPokemon.getStatistics().getIsBurned()
                        || opposingPokemon.getStatistics().getIsFrozen()
                        || opposingPokemon.getStatistics().getIsParalyzed()) {
                    System.out.println("But it failed!");
                } else if (opposingPokemon.getStatistics().getIsPoisoned()
                        || opposingPokemon.getStatistics().getIsBadlyPoisoned()) {
                    System.out.println(opposingPokemon.getPokemonName()
                            + " is already poisoned!");
                } else if (isAPoisonType) {
                    System.out.println("It didn't affect " + opposingPokemon
                            .getPokemonName() + "!");
                }
                break;
            case "Twineedle":
                if (randomNumber >= 0 && randomNumber <= 1 && !opposingPokemon
                        .getStatistics().getIsPoisoned() && !isAPoisonType
                        && !opposingPokemon.getStatistics().getIsAsleep()
                        && !opposingPokemon.getStatistics().getIsBurned()
                        && !opposingPokemon.getStatistics().getIsFrozen()
                        && !opposingPokemon.getStatistics().getIsParalyzed()) {
                    System.out.println(opposingPokemon.getPokemonName()
                            + " was poisoned!");
                    //BattleStatus.setAfflictedPokemon(opposingPokemon, 6, -1);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {}
                    return true;
                }
                break;
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {}
        return false;
    }

    public static boolean inflictingSleep(Move move, Pokemon opposingPokemon) {
        String name = move.getName();
        int turns = new Random().nextInt(7) + 1;
        switch (name) {
            case "Hypnosis":
            case "Lovely Kiss":
            case "Sing":
                if (!opposingPokemon.getStatistics().getIsAsleep()) {
                    System.out.println(opposingPokemon.getPokemonName()
                            + " fell asleep!");
                    //BattleStatus.setAfflictedPokemon(opposingPokemon, 0, turns);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {}
                    return true;
                } else if (opposingPokemon.getStatistics().getIsAsleep()) {
                    System.out.println(opposingPokemon.getPokemonName()
                            + " is already asleep!");
                }
                break;
            case "Sleep Powder":
                if (!opposingPokemon.getStatistics().getIsAsleep()) {
                    System.out.println(opposingPokemon.getPokemonName()
                            + " fell asleep!");
                    //BattleStatus.setAfflictedPokemon(opposingPokemon, 0, turns);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {}
                    return true;
                } else if (opposingPokemon.getStatistics().getIsAsleep()) {
                    System.out.println(opposingPokemon.getPokemonName()
                            + " is already asleep!");
                }
            case "Spore":
                if (!opposingPokemon.getStatistics().getIsAsleep()) {
                    System.out.println(opposingPokemon.getPokemonName()
                            + " fell asleep!");
                    //BattleStatus.setAfflictedPokemon(opposingPokemon, 0, turns);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {}
                    return true;
                } else if (opposingPokemon.getStatistics().getIsAsleep()) {
                    System.out.println(opposingPokemon.getPokemonName()
                            + " is already asleep!");
                }
                break;
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {}
        return false;
    }

    public static boolean causeFlinching(Move move) {
        if (move.getName().equals("Bite") || move.getName().equals(
                "Headbutt") || move.getName().equals("Low Kick") || move
                .getName().equals("Rock Slide") || move.getName().equals(
                "Rolling Kick") || move.getName().equals("Stomp")) {
            return new Random().nextInt(100) <= 30;
        } else {
            return new Random().nextInt(100) <= 10;
        }
    }

    public static void ongingEffects(Pokemon pokemon, Move move) {
        if (move.getName().equals("Focus Energy")) {
            Boolean[] moves = ongoingMove.get(pokemon);
            moves[5] = true;
            ongoingMove.replace(pokemon, moves);
            System.out.println(pokemon.getPokemonName() + " is getting pumped!");

        } else if (move.getName().equals("Light Screen")) {
            Boolean[] moves = ongoingMove.get(pokemon);
            moves[6] = true;
            ongoingMove.replace(pokemon, moves);
            System.out.println(pokemon.getPokemonName() + "'s protected against"
                    + " special attacks!");
        } else if (move.getName().equals("Reflect")) {
            Boolean[] moves = ongoingMove.get(pokemon);
            moves[8] = true;
            ongoingMove.replace(pokemon, moves);
            System.out.println(pokemon.getPokemonName() + " gained armor!");
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {}
    }

    public static boolean twoTurnEffects(Pokemon pokemon, Move move, boolean used) {
        if (move.getName().equals("Dig")) {

        } else if (move.getName().equals("Fly")) {

        } else if (move.getName().equals("Hyper Beam")) {

        } else if (move.getName().equals("Razor Wind")) {

        } else if (move.getName().equals("Skull Bash")) {
            if (used) {
                if (pokemon.equals(foePokemon)) {
                    System.out.print("Enemy ");
                }
                System.out.println(pokemon.getPokemonName() + " used "
                        +  move.getName() + "!");
                usingMove.remove(pokemon, move);
                return true;
            } else {
                if (pokemon.equals(foePokemon)) {
                    System.out.print("Enemy ");
                }
                System.out.println(pokemon.getPokemonName() + " lowered its head!");
                usingMove.put(pokemon, move);
                return false;
            }
        } else if (move.getName().equals("Sky Attack")) {

        } else if (move.getName().equals("SolarBeam")) {
            if (used) {
                if (pokemon.equals(foePokemon)) {
                    System.out.print("Enemy ");
                }
                System.out.println(pokemon.getPokemonName() + " used "
                        +  move.getName() + "!");
                usingMove.remove(pokemon, move);
                return true;
            } else {
                if (pokemon.equals(foePokemon)) {
                    System.out.print("Enemy ");
                }
                System.out.println(pokemon.getPokemonName() + " took in sunlight!");
                usingMove.put(pokemon, move);
                return false;
            }
        }
        return false;
    }

    public static void threeTurnEffects() {

    }
}