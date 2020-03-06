import luolastogeneraattori.objects.Point;
import luolastogeneraattori.utils.Step;
import luolastogeneraattori.utils.StepPriorityQueue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class StepPriorityQueueTest {
    
    public StepPriorityQueueTest() {
    }
    
    StepPriorityQueue spq;
    Point origin;
    Step s1;
    Step s2;
    Step s3;
    
    @Before
    public void setUp() {
        spq = new StepPriorityQueue();
        origin = Point.randomPoint();
        Point p1 = Point.randomPoint();
        Point p2 = Point.randomPoint();
        Point p3 = Point.randomPoint();
        s1 = new Step(p1, p1.dist(origin), null);
        s2 = new Step(p2, p2.dist(origin), null);
        s3 = new Step(p3, p3.dist(origin), null);
    }
    
    @Test
    public void addTest() {
        spq.add(s1);
        spq.add(s2);
        spq.add(s3);
        assertEquals(3, spq.size());
        Step closest = spq.pop();
        assertEquals(2, spq.size());
        assertTrue(closest.getDistance() <= spq.pop().getDistance());
        assertEquals(1, spq.size());
        assertTrue(closest.getDistance() <= spq.pop().getDistance());
        assertEquals(0, spq.size());
    }
    
    @Test
    public void stressTest() {
        for (int i = 0; i < 100; i++) {
            Point p = Point.randomPoint();
            spq.add(new Step(p, p.dist(origin), null));
        }
        assertEquals(100, spq.size());
    }
}
