import components.Trainer;
import components.battleClasses.pokemon.Pokemon;
import components.battleClasses.pokemon.kantoPokemon.*;
import components.Battle;

public class SimulationTester {

    public static void main(String[] args) {
        Pikachu pikachu = new Pikachu(10);
        //pikachu.useMove(0, 30);
        //pikachu.useMove(1, 39);
        Eevee eevee = new Eevee(15);

        Pokemon[] party1 = new Pokemon[]{pikachu, new Eevee(42), new Bulbasaur(27),
                new Charmander(50), new Squirtle(35), new Pidgey(19)};
        Pokemon[] party2 = new Pokemon[]{eevee, null, null, null, null, null};

        /*
        Test1: Order of speed: Pikachu should attack first
        Test2: Selecting a move: Ensure...
        - index should be between 0 and 3
        - move should not be a blank
        - move should have at least 1 pp, else fail.
        - if no moves are available, use struggle
        Test3: Calculate damage/determine if fainted; Ensure...
        - if hp is 0, Pokemon should faint
        - critical hit chance computed correctly
        - damage is calculated correctly <--Test calculations
        Test4: Calculate battle statistics/updating stats <--Test calculations
        Test5: Exp. points <--Test calculations
        Test6: Priority <--Test further
        Test7: components.Trainer battle (name)/ Prize money
        Test8: More than one pokemon in party on either side (Pokemon option)
        Test9: Status conditions and their effects

        To be fixed:
        Critical hit damage should disregard status changes
        Simulation should not allow battle shift if there is only one pokemon
        Simulation can still run as long as there is one usable pokemon
        Reflect and Light Screen need to be tested
        Fire Spin needs proper PP reduction
        Account for moves with no accuracy (they shouldn't miss)
         */
        if (!party1[0].getStatistics().getIsFainted()) {
            Battle.battle(party1, party2);
        }
        Pokemon[] party3 = new Pokemon[] { new Weedle(6), new Caterpie(6), null,
                null, null, null};
        Trainer trainer = new Trainer(0, 0, "Rick", "Bug Catcher", party3, (short) 72);
        Battle.setTrainer(trainer);
        /*if (!party1[0].getStatistics().getIsFainted() && !party1[1].getStatistics()
                .getIsFainted()) {
            Battle.battle(party1, party3);
        }*/
        System.out.println("Final HP: " + party1[1].getStatistics().getCurrentHealthPoints());
        /*
        NOTES:

         */
    }

}
