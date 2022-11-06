package components.battleClasses.pokemon.kantoPokemon;

import components.battleClasses.ExperienceType;
import components.battleClasses.Move;
import components.battleClasses.Type;
import components.battleClasses.pokemon.Pokemon;

import java.util.ArrayList;

public class Eevee extends Pokemon {

    private ArrayList<Move> learnset = new ArrayList<>();
    private static int[] levels = {1, 1, 8, 16, 23, 30, 36, 42};
    private Move[] moveset;

    public Eevee(int startingLevel) {
        super("Eevee", "Eevee", 133, startingLevel, 55, 55, 50, 65, 55,
                ExperienceType.MEDIUM_FAST, 45, -1, 92, levels, Type.NORMAL);
        createLearnset();
        createMoveset(startingLevel);
        this.moveset = super.getMoveset();
    }

    @Override
    public void createLearnset() {

        ArrayList<Move> learnset = new ArrayList<>();
        learnset.add(Move.TACKLE);
        learnset.add(Move.TAIL_WHIP);
        learnset.add(Move.SAND_ATTACK);
        learnset.add(Move.GROWL);
        learnset.add(Move.QUICK_ATTACK);
        learnset.add(Move.BITE);
        learnset.add(Move.FOCUS_ENERGY);
        learnset.add(Move.TAKE_DOWN);
        super.setLearnset(learnset);

    }

}
