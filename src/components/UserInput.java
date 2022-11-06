package components;

import components.battleClasses.Move;
import components.battleClasses.pokemon.Pokemon;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import static components.Battle.leadPokemon;
import static components.battleClasses.BattleStats.getStats;

import static components.Battle.usingMove;

public class UserInput {

    private static Scanner scanner;
    private static boolean validChoice;
    protected static int choice;
    protected static int moveIndex;
    protected static int C;
    protected static Pokemon lastPokemon;

    public static String changeNickname(Pokemon pokemon) {
        System.out.println("Nickname: ");
        String name = scanner.nextLine();
        if (name.equals("")) {
            pokemon.setNickname(pokemon.getPokemonName());
            return pokemon.getPokemonName();
        } else {
            pokemon.setNickname(name);
            return name;
        }
    }

    public static boolean selectOption() {
        scanner = new Scanner(System.in);
        validChoice = false;
        /*System.out.println();
        System.out.println("What will " + components.Battle.leadPokemon.getPokemonName()
                + " do?"); //Only for GEN III onward*/
        System.out.println();
        System.out.println(Battle.leadPokemon.getPokemonName() + "\tLv:" + Battle
                .leadPokemon.getStatistics().getLevel());
        System.out.println("1: Fight\t2: Pokemon");
        System.out.println("3: Bag  \t4: Run");
        while (!validChoice) {
            System.out.println("HP: " + Battle.leadPokemon.getStatistics()
                    .getCurrentHealthPoints() + "/" + Battle.leadPokemon.getStatistics()
                    .getHealthPoints());
            try {
                //int choice = -1;
                choice = scanner.nextInt();
                if (choice == 1) {
                    validChoice = true;
                    return selectMove();
                } else if (choice == 2) {
                    validChoice = true;
                    return selectPokemon();
                } else if (choice == 3) {
                    validChoice = true;
                    return selectItem();
                } else if (choice == 4) {
                    validChoice = true;
                    return escapeAttempt();
                }
            } catch (InputMismatchException exception) {
                //Do nothing; Resume normal function
            }
        }
        System.out.println("Error.");
        return false;
    }

    public static boolean selectMove() {
        if (!Battle.leadPokemon.hasUsableMoves()) { //Pokemon has moves to use;
            moveIndex = -1;
            return true;
        }
        if (usingMove.containsValue(Move.FIRE_SPIN)) {
            if (usingMove.get(leadPokemon) != null) {
                if (usingMove.get(leadPokemon).getName().equals("Fire Spin")) {
                    if (leadPokemon.getPowerPointsSet().get(Move.FIRE_SPIN) > 0) {
                        validChoice = true;
                        return true;
                    }
                }
            }
        }
        System.out.println("Select a Move.");
        int i = 0;
        for (Move move: Battle.leadPokemon.getMoveset()) {
            if (move == Move.BLANK) {
                System.out.println(i + ": -----");
            } else {
                System.out.println(i + ": " + move.getName()); //In one window
                System.out.println("TYPE/" + move.getType().toString() + "\n"
                        + Battle.leadPokemon.getPowerPointsSet().get(move) + "/"
                        + move.getPowerPoints()); //In a smaller window
            }
            i++;
        }
        validChoice = false;
        while (!validChoice) {
            try {
                moveIndex = scanner.nextInt();
                scanner.nextLine();
                if (moveIndex >= 0 && moveIndex <= 3) {
                    Move moveChoice = Battle.leadPokemon.getMoveset()[moveIndex];
                    if (moveChoice != Move.BLANK) {
                        if (Battle.leadPokemon.getPowerPointsSet().get(moveChoice)
                                > 0) {
                            validChoice = true;
                            return true;
                        } else {
                            System.out.println("There's no PP left for this move!");
                        }

                    }
                }
            } catch (InputMismatchException exception) {
                return false;
            }
        }
        return false;
    }

    public static boolean selectPokemon() {
        validChoice = false;
        int pokemonChoice;
        for (int i = 0; i < 6; i++) {
            if (Battle.pokemonParty[i] != null) {
                System.out.println(i + ": [ " + Battle.pokemonParty[i]
                        .getPokemonName() + " ]");
            } else {
                System.out.println(i + ": []");
            }
        }
        System.out.println("Bring out which Pokemon?"); //To be displayed in caption
        System.out.println("Press any other key to go back.");
        while (!validChoice) {
            try {
                scanner = new Scanner(System.in);
                pokemonChoice = scanner.nextInt();
                if (0 <= pokemonChoice && pokemonChoice <= 5) {
                    if (Battle.pokemonParty[pokemonChoice] != null && Battle
                            .leadPokemon != Battle.pokemonParty[pokemonChoice]
                            && !Battle.pokemonParty[pokemonChoice].getStatistics()
                            .getIsFainted()) {
                        lastPokemon = Battle.leadPokemon;
                        Battle.leadPokemon = Battle.pokemonParty[pokemonChoice];
                        Battle.participants.add(Battle.leadPokemon);
                        validChoice = true;
                    }
                }
            } catch (InputMismatchException ex) {
                if (!Battle.leadPokemon.getStatistics().getIsFainted()) {
                    return false;
                } else {
                    pokemonChoice = -1;
                }
            }
        }
        return true;
    }

    public static boolean selectItem() {
        return false;
    }

    public static boolean escapeAttempt() {
        if (Battle.opponent == null) {
            C++;
            int A = getStats(Battle.leadPokemon, 3);
            int B = (getStats(Battle.foePokemon, 3) / 4) % 256;
            int F = ((A * 32) / B) + (30 * C);
            if (F > 255) {
                System.out.println("Got away safely!");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {

                }
                return true;
            } else {
                if (new Random().nextInt(256) < F) {
                    System.out.println("Got away safely!");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {

                    }
                    return true;
                } else {
                    System.out.println("Can't escape!");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {

                    }
                    return false;
                }
            }

        } else {
            System.out.println("No! There's no running from a trainer battle!");
            return false;
        }
    }

    public static boolean switchPokemon() {
        scanner = new Scanner(System.in);
        System.out.println(Battle.opponent.getTrainerClass() + " "
                + Battle.opponent.getName() + " is about to use " + Battle
                .foePokemon.getPokemonName() + "!");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {

        }
        System.out.println("Will 'player' change pokemon? "
                + "(Y/N)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("Y")) {
            return selectPokemon();
        } else {
            return false;
        }
    }

    /*
    Create a method that monitors button press and release time
    if press/release time < 0.25 turn in corresponding direction
    else move a step for each 0.3 sec
     */

}
