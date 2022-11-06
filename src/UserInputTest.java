import components.battleClasses.pokemon.Pokemon;
import components.battleClasses.pokemon.kantoPokemon.Pikachu;
import components.Battle;
import components.UserInput;

public class UserInputTest {

    public static void main(String[] args) {
        Pikachu pikachu = new Pikachu(5);
        boolean test1 = testNicknameEqualsName(pikachu);

        UserInput.changeNickname(pikachu);

        boolean test2 = testNicknameEqualsName(pikachu);

        UserInput.changeNickname(pikachu);
        boolean test3 = testNicknameDiffersFromName(pikachu,"Sparky");

        //In this case, select Fight, and then exit; Should return false
        Battle.setLeadPokemon(pikachu);
        boolean test4 = !testSelectOption();

        //In this case, select Run; Should return true
        boolean test5 = testSelectOption();

        //In this case, select Fight, and then any of 1 through 4; Should return true
        boolean test6 = testSelectOption();

        //NOTE: UNABLE TO DO FIGHT, EXIT, FIGHT; THIS MAY PROVE TO BE AN ISSUE...

        boolean[] results = new boolean[] {test1, test2, test3, test4, test5,
            test6};
        for (boolean result : results) {
            System.out.println("Result of test: " + (result == true ? "PASS"
                    : "FAIL"));
        }
    }

    public static boolean testNicknameEqualsName(Pokemon pokemon) {
        boolean test2a = pokemon.getPokemonName().equals(pokemon.getNickname());
        boolean test2b = pokemon.getPokemonName().equals(pokemon.getPokemonName());
        return test2a && test2b;
    }

    public static boolean testNicknameDiffersFromName(Pokemon pokemon, String name) {
        boolean test3a = !pokemon.getPokemonName().equals(pokemon.getNickname());
        boolean test3b = pokemon.getNickname().equals(name);
        boolean test3c = !pokemon.getPokemonName().equals(name);
        return test3a && test3b && test3c;
    }

    public static boolean testSelectOption() {
        return UserInput.selectOption();
    }
}
