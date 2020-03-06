package luolastogeneraattori.cavegenerators;

import java.util.Random;
import luolastogeneraattori.objects.Cave;
import luolastogeneraattori.objects.Corridor;
import luolastogeneraattori.objects.Point;
import luolastogeneraattori.utils.Raport;
import luolastogeneraattori.objects.Room;
import luolastogeneraattori.utils.CorridorList;
import luolastogeneraattori.utils.Delaunay;
import luolastogeneraattori.utils.RoomList;
import luolastogeneraattori.utils.SpanningTrees;

public class TinyKeepish {

    private CorridorList corridors;
    private RoomList rooms;
    private RoomList largeRooms;
    int[][] roomLocations;

    
    /**
     * 1. Generoidaan satunnaisia huoneita läjäpäin ruudulle
     * 2. Ajetaan simulaatio jossa huoneet tönivät toisiaan kunnes mitkään kaksi huonetta eivät ole toistensa päällä
     * 3. Generoidaan vekko jossa  huoneet ovat verkon solmuja Delaunay Triangulation Algoritmilla
     * 4. Generoidaan luodusta verkosta virittävä puu
     * 5. Täytetään tyhjä tila 1x1 kokosilla huoneilla
     * 6. Generoidaan aputaulu josta selviää että minkä alueen mikäkin huone peittää kartalta
     * 7. Piirretään ne huoneet, joiden yli virittävän puun määrittämät käytävät kulkevat linnuntietä
     * @param roomsToGenerate   Generoitavien huoneiden lukumäärä
     * @param generationMethod  Käytettävä metodi huoneidenheneroimiseen
     * @param collisionMethod Törmäystarkistusmetodi
     * @param largeCutoff   Raja, jota suuremmat huoneet lasketaan isoiksi huoneiksi
     * @param spanningTreeType  Käytettävä virittävä puu algoritmi "BASIC" tai "RANDOM"
     * @param treeCutOff    P(Corridor) jolla käytävä, jota ei valittu virittävään puuhun otetaan mukaan
     * @return Raportti joka kertoo kauanko generointiin kului aikaa ja paljonko se varaa muistia
     */
    public Raport generateMap(int roomsToGenerate, String generationMethod, String collisionMethod, int largeCutoff, String spanningTreeType, int treeCutOff) {
        long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long t1 = System.currentTimeMillis();
        generateRooms(roomsToGenerate, generationMethod);

        while (checkCollisions(collisionMethod) > 0) {
        }

        findLargeRooms(largeCutoff);

        runDelaunay();

        generateCorridors(roomsToGenerate, spanningTreeType, treeCutOff);

        fillGaps(roomsToGenerate);

        solveRoomLocations();

        generateResult();

        long actualTimeSpent = System.currentTimeMillis() - t1;
        long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long actualMemUsed = afterUsedMem - beforeUsedMem;
        return new Raport(actualTimeSpent, actualMemUsed);
    }
    
    /**
     * Metodi generoi käytävät Delaunay Triangulation Algoritmilla
     */
    public void runDelaunay() {
        corridors = new Delaunay().triangulate(largeRooms.toArray()).clearDuplicates(); 
    }
    
    /**
     * Metodi valitsee mitä Virittävä Puu -Algoritmia käytetään
     * @param maxRoomId   suurin huoneen id
     * @param spanningTreeType  Käytettävä Virittävä Puu -Algoritmi "BASIC" tai "RANDOM"
     * @param treeCutOff Todennäköisyys, jolla puuhun kuulumaton käytävä otetaan mukaan
     */
    public void generateCorridors(int maxRoomId, String spanningTreeType, int treeCutOff) {
        switch (spanningTreeType) {
            case "BASIC":
                corridors = new SpanningTrees().basic(largeRooms.toArray(), corridors, maxRoomId, treeCutOff);
                break;
            case "RANDOM":
                corridors = new SpanningTrees().random(largeRooms.toArray(), corridors, maxRoomId, treeCutOff);
                break;
            default:
                break;
        }
    }
    
    /**
     * Metodi "Piirtää" lopullisen kartan Cave -olion char[][] karttaan
     * Huoneiden valikointi tapahtuu käymällä käytävät läpi yksi kerrallaan,
     * käytäviä "ajetaan" alusta loppuun askel kerrallaan kohti päämäärää
     * ja joka askeleella tarkistetaan että onko siinä kohdassa oleva huone
     * vielä otettu mukaan, jos ei, otetaan se mukaan
     */
    public void generateResult() {
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
        
        Cave.getInstance().setMap(Cave.generateBlankMapWithBorders());
        for (Room r : result.toArray()) {
            r.drawRoomNoWalls();
        }
        rooms = result;
    }
    
