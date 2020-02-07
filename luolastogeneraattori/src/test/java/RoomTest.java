

import luolastogeneraattori.cavegenerators.CaveGenerator;
import luolastogeneraattori.objects.Cave;
import luolastogeneraattori.objects.Point;
import luolastogeneraattori.objects.Room;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
public class RoomTest {
    
    public RoomTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void drawCircleWorksCorrectly() {
        char[][] testMap = {
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
        };
        Point[] testPoints = {
            new Point(7, 3),
            new Point(5, 4),
            new Point(6, 4),
            new Point(8, 4),
            new Point(9, 4),
            new Point(4, 5),
            new Point(10, 5),
            new Point(3, 6),
            new Point(11, 6),
            new Point(3, 7),
            new Point(11, 7),
            new Point(2, 8),
            new Point(12, 8),
            new Point(3, 9),
            new Point(11, 9),
            new Point(3, 10),
            new Point(11, 10),
            new Point(4, 11),
            new Point(10, 11),
            new Point(5, 12),
            new Point(6, 12),
            new Point(8, 12),
            new Point(9, 12),
            new Point(7, 13)
        };
        Cave cave = new Cave(testMap);
        Room room = new Room(new Point(7, 8), 3, 3, 5, 0);
        room.drawCircle();
        testMap = cave.getMap();
        boolean result = true;
        for (Point p : testPoints) {
            if (testMap[p.getY()][p.getX()] != '*') {
                result = false;
            }
        }
        assertTrue(result);
    }
    
    @Test
    public void drawRoomWorksCorrectly() {
        char[][] testMap = {
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
        };
        Point[] verticalWalls = {
            new Point(5, 6),
            new Point(9, 6),
            new Point(5, 7),
            new Point(9, 7),
            new Point(5, 8),
            new Point(9, 8),
            new Point(5, 9),
            new Point(9, 9),
            new Point(5, 10),
            new Point(9, 10)
        };
        Point[] horizontalWalls = {
            new Point(6, 6),
            new Point(7, 6),
            new Point(8, 6),
            new Point(6, 10),
            new Point(7, 10),
            new Point(8, 10)
        };
        Cave cave = new Cave(testMap);
        Room room = new Room(new Point(7, 8), 3, 3, 5, 0);
        room.drawRoom();
        char[][] map = cave.getMap();
        boolean result = true;
        for (Point p : verticalWalls) {
            if (map[p.getY()][p.getX()] != '|') {
                result = false;
            }
        }
        for (Point p : horizontalWalls) {
            if (map[p.getY()][p.getX()] != '-') {
                result = false;
            }
        }
        assertTrue(result);
    }
    
    @Test
    public void drawCenterWorksCorrectly() {
        CaveGenerator caveGen = new CaveGenerator();
        caveGen.generateMap(8);
        Room[] rooms = caveGen.getRooms();
        Cave cave = Cave.getInstance();
        boolean works = true;
        char[][] map = cave.getMap();
        for (Room r : rooms) {
            if (r.getId() != map[r.getCenter().getY()][r.getCenter().getX()]) {
                works = false;
            }
        }
        assertTrue(works);
    }
}
