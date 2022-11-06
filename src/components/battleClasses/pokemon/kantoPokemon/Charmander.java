package components.battleClasses.pokemon.kantoPokemon;

import components.battleClasses.ExperienceType;
import components.battleClasses.Move;
import components.battleClasses.Type;
import components.battleClasses.pokemon.Pokemon;

import java.util.ArrayList;

public class Charmander extends Pokemon{

    private ArrayList<Move> learnset = new ArrayList<>();
    private static int[] levels = {1, 1, 9, 15, 22, 30, 38, 46};
    private Move[] moveset;

    public Charmander(int startingLevel) {
        super("Charmander", "Charmander", 4, startingLevel, 39, 52, 43, 50, 65,
                ExperienceType.MEDIUM_SLOW, 45, 16, 65, levels, Type.FIRE);
        createLearnset();
        createMoveset(startingLevel);
        this.moveset = super.getMoveset();
    }

    @Override
    public void createLearnset() {

        ArrayList<Move> learnset = new ArrayList<>();
        learnset.add(Move.SCRATCH);
        learnset.add(Move.GROWL);
        learnset.add(Move.EMBER);
        learnset.add(Move.LEER);
        learnset.add(Move.RAGE);
        learnset.add(Move.SLASH);
        learnset.add(Move.FLAMETHROWER);
        learnset.add(Move.FIRE_SPIN); //Test when the user is slower than the target
        super.setLearnset(learnset);

    }

}