    /**
     * Lasketaan mihin suuntaan "ajetaan" generateResult() metodissa
     * @param c Käsiteltävä Corridor-Olio
     * @return liikevektorina käytettävä Point-Olio
     */
    private Point solveSearchDirection(Corridor c) {
        Point a = c.getFrom().getCenter();
        Point b = c.getTo().getCenter();
        double angle = Math.toDegrees(Math.atan2(b.getY() - a.getY(), b.getX() - a.getX()));
        if (angle < 0) {
            angle = 180 + (180 + angle);
        }
        return new Point(Math.cos(Math.toRadians(angle)), Math.sin(Math.toRadians(angle)));
    }
    
    /**
     * Metodi rakentaa 2D aputaulun, josta näkee että mitkä ruudut mikäkin huone peittää
     */
    public void solveRoomLocations() {
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

    /**
     * Metodi etsii kaikki huoneet joiden pinta-ala on vähintään parametrin kokoinen
     * @param largeCutoff Suuren huoneen vähimmäis Pinta-ala
     */
    public void findLargeRooms(int largeCutoff) {
        largeRooms = new RoomList();
        roomLocations = new int[Cave.HEIGHT][Cave.WIDTH];
        for (Room r : rooms.toArray()) {
            if (r.getWidth() * r.getHeight() >= largeCutoff) {
                largeRooms.add(r);
            }
            r.drawRoomNoWalls();
        }
    }

    /**
     * Metodi generoi 1x1 kokosia huoneita kaikkiin tyhjiin ruutuihin kartalla
     * @param currentId Suurimman olemassa olevan huoneen id
     */
    public void fillGaps(int currentId) {
        char[][] map = Cave.getInstance().getMap();
        for (int row = 0; row < Cave.HEIGHT; row++) {
            for (int column = 0; column < Cave.WIDTH; column++) {
                if (map[row][column] == ' ') {
                    rooms.add(new Room(new Point(column, row), 1, 1, 1, currentId++));
                }
            }
        }
    }

    /**
     * Metodi generoi satunnaisia huoneita spiraaliin, generoitavien huoneiden ja kierrosten
     * määrä riippuu parametreista   
     * TAI
     * Metodi generoi satunnaisia huoneita, satunnaisiin kohtiin ympyrällä 
     * keskipisteen ympärillä
     * Riippuen parametrista GenerationMethod
     * @param roomsToGenerate Generoitavien huoneiden lukumäärä
     * @param GenerationMethod  Käytettävä generointimetodi
     */
    public void generateRooms(int roomsToGenerate, String GenerationMethod) {
        Point center = new Point(Cave.WIDTH / 2, Cave.HEIGHT / 2);
        this.rooms = new RoomList();
        switch (GenerationMethod) {
            case "SPIRAL":
                double radius = 1.0;
                double degreeBetweenPoints = (360 * 3) / roomsToGenerate;
                for (int i = 0; i < roomsToGenerate; i++) {
                    double x = radius * Math.cos(Math.toRadians(degreeBetweenPoints * i));
                    double y = radius * Math.sin(Math.toRadians(degreeBetweenPoints * i));
                    Point p = new Point((int) x, (int) y);
                    p.add(center);
                    radius += 0.1;
                    rooms.add(Room.generateRandomRoom(p, i));
                }
                break;
            case "STACK":
                boolean[][] help = new boolean[Cave.HEIGHT][Cave.WIDTH];
                Random rnd = new Random();
                int counter = 0;
                while (counter < roomsToGenerate) {
                    double range = rnd.nextDouble() * 20;
                    double angle = rnd.nextDouble() * 360;
                    double x = range * Math.cos(Math.toRadians(angle));
                    double y = range * Math.sin(Math.toRadians(angle));
                    Point p = new Point(x, y);
                    p.add(center);
                    if (!help[p.getY()][p.getX()]) {
                        rooms.add(Room.generateRandomRoom(p, counter));
                        counter++;
                    }
                }
                break;
        }
    }

    /**
     * Metodi kutsuu Room-olioiden checkCollision metodia kaikkien huoneiden
     * kesken ja laskee tapahtuneiden törmäyksien lukumäärän
     *  o = i + 1
     * @return int Tapahtuneiden törmäysten lukumäärä
     */
    public int checkCollisions(String method) {
        int collisions = 0;
        Room[] rooms = this.rooms.toArray();
        for (int i = 0; i < rooms.length; i++) {
            for (int o = 0; o < rooms.length; o++) {
                if (i == o) {
                    continue;
                }
                switch (method) {
                    case "SPHERE":
                        collisions += rooms[i].checkCollision(rooms[o]);
                        break;
                    case "SQUARE":
                        collisions += rooms[i].checkCollisionSquare(rooms[o]);
                        break;
                }
            }
            collisions += rooms[i].checkCollisionWithWalls();
        }
        return collisions;
    }
    
    public RoomList getRooms() {
        return this.rooms;
    }
    
    public RoomList getLargeRooms() {
        return this.largeRooms;
    }
    
    public CorridorList getCorridors() {
        return this.corridors;
    }
    
}
