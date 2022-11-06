import components.battleClasses.Move;
import components.battleClasses.Type;
import components.battleClasses.pokemon.kantoPokemon.Pikachu;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoveTest {

    Move move1;
    Move move2;
    Move move3;
    Move move4;
    Pikachu pikachu;

    @Before
    public void setUp() throws Exception {
        pikachu = new Pikachu(30);
        move1 = pikachu.getMoveset()[0];
        move2 = pikachu.getMoveset()[1];
        move3 = pikachu.getMoveset()[2];
        move4 = pikachu.getMoveset()[3];
    }

    @Test
    public void testPowerPoints() {
        assertEquals(30, move1.getPowerPoints());
        assertEquals(Type.NORMAL, move1.getType());
        boolean used = pikachu.useMove(0, 15);
        int pp = pikachu.getPowerPointsSet().get(move1);
        assertEquals(true, used);
        assertEquals(15, pp);
        used = pikachu.useMove(0, 1);
        pp = pikachu.getPowerPointsSet().get(move1);
        assertEquals(true, used);
        assertEquals(14, pp);


        assertEquals(15, move2.getPowerPoints());
        assertEquals(Type.NORMAL, move2.getType());
        used = pikachu.useMove(1, 15);
        pp = pikachu.getPowerPointsSet().get(move2);
        assertEquals(true, used);
        assertEquals(0, pp);
        used = pikachu.useMove(1, 1);
        pp = pikachu.getPowerPointsSet().get(move2);
        assertEquals(false, used);
        assertEquals(0, pp);

        assertEquals(20, move3.getPowerPoints());
        assertEquals(Type.NORMAL, move3.getType());
        used = pikachu.useMove(2, 4);
        pp = pikachu.getPowerPointsSet().get(move3);
        assertEquals(true, used);
        assertEquals(16, pp);
        used = pikachu.useMove(2, 7);
        pp = pikachu.getPowerPointsSet().get(move3);
        assertEquals(true, used);
        assertEquals(9, pp);

        assertEquals(15, move4.getPowerPoints());
        assertEquals(Type.ELECTRIC, move4.getType());
        used = pikachu.useMove(3, 15);
        pp = pikachu.getPowerPointsSet().get(move4);
        assertEquals(true, used);
        assertEquals(0, pp);
        used = pikachu.useMove(3, 1);
        pp = pikachu.getPowerPointsSet().get(move4);
        assertEquals(false, used);
        assertEquals(0, pp);
        used = pikachu.useMove(3, 1);
        pp = pikachu.getPowerPointsSet().get(move4);
        assertEquals(false, used);
        assertEquals(0, pp);
    }
}