
import components.battleClasses.Move;
import components.battleClasses.Type;
import components.battleClasses.pokemon.kantoPokemon.Pikachu;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PokemonTest {
    Pikachu pikachu1;
    Pikachu pikachu2;
    Pikachu pikachu3;

    @Before
    public void setUp() {
        pikachu1 = new Pikachu(5);
        pikachu2 = new Pikachu(30);
        pikachu3 = new Pikachu(81);
    }

    @Test
    public void testCreateMoveset() {
        assertEquals(true, pikachu1.alreadyHasMove(Move.GROWL));
        assertEquals(true, pikachu1.alreadyHasMove(Move.THUNDER_SHOCK));

        assertEquals(true, pikachu2.alreadyHasMove(Move.QUICK_ATTACK));
        assertEquals(true, pikachu2.alreadyHasMove(Move.DOUBLE_TEAM));
        assertEquals(true, pikachu2.alreadyHasMove(Move.SLAM));
        assertEquals(true, pikachu2.alreadyHasMove(Move.THUNDERBOLT));

        assertEquals(true, pikachu3.alreadyHasMove(Move.THUNDERBOLT));
        assertEquals(true, pikachu3.alreadyHasMove(Move.AGILITY));
        assertEquals(true, pikachu3.alreadyHasMove(Move.THUNDER));
        assertEquals(true, pikachu3.alreadyHasMove(Move.LIGHT_SCREEN));
    }

    @Test
    public void testGetPokemonName() {
        assertEquals("Pikachu", pikachu1.getPokemonName());
        assertEquals(pikachu1.getPokemonName(), pikachu2.getPokemonName());
        assertEquals(pikachu2.getPokemonName(), pikachu3.getPokemonName());
    }

    @Test
    public void testGetType() {
        assertEquals(Type.ELECTRIC, pikachu1.getType()[0]);
        assertEquals(Type.ELECTRIC, pikachu2.getType()[0]);
        assertEquals(Type.ELECTRIC, pikachu3.getType()[0]);
    }

    @Test
    public void testGetIDNumber() {
    }

    @Test
    public void testGetCatchRate() {
        assertEquals(190, pikachu1.getCatchRate());
        assertEquals(pikachu1.getCatchRate(), pikachu2.getCatchRate());
        assertEquals(pikachu2.getCatchRate(), pikachu3.getCatchRate());
    }

    @Test
    public void testGetThresholdLevel() {
        assertEquals(-1, pikachu1.getThresholdLevel());
        assertEquals(pikachu1.getThresholdLevel(), pikachu2.getThresholdLevel());
        assertEquals(pikachu2.getThresholdLevel(), pikachu3.getThresholdLevel());
    }

    @Test
    public void testEquals() {
        assertEquals(true, pikachu1.equals(pikachu1));
        assertEquals(false, pikachu1.equals(pikachu2));
        assertEquals(false, pikachu1.equals(pikachu3));
        assertEquals(false, pikachu2.equals(pikachu3));
    }
}