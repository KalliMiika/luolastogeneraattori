import luolastogeneraattori.objects.Corridor;
import luolastogeneraattori.objects.Room;
import luolastogeneraattori.utils.CorridorList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CorridorListTest {
    
    public CorridorListTest() {
    }
    
    CorridorList cl;
    Corridor c1;
    Corridor c2;
    Corridor c3;
    
    @Before
    public void setUp() {
        cl = new CorridorList();
        c1 = new Corridor(Room.generateRandomRoom(0), Room.generateRandomRoom(1));
        c2 = new Corridor(Room.generateRandomRoom(2), Room.generateRandomRoom(3));
        c3 = new Corridor(Room.generateRandomRoom(4), Room.generateRandomRoom(5));
    }
    
    @Test
    public void addGetAndRemoveTest() {
        int s = cl.size();
        cl.add(c1);
        cl.add(c2);
        cl.add(c3);
        assertEquals(3, cl.size());
        assertTrue(cl.contains(c1));
        assertTrue(cl.contains(c2));
        assertTrue(cl.contains(c3));
        Corridor c = cl.get(0);
        assertEquals(c1, c);
        cl.remove(c2);
        assertEquals(2, cl.size());
        assertFalse(cl.contains(c2));
    }
    
    @Test
    public void expandMustWorkIfNoCrashTest() {
        for (int i = 0; i < 100; i++) {
            cl.add(new Corridor(Room.generateRandomRoom(i * 2), Room.generateRandomRoom(i * 2 + 1)));
        }
        assertTrue(cl.size() > 8);
        assertEquals(100, cl.size());
    }
    
    @Test
    public void toArrayTest() {
        int roomsToGenerate = 100;
        for (int i = 0; i < roomsToGenerate; i++) {
            cl.add(new Corridor(Room.generateRandomRoom(i * 2), Room.generateRandomRoom(i * 2 + 1)));
        }
        assertEquals(100, cl.toArray().length);
        for (Corridor c : cl.toArray()) {
            assertTrue(cl.contains(c));
        }
    }
    
    @Test
    public void removeDuplicatesTest() {
        Room a = Room.generateRandomRoom(0);
        Room b = Room.generateRandomRoom(1);
        Corridor ab = new Corridor(a, b);
        Corridor ba = new Corridor(b, a);
        CorridorList corridors = new CorridorList();
        corridors.add(ab);
        corridors.add(ba);
        assertEquals(2, corridors.size());
        corridors = corridors.clearDuplicates();
        assertEquals(1, corridors.size());
    }
}
