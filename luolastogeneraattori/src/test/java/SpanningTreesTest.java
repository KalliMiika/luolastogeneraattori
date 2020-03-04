
import luolastogeneraattori.objects.Corridor;
import luolastogeneraattori.objects.Room;
import luolastogeneraattori.utils.CorridorList;
import luolastogeneraattori.utils.Delaunay;
import luolastogeneraattori.utils.SpanningTrees;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
public class SpanningTreesTest {
    
    public SpanningTreesTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void basicTest() {
        Room[] rooms = new Room[10];
        for (int i = 0; i < rooms.length; i++) {
            rooms[i] = Room.generateRandomRoom(i);
        }
        CorridorList corridors = new Delaunay().triangulate(rooms);
        corridors = new SpanningTrees().basic(rooms, corridors, 10);
        boolean[] help = new boolean[rooms.length];
        for (Corridor c : corridors.toArray()) {
            help[c.getFrom().getId()] = true;
            help[c.getTo().getId()] = true;
        }
        for (boolean b : help) {
            assertTrue(b);
        }
    }
    
    @Test
    public void randomTest() {
        Room[] rooms = new Room[10];
        for (int i = 0; i < rooms.length; i++) {
            rooms[i] = Room.generateRandomRoom(i);
        }
        CorridorList corridors = new Delaunay().triangulate(rooms);
        corridors = new SpanningTrees().random(rooms, corridors, 10);
        boolean[] help = new boolean[rooms.length];
        for (Corridor c : corridors.toArray()) {
            help[c.getFrom().getId()] = true;
            help[c.getTo().getId()] = true;
        }
        for (boolean b : help) {
            assertTrue(b);
        }
    }
}
