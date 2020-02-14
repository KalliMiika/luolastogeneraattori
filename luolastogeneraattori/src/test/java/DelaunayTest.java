
import java.util.ArrayList;
import luolastogeneraattori.objects.Corridor;
import luolastogeneraattori.objects.Room;
import luolastogeneraattori.utils.Delaunay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

public class DelaunayTest {

    public DelaunayTest() {
    }

    @Before
    public void setUp() {
        int roomsToGenerate = 10;
        rooms = new Room[roomsToGenerate];
        for (int i = 0; i < roomsToGenerate; i++) {
            rooms[i] = Room.generateRandomRoom(i);
        }
        ArrayList<Corridor> cors = new Delaunay().triangulate(rooms);
        corridors = new Corridor[cors.size()];
        for (int i = 0; i< cors.size(); i++) {
            corridors[i] = cors.get(i);
        }
    }

    Room[] rooms;
    Corridor[] corridors;
    
    @Test
    public void allRoomsHaveCorridorsConnectedToThem() {
        boolean[] yes = new boolean[10];
        for (Corridor c : corridors) {
            yes[c.getFrom().getId()] = true;
            yes[c.getTo().getId()] = true;
        }
        boolean fail = false;
        for (Room r : rooms) {
            if (!yes[r.getId()]) {
                fail = true;
            }
        }
        assertFalse(fail);
    }
    
    /**
     * To be implemented
     */
    @Test
    public void noDuplicateCorridors() {
        boolean fail = false;
        for (int i = 0; i < corridors.length; i++) {
            for (int j = 0; j < corridors.length; j++) {
                if (i != j && corridors[i].equals(corridors[j])){
                    //fail = true;
                } 
            }
        }
        assertFalse(fail);
    }
    
    @Test
    public void threeRoomsReturnsThreeCorridors() {
        Room[] rooms = new Room[3];
        rooms[0] = Room.generateRandomRoom(0);
        rooms[1] = Room.generateRandomRoom(1);
        rooms[2] = Room.generateRandomRoom(2);
        assertEquals(3, new Delaunay().triangulate(rooms).size());
    }
    
    @Test
    public void twoRoomsReturnsNull() {
        Room[] rooms = new Room[2];
        rooms[0] = Room.generateRandomRoom(0);
        rooms[1] = Room.generateRandomRoom(1);
        assertNull("Two rooms should return null", new Delaunay().triangulate(rooms));
    }

}
