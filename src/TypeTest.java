import components.battleClasses.Type;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TypeTest {

    private Type type1;
    private Type type2;
    private Type type3;
    private Type type4;
    private Type type5;

    @Before
    public void setUp() {
        type1 = Type.ELECTRIC;
        type2 = Type.NORMAL;
        type3 = Type.GRASS;
        type4 = Type.FIRE;
        type5 = Type.WATER;
    }

    @Test
    public void testToStringMethod() {
        assertEquals(type1.toString(), "Electric");
        assertEquals(type2.toString(), "Normal");
        assertEquals(type3.toString(), "Grass");
        assertEquals(type4.toString(), "Fire");
        assertEquals(type5.toString(), "Water");
    }
}