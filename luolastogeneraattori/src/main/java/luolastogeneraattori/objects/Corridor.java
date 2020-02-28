package luolastogeneraattori.objects;

import luolastogeneraattori.utils.StepPriorityQueue;
import luolastogeneraattori.utils.Step;

public class Corridor {

    private Room from;
    private Room to;

    public Corridor(Room from, Room to) {
        this.from = from;
        this.to = to;
    }

    public Room getFrom() {
        return this.from;
    }

    public Room getTo() {
        return this.to;
    }

    private Point start;
    private Point goal;
    private final Point[] directions = {new Point(0, 1), new Point(0, -1), new Point(1, 0), new Point(-1, 0)};

    /**
     * Generoidaan käytävä huoneesta 'from' huoneeseen 'to' A* -haulla,
     * liikkuminen rajoitettu ylös - alas, vasen - oikea
     */
    public void drawCorridorWithPriorityList() {
        findStartAndGoal();
        Cave.getInstance().map[start.getY()][start.getX()] = '+';
        Cave.getInstance().map[goal.getY()][goal.getX()] = '+';
        boolean[][] help = new boolean[Cave.HEIGHT][Cave.WIDTH];
        StepPriorityQueue spq = new StepPriorityQueue();
        Step currentStep = new Step(start, start.dist(goal), null);
        spq.add(currentStep);
        help[start.getY()][start.getX()] = true;
        while (spq.size() > 0) {
            currentStep = spq.pop();
            if (currentStep.getPoint().equals(goal)) {
                break;
            }
            int x = currentStep.getPoint().getX();
            int y = currentStep.getPoint().getY();
            for (Point direction : directions) {
                Point p = currentStep.getPoint().clone();
                p.add(direction);
                if (!help[p.getY()][p.getX()] && (Cave.getInstance().map[p.getY()][p.getX()] == ' '
                        || Cave.getInstance().map[p.getY()][p.getX()] == '+'
                        || Cave.getInstance().map[p.getY()][p.getX()] == '#')) {
                    spq.add(new Step(p, p.dist(goal), currentStep));
                    help[p.getY()][p.getX()] = true;
                }
            }
        }
        if (currentStep.hasPrevious()) {
            currentStep = currentStep.getPrevious();
        }
        while (currentStep.hasPrevious()) {
            if (Cave.getInstance().map[currentStep.getPoint().getY()][currentStep.getPoint().getX()] == ' ') {
                Cave.getInstance().map[currentStep.getPoint().getY()][currentStep.getPoint().getX()] = '#';
            }
            currentStep = currentStep.getPrevious();
        }
    }
    
    /**
     * Generoidaan käytävä huoneesta 'from' huoneeseen 'to' A* -haulla,
     * liikkuminen pysty- ja vaakasuunnassa, sekä kulmittain sallittu
     */
    public void drawCorridorWithPriorityListAllowDiagonal() {
        findStartAndGoal();
        Cave.getInstance().map[start.getY()][start.getX()] = '+';
        Cave.getInstance().map[goal.getY()][goal.getX()] = '+';
        boolean[][] help = new boolean[Cave.HEIGHT][Cave.WIDTH];
        StepPriorityQueue spq = new StepPriorityQueue();
        Step currentStep = new Step(start, start.dist(goal), null);
        spq.add(currentStep);
        help[start.getY()][start.getX()] = true;
        while (spq.size() > 0) {
            currentStep = spq.pop();
            if (currentStep.getPoint().equals(goal)) {
                break;
            }
            int x = currentStep.getPoint().getX();
            int y = currentStep.getPoint().getY();
            for (int row = y - 1; row <= y + 1; row++) {
                for (int column = x - 1; column <= x + 1; column++) {
                    Point p = new Point(column, row);
                    if (!help[row][column] && (Cave.getInstance().map[row][column] == ' ' 
                            || Cave.getInstance().map[row][column] == '+'
                            || Cave.getInstance().map[p.getY()][p.getX()] == '#')) {
                        spq.add(new Step(p, p.dist(goal), currentStep));
                        help[row][column] = true;
                    }
                }
            }
        }
        if (currentStep.hasPrevious()) {
            currentStep = currentStep.getPrevious();
        }
        while (currentStep.hasPrevious()) {
            if (Cave.getInstance().map[currentStep.getPoint().getY()][currentStep.getPoint().getX()] == ' ') {
                Cave.getInstance().map[currentStep.getPoint().getY()][currentStep.getPoint().getX()] = '#';
            }
            currentStep = currentStep.getPrevious();
        }
    
    }

