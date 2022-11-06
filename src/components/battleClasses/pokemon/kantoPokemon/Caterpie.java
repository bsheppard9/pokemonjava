package components.battleClasses.pokemon.kantoPokemon;

import components.battleClasses.ExperienceType;
import components.battleClasses.Move;
import components.battleClasses.Type;
import components.battleClasses.pokemon.Pokemon;

import java.util.ArrayList;

public class Caterpie extends Pokemon {

    private ArrayList<Move> learnset = new ArrayList<>();
    private static int[] levels = {1, 1};
    private Move[] moveset;

    public Caterpie(int startingLevel) {
        super("Caterpie", "Caterpie", 10, startingLevel, 45, 30, 35, 20, 45,
                ExperienceType.MEDIUM_FAST, 255, 7, 53, levels, Type.BUG);
        createLearnset();
        createMoveset(startingLevel);
        this.moveset = super.getMoveset();
    }

    @Override
    public void createLearnset() {

        ArrayList<Move> learnset = new ArrayList<>();
        learnset.add(Move.TACKLE);
        learnset.add(Move.STRING_SHOT);
        super.setLearnset(learnset);

    }
}
