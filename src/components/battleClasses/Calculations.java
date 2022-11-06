package components.battleClasses;

import components.Battle;
import components.battleClasses.pokemon.Pokemon;

import java.util.Random;

import static components.battleClasses.BattleStats.getStats;

public class Calculations {
    public static double typeMatchup(Pokemon opposingPokemon, Move move) {
        Type type = move.getType();
        double result = 1.0;
        for (Type t : opposingPokemon.getType()) {
            switch (type) {
                case NORMAL:
                    if (t == Type.ROCK || t == Type.STEEL) {
                        result *= 0.5;
                    } else if (t == Type.GHOST) {
                        result *= 0;
                    } else {
                        result *= 1.0;
                    }
                    break;
                case GRASS:
                    if (t == Type.WATER || t == Type.GROUND || t == Type.ROCK) {
                        result *= 2.0;
                    } else if (t == Type.FIRE || t == Type.STEEL || t == Type.BUG ||
                            t == Type.FLYING || t == Type.DRAGON || t == Type.POISON) {
                        result *= 0.5;
                    } else {
                        result *= 1.0;
                    }
                    break;
                case WATER:
                    if (t == Type.FIRE || t == Type.GROUND || t == Type.ROCK) {
                        result *= 2.0;
                    } else if (t == Type.GRASS || t == Type.DRAGON) {
                        result *= 0.5;
                    } else {
                        result *= 1.0;
                    }
                    break;
                case FIRE:
                    if (t == Type.GRASS || t == Type.ICE || t == Type.STEEL) {
                        result *= 2.0;
                    } else if (t == Type.WATER || t == Type.ROCK || t == Type.DRAGON) {
                        result *= 0.5;
                    } else {
                        result *= 1.0;
                    }
                    break;
                case ELECTRIC:
                    if (t == Type.WATER || t == Type.FLYING) {
                        result *= 2.0;
                    } else if (t == Type.GRASS || t == Type.DRAGON) {
                        result *= 0.5;
                    } else if (t == Type.GROUND) {
                        result *= 0;
                    } else {
                        result *= 1.0;
                    }
                    break;
                case GROUND:
                    if (t == Type.FIRE || t == Type.ELECTRIC || t == Type.ROCK ||
                            t == Type.STEEL) {
                        result *= 2.0;
                    } else if (t == Type.BUG || t == Type.GRASS) {
                        result *= 0.5;
                    } else if (t == Type.FLYING) {
                        result *= 0;
                    } else {
                        result *= 1.0;
                    }
                    break;
                case ROCK:
                    if (t == Type.FIRE || t == Type.BUG || t == Type.FLYING ||
                            t == Type.ICE) {
                        result *= 2.0;
                    } else if (t == Type.GROUND || t == Type.STEEL || t == Type.FIGHTING) {
                        result *= 0.5;
                    } else {
                        result *= 1.0;
                    }
                    break;
                case STEEL:
                    if (t == Type.ROCK || t == Type.ICE || t == Type.FAIRY) {
                        result *= 2.0;
                    } else if (t == Type.FIRE || t == Type.WATER || t == Type.ELECTRIC ||
                            t == Type.STEEL) {
                        result *= 0.5;
                    } else {
                        result *= 1.0;
                    }
                    break;
                case BUG:
                    if (t == Type.GRASS || t == Type.PSYCHIC || t == Type.DARK) {
                        result *= 2.0;
                    } else if (t == Type.FIRE || t == Type.STEEL || t == Type.FLYING ||
                            t == Type.GHOST || t == Type.FAIRY || t == Type.FIGHTING ||
                            t == Type.POISON) {
                        result *= 0.5;
                    } else {
                        result *= 1.0;
                    }
                    break;
                case FLYING:
                    if (t == Type.GRASS || t == Type.BUG || t == Type.FIGHTING) {
                        result *= 2.0;
                    } else if (t == Type.ELECTRIC || t == Type.ROCK || t == Type.STEEL) {
                        result *= 0.5;
                    } else {
                        result *= 1.0;
                    }
                    break;
                case ICE:
                    if (t == Type.GRASS || t == Type.GROUND || t == Type.FLYING ||
                            t == Type.DRAGON) {
                        result *= 2.0;
                    } else if (t == Type.FIRE || t == Type.WATER || t == Type.STEEL ||
                            t == Type.ICE) {
                        result *= 0.5;
                    } else {
                        result *= 1.0;
                    }
                    break;
                case DRAGON:
                    if (t == Type.DRAGON) {
                        result *= 2.0;
                    } else if (t == Type.STEEL) {
                        result *= 0.5;
                    } else if (t == Type.FAIRY) {
                        result *= 0;
                    } else {
                        result *= 1.0;
                    }
                    break;
                case DARK:
                    if (t == Type.PSYCHIC || t == Type.GHOST) {
                        result *= 2.0;
                    } else if (t == Type.DARK || t == Type.FAIRY || t == Type.FIGHTING) {
                        result *= 0.5;
                    } else {
                        result *= 1.0;
                    }
                    break;
                case PSYCHIC:
                    if (t == Type.POISON || t == Type.FIGHTING) {
                        result *= 2.0;
                    } else if (t == Type.PSYCHIC || t == Type.STEEL) {
                        result *= 0.5;
                    } else if (t == Type.DARK) {
                        result *= 0;
                    } else {
                        result *= 1.0;
                    }
                    break;
                case GHOST:
                    if (t == Type.PSYCHIC || t == Type.GHOST) {
                        result *= 2.0;
                    } else if (t == Type.DARK) {
                        result *= 0.5;
                    } else if (t == Type.NORMAL) {
                        result *= 0;
                    } else {
                        result *= 1.0;
                    }
                    break;
                case FAIRY:
                    if (t == Type.DRAGON || t == Type.DARK || t == Type.FIGHTING) {
                        result *= 2.0;
                    } else if (t == Type.FIRE || t == Type.POISON || t == Type.STEEL) {
                        result *= 0.5;
                    } else {
                        result *= 1.0;
                    }
                    break;
                case FIGHTING:
                    if (t == Type.NORMAL || t == Type.ROCK || t == Type.STEEL ||
                            t == Type.ICE || t == Type.DARK) {
                        result *= 2.0;
                    } else if (t == Type.BUG || t == Type.FLYING || t == Type.PSYCHIC ||
                            t == Type.FAIRY || t == Type.POISON) {
                        result *= 0.5;
                    } else if (t == Type.GHOST) {
                        result *= 0;
                    } else {
                        result *= 1.0;
                    }
                    break;
                case POISON:
                    if (t == Type.GRASS || t == Type.FAIRY) {
                        result *= 2.0;
                    } else if (t == Type.GROUND || t == Type.ROCK || t == Type.GHOST ||
                            t == Type.POISON) {
                        result *= 0.5;
                    } else if (t == Type.STEEL) {
                        result *= 0;
                    } else {
                        result *= 1.0;
                    }
                    break;
            }
        }
        return result;
    }

