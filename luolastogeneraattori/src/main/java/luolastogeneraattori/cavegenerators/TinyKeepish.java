package luolastogeneraattori.cavegenerators;

import luolastogeneraattori.objects.Cave;
import luolastogeneraattori.objects.Corridor;
import luolastogeneraattori.objects.Point;
import luolastogeneraattori.objects.Room;
import luolastogeneraattori.ui.Graph;
import luolastogeneraattori.utils.CorridorList;
import luolastogeneraattori.utils.Delaunay;
import luolastogeneraattori.utils.RoomList;
import luolastogeneraattori.utils.SpanningTrees;

public class TinyKeepish {

    private CorridorList corridors;
    private RoomList rooms;
    private RoomList largeRooms;
    int[][] roomLocations;

    public void generateMap(int roomsToGenerate, int rounds, int largeCutoff) {
        generateRooms(roomsToGenerate, rounds);
        
        while (checkCollisions() > 0) {
        }

        findLargeRooms(largeCutoff);

        corridors = new Delaunay().triangulate(largeRooms.toArray()).clearDuplicates();
        corridors = new SpanningTrees().basic(largeRooms.toArray(), corridors, roomsToGenerate);

        fillGaps(roomsToGenerate);
        
        solveRoomLocations();
        
        rooms = generateResult();
        
        Graph g = new Graph();
        g.main(rooms, largeRooms, corridors);
    }
    
    private RoomList generateResult() {
        RoomList result = new RoomList();
        for (Corridor c : corridors.toArray()) {
            Point current = c.getFrom().getCenter().clone();
            Point goal = c.getTo().getCenter().clone();
            Point searchDirection = solveSearchDirection(c);
            int prevX = current.getX();
            int prevY = current.getY();
            while (!current.equals(goal)) {
                if (Math.abs(prevX - current.getX()) == 1
                        && Math.abs(prevY - current.getY()) == 1) {
                    Room r1 = rooms.get(roomLocations[prevY][current.getX()]);
                    Room r2 = rooms.get(roomLocations[current.getY()][prevX]);
                    if (!result.contains(r1)) {
                        result.add(r1);
                    } else if (!result.contains(r2)) {
                        result.add(r2);
                    }
                }
                Room r = rooms.get(roomLocations[current.getY()][current.getX()]);
                if (!result.contains(r)) {
                    result.add(r);
                }
                prevX = current.getX();
                prevY = current.getY();
                current.addSmart(searchDirection, goal);
            }
        }
        return result;
    }
    
    private Point solveSearchDirection(Corridor c) {
        Point a = c.getFrom().getCenter();
        Point b = c.getTo().getCenter();
        double angle = Math.toDegrees(Math.atan2(b.getY() - a.getY(), b.getX() - a.getX()));
        if (angle < 0) {
            angle = 180 + (180 + angle);
        }
        return new Point(Math.cos(Math.toRadians(angle)), Math.sin(Math.toRadians(angle)));
    }
    
    private void solveRoomLocations() {
        roomLocations = new int[Cave.HEIGHT][Cave.WIDTH];
        for (Room r : rooms.toArray()) {
            Point center = r.getCenter();
            int height = r.getHeight();
            int width = r.getWidth();
            int yStart = center.getY() - height / 2;
            int yEnd = center.getY() + (height + 1) / 2;
            int xStart = center.getX() - width / 2;
            int xEnd = center.getX() + (width + 1) / 2;
            for (int row = yStart; row < yEnd; row++) {
                for (int column = xStart; column < xEnd; column++) {
                    roomLocations[row][column] = r.getId();
                }
            }
        }
    }

    private void findLargeRooms(int largeCutoff) {
        largeRooms = new RoomList();
        roomLocations = new int[Cave.HEIGHT][Cave.WIDTH];
        for (Room r : rooms.toArray()) {
            if (r.getWidth() * r.getHeight() >= largeCutoff) {
                largeRooms.add(r);
            }
            r.drawRoomNoWalls();
        }
    }

    private void fillGaps(int currentId) {
        char[][] map = Cave.getInstance().getMap();
        for (int row = 0; row < Cave.HEIGHT; row++) {
            for (int column = 0; column < Cave.WIDTH; column++) {
                if (map[row][column] == ' ') {
                    rooms.add(new Room(new Point(column, row), 1, 1, 1, currentId++));
                }
            }
        }
    }

    private void generateRooms(int roomsToGenerate, int rounds) {
        double radius = 1.0;
        double degreeBetweenPoints = (360 * rounds) / roomsToGenerate;
        Point center = new Point(Cave.WIDTH / 2, Cave.HEIGHT / 2);
        this.rooms = new RoomList();
        for (int i = 0; i < roomsToGenerate; i++) {
            double x = radius * Math.cos(Math.toRadians(degreeBetweenPoints * i));
            double y = radius * Math.sin(Math.toRadians(degreeBetweenPoints * i));
            Point p = new Point((int) x, (int) y);
            p.add(center);
            radius += 0.2;
            rooms.add(Room.generateRandomRoom(p, i));
        }
    }

    /**
     * Metodi kutsuu Room-olioiden checkCollision metodia kaikkien huoneiden
     * kesken ja laskee tapahtuneiden törmäyksien lukumäärän
     *
     * @return int Tapahtuneiden törmäysten lukumäärä
     */
    private int checkCollisions() {
        int collisions = 0;
        Room[] rooms = this.rooms.toArray();
        for (int i = 0; i < rooms.length; i++) {
            for (int o = i + 1; o < rooms.length; o++) {
                collisions += rooms[i].checkCollision(rooms[o]);
            }
            collisions += rooms[i].checkCollisionWithWalls();
        }
        return collisions;
    }
}
