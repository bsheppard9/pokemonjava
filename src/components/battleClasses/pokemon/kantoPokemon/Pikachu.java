package components.battleClasses.pokemon.kantoPokemon;

import components.battleClasses.ExperienceType;
import components.battleClasses.Move;
import components.battleClasses.Type;
import components.battleClasses.pokemon.Pokemon;
import java.util.ArrayList;

public class Pikachu extends Pokemon {

    private ArrayList<Move> learnset = new ArrayList<>();
    private static int[] levels = {1, 1, 6, 8, 11, 15, 20, 26, 33, 41, 50};
    private Move[] moveset;

    public Pikachu(int startingLevel) {
        super("Pikachu", "Pikachu", 25, startingLevel, 35, 55, 30, 50, 90,
                ExperienceType.MEDIUM_FAST, 190, -1, 112, levels, Type.ELECTRIC);
        createLearnset();
        createMoveset(startingLevel);
        this.moveset = super.getMoveset();
    }

    @Override
    public void createLearnset() {

        ArrayList<Move> learnset = new ArrayList<>();
		learnset.add(Move.THUNDER_SHOCK);
		learnset.add(Move.GROWL);
		learnset.add(Move.TAIL_WHIP);
		learnset.add(Move.THUNDER_WAVE);
		learnset.add(Move.QUICK_ATTACK);
		learnset.add(Move.DOUBLE_TEAM);
		learnset.add(Move.SLAM);
		learnset.add(Move.THUNDERBOLT);
		learnset.add(Move.AGILITY);
		learnset.add(Move.THUNDER);
		learnset.add(Move.LIGHT_SCREEN);
		super.setLearnset(learnset);

    }
}
