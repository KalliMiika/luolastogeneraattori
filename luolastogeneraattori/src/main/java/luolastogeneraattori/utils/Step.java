package luolastogeneraattori.utils;


import luolastogeneraattori.objects.Point;

public class Step {
    
    private Point point;
    private double distance;
    private Step previous;

    public Step(Point point, double distance, Step previous) {
        this.point = point;
        this.distance = distance;
        this.previous = previous;
    }

    public Point getPoint() {
        return point;
    }
    
    public double getDistance() {
        return distance;
    }

    public Step getPrevious() {
        return previous;
    }
    
    public boolean hasPrevious() {
        if (this.previous == null) {
            return false;
        }
        return true;
    }
}
