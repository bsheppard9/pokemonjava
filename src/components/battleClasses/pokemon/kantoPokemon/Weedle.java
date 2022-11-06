package components.battleClasses.pokemon.kantoPokemon;

import components.battleClasses.ExperienceType;
import components.battleClasses.Move;
import components.battleClasses.Type;
import components.battleClasses.pokemon.Pokemon;

import java.util.ArrayList;

public class Weedle extends Pokemon{

    private ArrayList<Move> learnset = new ArrayList<>();
    private static int[] levels = {1, 1};
    private Move[] moveset;

    public Weedle(int startingLevel) {
        super("Weedle", "Weedle", 13, startingLevel, 40, 35, 30, 20, 50,
                ExperienceType.MEDIUM_FAST, 255, 7, 52, levels, Type.BUG, Type.POISON);
        createLearnset();
        createMoveset(startingLevel);
        this.moveset = super.getMoveset();
    }

    @Override
    public void createLearnset() {

        ArrayList<Move> learnset = new ArrayList<>();
        learnset.add(Move.TACKLE);
        learnset.add(Move.POISON_STING);
        super.setLearnset(learnset);

    }
}
