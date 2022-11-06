package components.battleClasses.pokemon.kantoPokemon;

import components.battleClasses.ExperienceType;
import components.battleClasses.Move;
import components.battleClasses.Type;
import components.battleClasses.pokemon.Pokemon;

import java.util.ArrayList;

public class Pidgey extends Pokemon{

    private ArrayList<Move> learnset = new ArrayList<>();
    private static int[] levels = {1, 5, 12, 19, 28, 36, 44};
    private Move[] moveset;

    public Pidgey(int startingLevel) {
        super("Pidgey", "Pidgey", 16, startingLevel, 45, 49, 49, 65, 45,
                ExperienceType.MEDIUM_SLOW, 255, 18, 55, levels, Type.NORMAL, Type.FLYING);
        createLearnset();
        createMoveset(startingLevel);
        this.moveset = super.getMoveset();

    }

    @Override
    public void createLearnset() {

        ArrayList<Move> learnset = new ArrayList<>();
        learnset.add(Move.GUST);
        learnset.add(Move.SAND_ATTACK);
        learnset.add(Move.QUICK_ATTACK);
        learnset.add(Move.WHIRLWIND);
        learnset.add(Move.WING_ATTACK);
        learnset.add(Move.AGILITY);
        learnset.add(Move.MIRROR_MOVE);
        super.setLearnset(learnset);

    }

}
