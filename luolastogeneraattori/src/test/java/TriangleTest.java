

import luolastogeneraattori.objects.Point;
import luolastogeneraattori.objects.Room;
import luolastogeneraattori.utils.Triangle;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class TriangleTest {
    
    public TriangleTest() {
    }
    
    @Before
    public void setUp() {
        triangle = new Triangle();
        triangle.setVertex1(Room.generateRandomRoom(0));
        triangle.setVertex2(Room.generateRandomRoom(1));
        triangle.setVertex3(Room.generateRandomRoom(2));
    }
    Triangle triangle;
    
    @Test
    public void pointIsFoundInsideCircumcircle(){
        Point A = triangle.getVertex1().getCenter();
        Point B = triangle.getVertex2().getCenter();
        Point AB = new Point((A.getX() + B.getX()) / 2 , (A.getY() + B.getY()) / 2);
        assertTrue(triangle.pointIsWithinCircumCircle(AB));
    }
    
    @Test
    public void pointIsNotFoundInsideCircumcircle(){
        triangle = new Triangle();
        triangle.setVertex1(new Room(new Point(5, 5), 0, 0, 0, 0));
        triangle.setVertex2(new Room(new Point(15, 5), 0, 0, 0, 1));
        triangle.setVertex3(new Room(new Point(5, 15), 0, 0, 0, 2));
        Point A = triangle.getVertex1().getCenter();
        Point B = triangle.getVertex2().getCenter();
        Point AB = new Point(25, 25);
        assertFalse(triangle.pointIsWithinCircumCircle(AB));
    }
}
