package components.battleClasses.pokemon.kantoPokemon;

import components.battleClasses.ExperienceType;
import components.battleClasses.Move;
import components.battleClasses.Type;
import components.battleClasses.pokemon.Pokemon;

import java.util.ArrayList;

public class Squirtle extends Pokemon {

    private ArrayList<Move> learnset = new ArrayList<>();
    private static int[] levels = {1, 1, 8, 15, 22, 28, 35, 42};
    private Move[] moveset;

    public Squirtle(int startingLevel) {
        super("Squirtle", "Squirtle", 7, startingLevel, 44, 48, 65, 50, 43,
                ExperienceType.MEDIUM_SLOW, 45, 16, 66, levels, Type.WATER);
        createLearnset();
        createMoveset(startingLevel);
        this.moveset = super.getMoveset();

    }

    @Override
    public void createLearnset() {

        ArrayList<Move> learnset = new ArrayList<>();
        learnset.add(Move.TACKLE);
        learnset.add(Move.TAIL_WHIP);
        learnset.add(Move.BUBBLE);
        learnset.add(Move.WATER_GUN);
        learnset.add(Move.BITE);
        learnset.add(Move.WITHDRAW);
        learnset.add(Move.SKULL_BASH);
        learnset.add(Move.HYDRO_PUMP);
        super.setLearnset(learnset);

    }

}