    /**
     * Etsitään se seinäruutu, joka on lähimpänä päämäärää,
     * sen jälkeen tutkitaan onko lähettyvillä muita jo olemassa olevia ovia
     *  jos on, valitaan jo olemassa oleva ovi
     * jos ei, korvataan käytetty seinäpala ovella
     */
    private void findStartAndGoal() {
        Point start = from.getCenter();
        double curDistance = start.dist(this.to.getCenter());
        for (int row = from.getCenter().getY() - from.getRadius(); row <= from.getCenter().getY() + from.getRadius(); row++) {
            for (int column = from.getCenter().getX() - from.getRadius(); column <= from.getCenter().getX() + from.getRadius(); column++) {
                if (Cave.getInstance().map[row][column] == '-' || Cave.getInstance().map[row][column] == '|') {
                    Point p = new Point(column, row);
                    double d = p.dist(this.to.getCenter());
                    if (d < curDistance) {
                        start = p;
                        curDistance = d;
                    }
                }
            }
        }
        Point closestDoor = start; 
        for (int row = from.getCenter().getY() - from.getRadius(); row <= from.getCenter().getY() + from.getRadius(); row++) {
            for (int column = from.getCenter().getX() - from.getRadius(); column <= from.getCenter().getX() + from.getRadius(); column++) {
                if (Cave.getInstance().map[row][column] == '+') {
                    Point p = new Point(column, row);
                    double d = p.dist(start);
                    if (d < curDistance) {
                        closestDoor = p;
                        curDistance = d;
                    }
                }
            }
        }
        if (curDistance < 2) {
            start = closestDoor;
        }
        
        Point goal = to.getCenter();
        curDistance = goal.dist(this.from.getCenter());
        for (int row = to.getCenter().getY() - to.getRadius(); row <= to.getCenter().getY() + to.getRadius(); row++) {
            for (int column = to.getCenter().getX() - to.getRadius(); column <= to.getCenter().getX() + to.getRadius(); column++) {
                if (Cave.getInstance().map[row][column] == '-' || Cave.getInstance().map[row][column] == '|') {
                    Point p = new Point(column, row);
                    double d = p.dist(this.from.getCenter());
                    if (d < curDistance) {
                        goal = p;
                        curDistance = d;
                    }
                }
            }
        }   
        closestDoor = goal;
        for (int row = to.getCenter().getY() - to.getRadius(); row <= to.getCenter().getY() + to.getRadius(); row++) {
            for (int column = to.getCenter().getX() - to.getRadius(); column <= to.getCenter().getX() + to.getRadius(); column++) {
                if (Cave.getInstance().map[row][column] == '+') {
                    Point p = new Point(column, row);
                    double d = p.dist(goal);
                    if (d < curDistance) {
                        closestDoor = p;
                        curDistance = d;
                    }
                }
            }
        }
        if (curDistance < 2) {
            goal = closestDoor;
        }
        
        this.start = start;
        this.goal = goal;
    }

    /**
     * Yksinkertainen L-muotoisten käytävien generoimis algoritmi joka ei väist
     * seiniä
     */
    public void drawCorridor() {
        Point step = from.getCenter();
        Point goal = to.getCenter();
        Point direction;
        if (from.getCenter().getX() < to.getCenter().getX()) {
            direction = new Point(1, 0);
        } else {
            direction = new Point(-1, 0);
        }
        while (step.getX() != goal.getX()) {
            char terrainAtStep = Cave.getInstance().map[step.getY()][step.getX()];
            if (terrainAtStep == '-' || terrainAtStep == '|') {
                Cave.getInstance().map[step.getY()][step.getX()] = '+';
            } else if (terrainAtStep == ' ') {
                Cave.getInstance().map[step.getY()][step.getX()] = '#';
            }
            step.add(direction);
        }
        if (from.getCenter().getY() < to.getCenter().getY()) {
            direction = new Point(0, 1);
        } else {
            direction = new Point(0, -1);
        }
        while (step.getY() != goal.getY()) {
            char terrainAtStep = Cave.getInstance().map[step.getY()][step.getX()];
            if (terrainAtStep == '-' || terrainAtStep == '|') {
                Cave.getInstance().map[step.getY()][step.getX()] = '+';
            } else if (terrainAtStep == ' ') {
                Cave.getInstance().map[step.getY()][step.getX()] = '#';
            }
            step.add(direction);
        }

    }

    /**
     * Vertaa onko parametrina annetulla Corridor-objektilla samat päätepisteet,
     * kuin tällä
     * @param corridor  Corridor    Tarkasteltava Corridor -objekti
     * @return true jos päätepisteet ovat samat, false muutoin
     */
    public boolean equals(Corridor corridor) {
        if (this.from.getId() == corridor.getFrom().getId() && this.to.getId() == corridor.getTo().getId()) {
            return true;
        }
        if (this.from.getId() == corridor.getTo().getId() && this.to.getId() == corridor.getFrom().getId()) {
            return true;
        }
        return false;
    }
}
