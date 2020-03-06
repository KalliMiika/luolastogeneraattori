
import luolastogeneraattori.objects.Room;
import luolastogeneraattori.utils.RoomList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
public class RoomListTest {
    
    public RoomListTest() {
    }
    
    RoomList rl;
    Room r1;
    Room r2;
    Room r3;
    
    @Before
    public void setUp() {
        rl = new RoomList();
        r1 = Room.generateRandomRoom(0);
        r2 = Room.generateRandomRoom(1);
        r3 = Room.generateRandomRoom(2);
    }
    
    @Test
    public void addGetAndRemoveTest() {
        int s = rl.size();
        rl.add(r1);
        rl.add(r2);
        rl.add(r3);
        assertEquals(3, rl.size());
        assertTrue(rl.contains(r1));
        assertTrue(rl.contains(r2));
        assertTrue(rl.contains(r3));
        Room r = rl.get(0);
        assertEquals(r1, r);
        rl.remove(r2);
        assertEquals(2, rl.size());
        assertFalse(rl.contains(r2));
    }
    
    @Test
    public void expandMustWorkIfNoCrashTest() {
        for (int i = 0; i < 100; i++) {
            rl.add(Room.generateRandomRoom(i));
        }
        assertTrue(rl.size() > 8);
        assertEquals(100, rl.size());
    }
    
    @Test
    public void toArrayTest() {
        int roomsToGenerate = 100;
        for (int i = 0; i < roomsToGenerate; i++) {
            Room r = Room.generateRandomRoom(i);
            rl.add(r);
        }
        assertEquals(100, rl.toArray().length);
        for (Room r : rl.toArray()) {
            assertTrue(rl.contains(r));
        }
    }
}
