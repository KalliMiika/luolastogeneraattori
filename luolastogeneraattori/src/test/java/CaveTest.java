
import luolastogeneraattori.objects.Cave;
import static luolastogeneraattori.objects.Cave.HEIGHT;
import static luolastogeneraattori.objects.Cave.WIDTH;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
    
    @Test
    public void generateRandomMapWorksCorrectlyish() {
        int Pwall = 40;
        char[][] map = Cave.generateRandomMap(Pwall);
        boolean bordersAreWalls = true;
        for (int column, row = 0; row < HEIGHT; row++) {
            for (column = 0; column < WIDTH; column++) {
                if (row == 0 || column == 0 || row == HEIGHT - 1 || column == WIDTH - 1) {
                    if(map[row][column] != ' '){
                        bordersAreWalls = false;
                    }
                } 
            }
        }
        assertTrue(bordersAreWalls);
    }
}
