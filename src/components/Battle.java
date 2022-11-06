package components;

import components.battleClasses.*;
import components.battleClasses.pokemon.Pokemon;

import java.util.*;

import static components.Attack.attack;
import static components.battleClasses.BattleStats.getAccStats;
import static components.battleClasses.BattleStats.getStats;
import static components.battleClasses.BattleStats.setStats;

public class Battle {

    public static class SpeedComparator implements Comparator<Pokemon> {

        public int compare(Pokemon p1, Pokemon p2) {
            return getStats(p2, 3) - getStats(p1, 3);
        }
    }

    public static class PriorityComparator implements Comparator<Pokemon> {

        Map<Pokemon, Move> map;

        public PriorityComparator(Map map) {
            this.map = map;
        }

        public int compare(Pokemon p1, Pokemon p2) {
            return map.get(p2).getPriority() - map.get(p1).getPriority();
        }
    }

    protected static int turn;
    protected static boolean battleIsOver;
    public static Pokemon leadPokemon;
    public static Pokemon foePokemon;
    protected static int moveToUse;
    protected static int opposingMoveToUse;
    protected static boolean canCatchPokemon;
    public static Pokemon[] pokemonParty;
    public static Pokemon[] pokemonOpponent;

    protected static ArrayList<Pokemon> speedOrder = new ArrayList<>();
    protected static Map<Pokemon, Move> pokemonOrder = new HashMap<Pokemon, Move>();
    protected static Set<Pokemon> participants = new HashSet<>();
    protected static Trainer opponent;
    protected static ArrayList<Pokemon> remainingPokemon;
    protected static ArrayList<Pokemon> oppRemainingPokemon;
    public static Map<Pokemon, Move> usingMove = new HashMap<>();
    //In the case of Leech Seed, this indicates who this move is being used on
    private static boolean choiceMade;

    private static ArrayList<Move> moves; //FOR TESTING ONLY; WHEN SELECTING RANDOM MOVE!

	/*
	STEPS OF A BATTLE
	1. DEPENDING ON THE TYPE OF BATTLE, WILD OR TRAINER, THE BATTLE IS SET UP
	EX: WILD ___ APPEARED! ; TRAINER ___ WANTS TO BATTLE
	2. OPPOSING POKEMON ARE SENT OUT
	3. USER'S POKEMON IS SENT OUT
	- IF THE LEAD POKEMON IS FAINTED, THE NEXT POKEMON THAT IS NOT FAINTED IS SENT OUT
	4. AN OPTION IS CHOSEN
	fight:
	-ON THE OPPONENT'S SIDE, A RANDOM MOVE IS CHOSEN;
	-THE NUMBER OF USABLE MOVES
	IS DETERMINED BY WHETHER THE MOVE HAS PP REMAINING AND IS NOT BLANK; IF THERE
	ARE NO REMAINING MOVES, STRUGGLE IS USED
	pokemon:
	-THE USER CAN CHECK THE SUMMARY OF OTHER POKEMON
	-IF THE POKEMON IS SHIFTED, THE BATTLE MOVES ON TO THE NEXT TURN
	bag:
	-
	-IF AN ITEM IS USED, AN ITEM IS DECREMENTED BY 1; THE BATTLE MOVES ON TO
	THE NEXT TURN

	IF ANY OF THESE OPTIONS RESULT IN PRESSING B, GO BACK TO THE MENU
	run:
	-ONLY AVAILABLE IN WILD BATTLES; IF SUCCESSFUL, THE BATTLE ENDS AUTOMATICALLY
	-IF UNSUCCESSFUL, "CAN'T ESCAPE!" PRINTS, AND THE OPPONENT ATTACKS

	REGARDLESS OF THE OPTION, THE WILD POKEMON IS ADDED TO THE ARRAY OF MAPS.
	IF THE USER'S POKEMON FIGHTS, IT IS ADDED TO THE ARRAY
	THE ARRAY IS FIRST SORTED BY THE POKEMON'S SPEED, AND THEN BY THE MOVE'S PRIORITY

	IF THE OPPONENT'S POKEMON FAINTS, THEN THE USER'S POKEMON GAINS EXPERIENCE

	IF ONE OF THE POKEMON ON EITHER SIDE FAINTS, THERE IS A CHECK TO SEE IF ALL OF
	THE POKEMON IN EITHER ONE OF THE ARRAYS IS FAINTED; IF SO, THEN THE BATTLE IS OVER


	*/

