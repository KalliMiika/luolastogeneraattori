
import luolastogeneraattori.objects.Cave;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class CaveTest {
    
    public CaveTest() {
    }
    
    Cave cave;
    @Before
    public void setUp() {
        cave = new Cave();
    }
    
    @Test
    public void emptyMapIsEmpty() {
        char[][] map = Cave.generateEmptyMap();
        int counter = 0;
        for (char[] ca : map) {
            for (char c : ca) {
                if (c != '#') {
                    counter++;
                }
            }
        }
        assertEquals(0, counter);
    }
}
