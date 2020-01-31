

import luolastogeneraattori.objects.Cave;
import luolastogeneraattori.objects.Point;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PointTest {
    
    public PointTest() {
    }
    
    Point point;
    
    @Before
    public void setUp() {
        point = new Point();
    }
    
    @Test
    public void pointDefaultLocationIsCorrect() {
        assertEquals("(0, 0)", point.toString());
    }
    
    @Test
    public void pointAdditionWorksCorrectly() {
        Point point = Point.randomPoint();
        int x = -1 * point.getX();
        int y = -1 * point.getY();
        point.add(new Point(x, y));
        assertEquals("(0, 0)", point.toString());
    }
    
    @Test
    public void pointCheckCorrectlyNoticesLeftBound() {
        boolean b = point.check(new Point(-1, 0));
        assertTrue(b);
    }
    
    @Test
    public void pointCheckCorrectlyNoticesTopBound() {
        boolean b = point.check(new Point(0, -1));
        assertTrue(b);
    }
    
    @Test
    public void pointCheckCorrectlyNoticesRightBound() {
        boolean b = point.check(new Point(Cave.WIDTH, 0));
        assertTrue(b);
    }
    
    @Test
    public void pointCheckCorrectlyNoticesBottomBound() {
        boolean b = point.check(new Point(0, Cave.HEIGHT));
        assertTrue(b);
    }
    
    @Test
    public void pointCheckNoFalsePositive() {
        boolean b = point.check(Point.randomPoint());
        assertFalse(b);
    }
    
    @Test
    public void pointCheckAndAddCheckWorksCorrectly() {
        assertTrue(point.checkAndAdd(new Point(-1, 0)));
    }
    
    @Test
    public void pointCheckAndAddAdditionWorksCorrectly() {
        point.checkAndAdd(new Point(2, 2));
        assertEquals("(2, 2)", point.toString());
    }
    
    @Test
    public void pointCloneWorksCorrectly() {
        Point p = point.clone();
        assertTrue(point.equals(p));
    }
    
    @Test
    public void pointSubtractionWorksCorrectly() {
        Point first = new Point(2, 2);
        Point second = new Point(1, 1);
        first.subtract(second);
        assertEquals("(1, 1)", first.toString());
    }
}