    public static int calculateDamage(Pokemon pokemon, Pokemon opposingPokemon,
                                      Move move, double criticalHit) {
        double weather = 1.0;//Weather is 1.5 for correlating weather, or 0.5
        double target = 1.0;//Target is 0.75 if there if move hits more than one target

        double random = (new Random().nextDouble() * 0.15) + 0.85;
        double stab = 1.0;
        for (Type t : pokemon.getType()) {
            stab = t.equals(move.getType()) ? 1.5 : stab;
        }
        double burn = !pokemon.getStatistics().getIsBurned()
                ? 1.0 : 0.5;
        double type = typeMatchup(opposingPokemon, move);
        double other = 1.0;
        double factorOne;
        double factorTwo;
        double factorThree;
        double factorFour;
        int damage;

        if (move.getCategory().equals("Physical")) {
            factorOne = ((2 * opposingPokemon.getStatistics().getLevel())
                    / 5.0) + 2;
            double defense = (double) (getStats(opposingPokemon, 1));
            defense *= BattleStats.ongoingMove.get(opposingPokemon)[8] ? 2 : 1;
            if (defense > 1024) {
                defense -= (defense % 1024);
            }
            factorTwo = move.getPower() * (getStats(pokemon, 0)
                    / defense);
            factorThree = ((factorOne * factorTwo) / 50.0) + 2;
            double modifier = target * weather * criticalHit * random * stab
                    * type * burn * other;
            factorFour = factorThree * modifier;
            damage = (int) Math.round(factorFour);
        } else {
            factorOne = ((2 * opposingPokemon.getStatistics().getLevel()) / 5.0) + 2;
            double specialDefense = (double) (getStats(opposingPokemon, 2));
            specialDefense *= BattleStats.ongoingMove.get(opposingPokemon)[6] ? 2 : 1;
            if (specialDefense > 1024) {
                specialDefense -= (specialDefense % 1024);
            }
            factorTwo = move.getPower() * (getStats(pokemon, 2)
                    / specialDefense); //MAKE SURE THAT YOU CHECK THIS
            factorThree = ((factorOne * factorTwo) / 50.0) + 2;
            double modifier = target * weather * criticalHit * random * stab * type
                    * burn * other;
            factorFour = factorThree * modifier;
            damage = (int) Math.round(factorFour);
        }
        if (damage <= 0) {
            damage = 1;
        }
        return -damage;
    }

    public static int calculateExp(Pokemon foePokemon, double a,
                                   double s) {
        double b = (double) foePokemon.getBaseExperienceYield();
        double e = 1.0; //Change later; Factor for lucky egg (GEN II)
        double f = 1.0; //Change later; Factor for affection (GEN VI)
        double level = foePokemon.getStatistics().getLevel();
        double p = 1.0; //Change later; Factor for exp. point power (GEN VI)
        double t = 1.0; //pokemon.getIDNumber() == Trainer.getIdNo() ? 1 : 1.5; CHANGE L8R
			//? 1.0 : 1.2;
        double v = 1.0; //Change later; When the pokemon is at or past threshold (GEN VII)
        //double s = 1.0;
        //double s1 = 1.0;
        //double s2 = 1.0;
        int experiencePoints = (int) Math.round((a * t * b * e
                * level * p * f * v) / (7 * s));
        return experiencePoints;
    }

    public static int calculateRecoilDamage(Move move, int damage) {
        int recoil = 0;
        if (move.getName().equals("Take Down") || move.getName().equals("Submission"
            ) || move.getName().equals("Struggle")) {
            return (int) Math.round(damage / 4.0);
        } else if (move.getName().equals("Double"
                + " Edge")) {
            return (int) Math.round(damage / 3.0);
        }
        return -recoil;
    }

    public static int calculateSeedDamage(Pokemon opponent) {
        int health;
        health = (int) Math.round(opponent.getStatistics().getHealthPoints()
                / 16.0);
        int hp = opponent.getStatistics().getCurrentHealthPoints();
        if (hp < health) {
            health = hp;
        }
        return health;
    }
}
