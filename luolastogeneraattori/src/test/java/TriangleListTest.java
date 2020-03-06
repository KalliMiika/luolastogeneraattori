
import luolastogeneraattori.objects.Room;
import luolastogeneraattori.utils.Triangle;
import luolastogeneraattori.utils.TriangleList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TriangleListTest {
    
    public TriangleListTest() {
    }
    
    TriangleList tl;
    Triangle t1;
    Triangle t2;
    Triangle t3;
    
    @Before
    public void setUp() {
        tl = new TriangleList();
        t1 = new Triangle(Room.generateRandomRoom(0), Room.generateRandomRoom(1), Room.generateRandomRoom(2));
        t2 = new Triangle(Room.generateRandomRoom(3), Room.generateRandomRoom(4), Room.generateRandomRoom(5));
        t3 = new Triangle(Room.generateRandomRoom(6), Room.generateRandomRoom(7), Room.generateRandomRoom(8));
    }
    
    @Test
    public void addGetAndRemoveTest() {
        int s = tl.size();
        tl.add(t1);
        tl.add(t2);
        tl.add(t3);
        assertEquals(3, tl.size());
        assertTrue(tl.contains(t1));
        assertTrue(tl.contains(t2));
        assertTrue(tl.contains(t3));
        Triangle t = tl.get(0);
        assertEquals(t1, t);
        tl.remove(1);
        assertEquals(2, tl.size());
        assertFalse(tl.contains(t2));
    }
    
    @Test
    public void expandMustWorkIfNoCrashTest() {
        for (int i = 0; i < 100; i++) {
            tl.add(new Triangle(Room.generateRandomRoom(i * 3), Room.generateRandomRoom(i * 3 + 1), Room.generateRandomRoom(i * 3 + 2)));
        }
        assertTrue(tl.size() > 8);
        assertEquals(100, tl.size());
    }
}
