
import luolastogeneraattori.objects.Point;
import luolastogeneraattori.objects.Room;
import luolastogeneraattori.utils.RoomStack;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

public class RoomStackTest {
    
    public RoomStackTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void roomStackTest() {
        RoomStack rs = new RoomStack();
        assertNull("empty stack should return null", rs.pop());
        rs.put(new Room(new Point(0, 0), 0, 0, 0, 0));
        rs.put(new Room(new Point(1, 1), 0, 0, 0, 1));
        rs.put(new Room(new Point(2, 2), 0, 0, 0, 2));
        rs.put(new Room(new Point(3, 3), 0, 0, 0, 3));
        rs.put(new Room(new Point(4, 4), 0, 0, 0, 4));
        rs.put(new Room(new Point(5, 5), 0, 0, 0, 5));
        assertEquals(5, rs.pop().getId());
        assertEquals(4, rs.pop().getId());
        assertEquals(3, rs.pop().getId());
        assertEquals(2, rs.pop().getId());
        assertEquals(1, rs.pop().getId());
        assertEquals(0, rs.pop().getId());
    }
}