    public static void setTrainer(Trainer trainer) {
        opponent = trainer;
    }

    public static void battleSetup() {
        //THE SETUP OF THE BATTLE WILL BE DETERMINED BY WHETHER OR NOT THE
        //TRAINER IS NULL

        //Draw trainer icon at 8, 40
        //Draw roster icon at 72, 80
        /*
        Start the first pokemon party icon at 89, 81
        The second is at 97, 81
        The third, 105, 81
        The fourth, 113, 81
        The fifth, 121, 81
        The sixth, 129, 81
         */

        if (opponent == null) {
            canCatchPokemon = true;
            /*
            After transition, print the opposing pokemon's name at 7, 0;
            Print the level at 39, 9
            Draw foe's health info at 11, 10
            */
            System.out.println("A wild " + foePokemon.getPokemonName()
                    + " appeared!");
        } else {
            canCatchPokemon = false;
            //Print roster icon at 11, 16
            /*
            Print first party icon at 65, 17
            The second icon at 57, 17
            The third, at 49, 17
            The fourth, at 41, 17
            The fifth, at 33, 17
            The sixth, at 25, 17
             */
            System.out.println(opponent.getTrainerClass() + " wants to fight!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {}
            System.out.println(opponent.getTrainerClass() + " sent out "
                    + foePokemon.getPokemonName() + "!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {}
        }
        /*
        Print your pokemon's name at 79, 56
        Draw health bar at 72, 66
        Level is printed at 120, 65
        Current hp printed at 89, 81 if 3 digits, 96, 81 if 2 digits
        Total hp printed at 121, 81 if 3 digits, 128, 81 if 2 digits
         */
        battleIsOver = false;
        moves = new ArrayList<>(Arrays.asList(foePokemon.getMoveset()));
        moves.removeAll(Collections.singleton(Move.BLANK));
        setStats();
        participants.add(leadPokemon);
        UserInput.C = 1;
        remainingPokemon = new ArrayList<>(Arrays.asList(pokemonParty));
        remainingPokemon.removeAll(Collections.singleton(null));
        oppRemainingPokemon = new ArrayList<>(Arrays.asList(pokemonOpponent));
        oppRemainingPokemon.removeAll(Collections.singleton(null));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {}
    }

    //FOR TESTING ONLY!!
    public static void setLeadPokemon(Pokemon pokemon) {
        leadPokemon = pokemon;
    }

    public static Pokemon[] battle(Pokemon[] partyPokemon, Pokemon[] opposingPokemon) {
        pokemonParty = partyPokemon;
        pokemonOpponent = opposingPokemon;
        leadPokemon = partyPokemon[0];
        foePokemon = opposingPokemon[0];
        battleSetup();
        while(!battleIsOver) {
            System.out.println(foePokemon.getPokemonName() + "\tLv:" + foePokemon
                    .getStatistics().getLevel());
            System.out.println("HP: " + foePokemon.getStatistics()
                    .getCurrentHealthPoints() + "/" + foePokemon.getStatistics()
                    .getHealthPoints());
            while (!choiceMade) {
                if (usingMove.containsKey(leadPokemon)) {
                    if (usingMove.get(leadPokemon).getName().equals("Fire Spin")) {
                        choiceMade = UserInput.selectOption();
                        if (choiceMade && UserInput.choice == 1) {
                            UserInput.C = 0;
                            speedOrder.add(leadPokemon);
                            pokemonOrder.put(leadPokemon, leadPokemon.getMoveset()[UserInput
                                    .moveIndex]);
                        } else if (UserInput.choice == 4) {
                            if (opponent == null) {
                                battleIsOver = choiceMade;
                                choiceMade = true;
                            }
                        }
                    } else {
                        choiceMade = true;
                        speedOrder.add(leadPokemon);
                        pokemonOrder.put(leadPokemon, leadPokemon.getMoveset()[UserInput
                                .moveIndex]);
                    }
                } else {
                    choiceMade = UserInput.selectOption();
                    if (choiceMade && UserInput.choice == 1) {
                        UserInput.C = 0;
                        speedOrder.add(leadPokemon);
                        pokemonOrder.put(leadPokemon, leadPokemon.getMoveset()[UserInput
                                .moveIndex]);
                    } else if (UserInput.choice == 4) {
                        if (opponent == null) {
                            battleIsOver = choiceMade;
                            choiceMade = true;
                        }
                    }
                }
            }
            int previousMove = opposingMoveToUse;
            opposingMoveToUse = new Random().nextInt(moves.size());
            if (usingMove.get(foePokemon) != null) {
                opposingMoveToUse = previousMove;
            }
            while (foePokemon.getPowerPointsSet().get(foePokemon.getMoveset()
                    [opposingMoveToUse]) < 0) {
                opposingMoveToUse = new Random().nextInt(moves.size());
            }
            speedOrder.add(foePokemon);
            pokemonOrder.put(foePokemon, foePokemon.getMoveset()[opposingMoveToUse]);
            Collections.sort(speedOrder, new SpeedComparator());
            Collections.sort(speedOrder, new PriorityComparator(pokemonOrder));
            for (Pokemon pokemon : speedOrder) { //NEEDS TO BE UPDATED FOR DOUBLE BATTLES
                if (pokemon.equals(leadPokemon) && !pokemon.getStatistics()
                        .getIsFainted() && !battleIsOver && !foePokemon
                        .getStatistics().getIsFainted()) {
                    if (UserInput.moveIndex == -1) {
                        System.out.println(Battle.leadPokemon.getNickname()
                                + " has no moves left!");
                        moveToUse = -1;
                    } else {
                        moveToUse =  UserInput.moveIndex;
                    }
                    Pokemon[] updated = attack(leadPokemon, foePokemon, moveToUse);
                    leadPokemon = updated[0];
                    foePokemon = updated[1];
                    if (pokemon.getStatistics().getIsBurned() && !foePokemon
                            .getStatistics().getIsFainted()) {
                        System.out.println(pokemon.getPokemonName() + "'s hurt"
                                + " by the burn!");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {}
                        pokemon.getStatistics().setCurrentHealthPoints(-((int)
                                Math.round(pokemon.getStatistics().getHealthPoints()
                                        / 16.0)));
                    } else if (pokemon.getStatistics().getIsPoisoned() &&
                            !foePokemon.getStatistics().getIsFainted()) {
                        System.out.println(pokemon.getPokemonName() + "'s hurt"
                                + " by poison!");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {}
                        pokemon.getStatistics().setCurrentHealthPoints(-((int)
                            Math.round(pokemon.getStatistics().getHealthPoints()
                            / 16.0)));
                    }
                    if (foePokemon.getStatistics().getIsFainted()) {
                        double a = opponent == null ? 1 : 1.5;
                        double s = participants.size();
                        int exp = Calculations.calculateExp(foePokemon, a, s);
                        for (Pokemon participant : participants) {
                            System.out.println(participant.getNickname() + " gained "
                                    + exp + " experience points!");
                            participant.getStatistics().gainExperience(exp);
                            //Upon getting knocked out (Self-destruct) and another pokemon had been switched out,
                            //only the other pokemon receives points?
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {}
                        }
                        oppRemainingPokemon.remove(foePokemon);
                        battleIsOver = oppRemainingPokemon.size() == 0;
                        if (oppRemainingPokemon.size() > 0) {
                            participants.clear();
                            foePokemon = oppRemainingPokemon.get(0);
                            boolean switched = false;
                            if (remainingPokemon.size() > 0) {
                                switched = UserInput.switchPokemon();
                            }
                            System.out.println(opponent.getTrainerClass() + " "
                                    + opponent.getName() + " sent out "
                                    + foePokemon.getPokemonName() + "!");
                            if (switched) {
                                System.out.println(UserInput.lastPokemon
                                        .getPokemonName() + ", that's enough! "
                                        + "Come back!");
                                System.out.println("Go! " + leadPokemon
                                        .getPokemonName());
                            }
                            participants.add(leadPokemon);
                        }
                    }
                } else if (!pokemon.getStatistics().getIsFainted() && !battleIsOver
                        && !foePokemon.getStatistics().getIsFainted()) {
                    Pokemon[] updated = attack(foePokemon, leadPokemon, opposingMoveToUse);
                    foePokemon = updated[0];
                    leadPokemon = updated[1];
                    if (!foePokemon.getStatistics().getIsFainted()) {
                        if (foePokemon.getStatistics().getIsBurned()) {
                            System.out.println("Enemy " + foePokemon.getPokemonName()
                                    + "'s hurt by the burn!");
                            foePokemon.getStatistics().setCurrentHealthPoints(-((int)
                                    Math.round(pokemon.getStatistics().getHealthPoints()
                                            / 16.0)));
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {}
                        } else if (foePokemon.getStatistics().getIsPoisoned()) {
                            System.out.println("Enemy " + foePokemon.getPokemonName()
                                    + "'s hurt by poison!");
                            foePokemon.getStatistics().setCurrentHealthPoints(-((int)
                                    Math.round(pokemon.getStatistics().getHealthPoints()
                                            / 16.0)));
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {}
                        }
                    }

                    if(usingMove.containsValue(Move.LEECH_SEED)) {
                        String name = "";
                        if (usingMove.get(leadPokemon) != null) {
                            if (usingMove.get(leadPokemon).getName().equals("Leech Seed")) {
                                int hp = Calculations.calculateSeedDamage(pokemon);
                                leadPokemon.getStatistics().setCurrentHealthPoints(-hp);
                                foePokemon.getStatistics().setCurrentHealthPoints(hp);
                                name = name + pokemon.getPokemonName();
                            }
                        } else if (usingMove.get(foePokemon) != null){
                            if (usingMove.get(foePokemon).getName()
                                    .equals("Leech Seed")) {
                                int hp = Calculations.calculateSeedDamage(foePokemon);
                                foePokemon.getStatistics().setCurrentHealthPoints(-hp);
                                leadPokemon.getStatistics().setCurrentHealthPoints(hp);
                                name = name + "Enemy " + foePokemon.getPokemonName();
                            }
                        }
                        System.out.println("Leech seed saps " + name + "!");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {}
                    }

                    if (leadPokemon.getStatistics().getIsFainted()) {
                        participants.remove(leadPokemon);
                        remainingPokemon.remove(leadPokemon);
                        battleIsOver = remainingPokemon.size() == 0;
                        if (remainingPokemon.size() > 0) {
                            boolean replacementFound = false;
                            while (!replacementFound) {
                                replacementFound = UserInput.selectPokemon();
                            }
                            System.out.println("Go! " + leadPokemon.getPokemonName()
                                    + "!");
                        } else {
                            System.out.println("Player is out of usable pokemon!");
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {}
                            System.out.println("Player whited out!");
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {}
                        }
                    }
                }
            }
            leadPokemon.getStatistics().setIsFlinching(false);
            foePokemon.getStatistics().setIsFlinching(false);
            choiceMade = false;
            speedOrder.clear();
            turn++;
        }
        if (opponent != null) {
            System.out.println("Player defeated " + opponent.getTrainerClass()
                    + "!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {}
            System.out.println("Received " + opponent.getPrizeMoney() + " for"
                    + " winning!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {}
        }
        participants.clear();
        opponent = null;
        return partyPokemon;
    }
}
