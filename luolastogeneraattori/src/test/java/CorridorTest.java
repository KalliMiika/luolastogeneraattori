
import luolastogeneraattori.objects.Cave;
import luolastogeneraattori.objects.Corridor;
import luolastogeneraattori.objects.Point;
import luolastogeneraattori.objects.Room;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


public class CorridorTest {
    
    public CorridorTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void getterTest() {
        Cave cave = new Cave();
        Room r1 = new Room(new Point(10, 10), 3, 3, 6, 0);
        Room r2 = new Room(new Point(40, 10), 3, 3, 6, 1);
        Corridor c = new Corridor(r1, r2);
        assertEquals(c.getFrom().getId(), r1.getId());
        assertEquals(c.getTo().getId(), r2.getId());
    }
    
    @Test
    public void drawCorridorWithPriorityListTest() {
        Cave cave = new Cave();
        Room r1 = new Room(new Point(10, 10), 3, 3, 6, 0);
        r1.drawRoom();
        Room r2 = new Room(new Point(40, 10), 3, 3, 6, 1);
        r2.drawRoom();
        Corridor c = new Corridor(r1, r2);
        c.drawCorridorWithPriorityList();
        assertEquals('+', cave.getMap()[10][12]);
        assertEquals('#', cave.getMap()[10][13]);
        assertEquals('#', cave.getMap()[10][15]);
        assertEquals('#', cave.getMap()[10][18]);
        assertEquals('#', cave.getMap()[10][21]);
        assertEquals('#', cave.getMap()[10][24]);
        assertEquals('#', cave.getMap()[10][27]);
        assertEquals('#', cave.getMap()[10][30]);
        assertEquals('#', cave.getMap()[10][33]);
        assertEquals('#', cave.getMap()[10][35]);
        assertEquals('+', cave.getMap()[10][38]);
    }
    @Test
    public void drawCorridorTest() {
        Cave cave = new Cave();
        Room r1 = new Room(new Point(10, 10), 3, 3, 6, 0);
        r1.drawRoom();
        Room r2 = new Room(new Point(40, 11), 3, 3, 6, 1);
        r2.drawRoom();
        Corridor c = new Corridor(r1, r2);
        c.drawCorridor();
        assertEquals('+', cave.getMap()[10][12]);
        assertEquals('+', cave.getMap()[10][38]);
    }
    
    @Test
    public void drawCorridorWithPriorityListAllowDiagonal() {
        Cave cave = new Cave();
        Room r1 = new Room(new Point(10, 30), 3, 3, 6, 0);
        r1.drawRoom();
        Room r2 = new Room(new Point(30, 10), 3, 3, 6, 1);
        r2.drawRoom();
        Corridor c = new Corridor(r1, r2);
        c.drawCorridorWithPriorityListAllowDiagonal();
        assertEquals('+', cave.getMap()[28][11]);
        assertEquals('#', cave.getMap()[27][12]);
        assertEquals('#', cave.getMap()[26][13]);
        assertEquals('#', cave.getMap()[25][14]);
        assertEquals('#', cave.getMap()[24][15]);
        assertEquals('#', cave.getMap()[23][16]);
        assertEquals('#', cave.getMap()[22][17]);
        assertEquals('#', cave.getMap()[21][18]);
        assertEquals('#', cave.getMap()[20][19]);
        assertEquals('#', cave.getMap()[19][20]);
        assertEquals('+', cave.getMap()[11][28]);
    }
}
