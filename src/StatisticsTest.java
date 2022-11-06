import components.battleClasses.pokemon.kantoPokemon.Pikachu;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StatisticsTest {

    //YOU MUST FINISH POKEMON FIRST!!
    private Pikachu pikachu;

    @Before
    public void setUp() {
        pikachu = new Pikachu(81);
        pikachu.getStatistics().setIndividualValues(8, 1);
        pikachu.getStatistics().setIndividualValues(13, 2);
        pikachu.getStatistics().setIndividualValues(9, 3);
        pikachu.getStatistics().setIndividualValues(5, 4);
        pikachu.getStatistics().setEffortValues(22850, 0);
        pikachu.getStatistics().setEffortValues(23140, 1);
        pikachu.getStatistics().setEffortValues(17280, 2);
        pikachu.getStatistics().setEffortValues(19625, 3);
        pikachu.getStatistics().setEffortValues(24795, 4);
    }

    @Test
    public void testStatistics() {
        assertEquals(7, pikachu.getStatistics().getIndividualValues()[0]);
        assertEquals(189, pikachu.getStatistics().getHealthPoints());
        assertEquals(137, pikachu.getStatistics().getAttack());
        assertEquals(101, pikachu.getStatistics().getDefense());
        assertEquals(128, pikachu.getStatistics().getSpecial());
        assertEquals(190, pikachu.getStatistics().getSpeed());
    }

    @Test
    public void testExperiencePoints() {
        assertEquals(531441, pikachu.getStatistics().getExperiencePoints());
        assertEquals(19927, pikachu.getStatistics()
                .getExperienceNeededForNextLevel());
        pikachu.getStatistics().gainExperience(0);
        assertEquals(19927, pikachu.getStatistics()
                .getExperienceNeededForNextLevel());
        pikachu.getStatistics().gainExperience(6643);
        assertEquals(13284, pikachu.getStatistics()
                .getExperienceNeededForNextLevel());
        assertEquals(538084, pikachu.getStatistics().getExperiencePoints());
        pikachu.getStatistics().gainExperience(13286);
        assertEquals(551370, pikachu.getStatistics().getExperiencePoints());
        assertEquals(82, pikachu.getStatistics().getLevel());
        assertEquals(20417, pikachu.getStatistics()
                .getExperienceNeededForNextLevel());

    }

    //How do you add EVs after defeating a Pokemon? (GEN III onward)

    @Test
    public void testStatDecrease() {
        //Make sure that you get the right factors when using status moves.
    }

}