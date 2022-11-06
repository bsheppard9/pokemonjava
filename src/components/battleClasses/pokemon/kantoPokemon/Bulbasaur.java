package components.battleClasses.pokemon.kantoPokemon;

import components.battleClasses.ExperienceType;
import components.battleClasses.Move;
import components.battleClasses.Type;
import components.battleClasses.pokemon.Pokemon;

import java.util.ArrayList;

public class Bulbasaur extends Pokemon {

    private ArrayList<Move> learnset = new ArrayList<>();
    private static int[] levels = {1, 1, 7, 13, 20, 27, 34, 41, 48};
    private Move[] moveset;

    public Bulbasaur(int startingLevel) {
        super("Bulbasaur", "Bulbasaur", 1, startingLevel, 45, 49, 49, 65, 45,
                ExperienceType.MEDIUM_SLOW, 45, 16, 64, levels, Type.GRASS, Type.POISON);
        createLearnset();
        createMoveset(startingLevel);
        this.moveset = super.getMoveset();

    }

    @Override
    public void createLearnset() {

        ArrayList<Move> learnset = new ArrayList<>();
        learnset.add(Move.TACKLE);
        learnset.add(Move.GROWL);
        learnset.add(Move.LEECH_SEED);
        learnset.add(Move.VINE_WHIP);
        learnset.add(Move.POISON_POWDER);
        learnset.add(Move.RAZOR_LEAF);
        learnset.add(Move.GROWTH);
        learnset.add(Move.SLEEP_POWDER);
        learnset.add(Move.SOLAR_BEAM);
        super.setLearnset(learnset);

    }

}
