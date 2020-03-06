package luolastogeneraattori.objects;

import java.util.Random;

public class Room {

    private Point center;
    private double radius;

    private int width;
    private int height;

    private int id;
    

    /**
     * Taulukko määrittää että kuinka moneen osaan ympyrä jaetaan riippuen
     * huoneen säteestä. Käytetään debuggauksessa huoneen "kuplan" piirtämiseen.
     */
    private int[] pointsForRadius = {
        /*Points  | Radius */
        0, /*0      */
        0, /*1      */
        8, /*2      */
        12, /*3      */
        20, /*4      */
        24, /*5      */
        40, /*6      */};

    /**
     * Room -olion default konstruktori
     */
    public Room() {

    }

    /**
     * Room -Olion konstruktori joka alustaa Room -Olion käyttämät parametrit
     *
     * @param center Poiut Huoneen keskikohdan koordinaatit
     * @param width int Huoneen leveys
     * @param height int Huoneen korkeus
     * @param radius int Huoneen säde
     * @param id int huoneen id
     */
    public Room(Point center, int width, int height, double radius, int id) {
        this.center = center;
        this.width = width;
        this.height = height;
        this.radius = radius;
        this.id = id;
    }

    /**
     * Metodi piirtää huoneen id:tä vastaavan kirjaimen kartalle huoneen center
     * koordinaattien osoittamaan kohtaan
     */
    public void drawCenter() {
        Cave.getInstance().map[center.getY()][center.getX()] = (char) (97 + this.id);
    }

    /**
     * Debuggaus metodi Metodi piirtää huonetta ympäröivän "kuplan" huoneen
     * ympärille Jaetaan täys ympyrä (360 astetta) pointsForRadius määrittämälle
     * luvulle huoneen säteen(radius) perusteella piirretään '*' huoneen
     * säteen(radius) päähän huoneen keskipisteestä(center), lasketun asteluvun
     * osoittamaan suuntaan
     */
    public void drawCircle() {
        int pointCount = pointsForRadius[(int) radius];
        double degreeBetweenPoints = 360 / pointCount;
        for (int i = 0; i < pointCount; i++) {
            double x = radius * Math.cos(Math.toRadians(degreeBetweenPoints * i));
            double y = radius * Math.sin(Math.toRadians(degreeBetweenPoints * i));
            Point p = new Point((int) x, (int) y);
            if (!p.checkAndAdd(center)) {
                Cave.getInstance().map[p.getY()][p.getX()] = '*';
            }
        }
    }

    /**
     * Metodi piirtää huoneen kartalle. Huoneeseen kuuluvat ruudut merkataan '.'
     * Huoneen ulkopuolelle piirretään seinät '|' ja '-' merkeistä.
     */
    public void drawRoom() {
        int yStart = this.center.getY() - this.height / 2;
        int yEnd = this.center.getY() + (this.height + 1) / 2;
        int xStart = this.center.getX() - this.width / 2;
        int xEnd = this.center.getX() + (this.width + 1) / 2;
        for (int row = yStart - 1; row < yEnd + 1; row++) {
            for (int column = xStart - 1; column < xEnd + 1; column++) {
                if ((column >= xStart && column < xEnd) && (row == yStart - 1 || row == yEnd)) {
                    Cave.getInstance().map[row][column] = '-';
                } else if ((column == xStart - 1 || column == xEnd) && (row == yStart - 1 || row == yEnd)) {
                    Cave.getInstance().map[row][column] = '!';
                } else if (column == xStart - 1 || column == xEnd) {
                    Cave.getInstance().map[row][column] = '|';
                } else {
                    Cave.getInstance().map[row][column] = '.';
                }
            }
        }
    }
    
    /**
     * Metodi piirtää huoneen lattian, mutta jättää seinät piirtämättä
     */
    public void drawRoomNoWalls() {
        int yStart = this.center.getY() - this.height / 2;
        int yEnd = this.center.getY() + (this.height + 1) / 2;
        int xStart = this.center.getX() - this.width / 2;
        int xEnd = this.center.getX() + (this.width + 1) / 2;
        for (int row = yStart; row < yEnd; row++) {
            for (int column = xStart; column < xEnd; column++) {
                Cave.getInstance().map[row][column] = '.';
            }
        }
    }

    /**
     * Tarkistaa leikkaako parametrina annetun huoneen "kupla" tämän huoneen
     * "kuplaa" Lasketaan ensin etäisyys huoneiden keskipisteiden väliltä, jonka
     * jälkeen verrataan laskettua etäisyyttä huoneiden säteiden(radius)
     * summaan, jos etäisyys on pienempi kuin säteiden(radius) summa, niin
     * päätellään että kuplien täytyy leikata.
     *
     * @param room Room Tarkisteltava huone
     * @return int 1 jos huoneiden kuplat leikkaavat, 0 muutoin
     */
    public int checkCollision(Room room) {
        int x = Math.abs(this.center.getX() - room.getCenter().getX());
        int y = Math.abs(this.center.getY() - room.getCenter().getY());
        double c = Math.sqrt((x * x) + (y * y));
        if (c <= this.radius + room.radius) {
            Point vel = solveCollision(room);
            room.center.checkAndAdd(vel);
            this.center.checkAndAdd(vel.cloneOpposite());
            return 1;
        }
        return 0;
    }
    
    /**
     * Tarkistaa törmääkö tämä huone parametrina annetun huoneen kanssa.
     * Törmäys selvitetään laskemalla ensin tämän huoneen vasen, oikea, ylä ja ala seinien koordinaatit
     * Ja sitten laskemalla että onko mikään tarkasteltavan huoneen kulmista näiden koordinaattien sisäpuolella,
     * Jos on niin törmäys tapahtuu
     * @param room  Tarkasteltava huone
     * @return  1 jos törmättiin, 0 muutoin
     */
    public int checkCollisionSquare(Room room) {
        int negPadding = 2;
        int posPadding = 0;
        int top = this.center.getY() - this.height / 2;
        int bottom = this.center.getY() + (this.height - 1) / 2;
        int left = this.center.getX() - this.width / 2;
        int right = this.center.getX() + (this.width - 1) / 2;
        
        int ret = 0;
        for (Point p : room.getFourCorners()) {
            if (p.getX() >= left
                    && p.getX() <= right
                    && p.getY() >= top
                    && p.getY() <= bottom) {
                Point vel = solveCollision(room);
                room.center.checkAndAdd(vel);
                this.center.checkAndAdd(vel.cloneOpposite());
                ret++;
            }
        }
        return ret;
    }
    
    /**
     * Palauttaa tämän huoneen kulmien lokaatiot taulukkona
     * @return Point[] Huoneen kulmien lokaatiot
     */
    public Point[] getFourCorners() {
        int top = this.center.getY() - this.height / 2;
        int bottom = this.center.getY() + (this.height - 1) / 2;
        int left = this.center.getX() - this.width / 2;
        int right = this.center.getX() + (this.width - 1) / 2;
        return new Point[]{
            new Point(left, top),
            new Point(left, bottom),
            new Point(right, top),
            new Point(right, bottom)
        };
    }

    /**
     * Tarkistetaan leikkaako tämän Room-Olion "kupla" seiniä.
     *
     * @return int Leikattujen seinien lukumäärä
     */
    public int checkCollisionWithWalls() {
        int collisions = 0;
        if ((this.center.getX() - radius) <= 0) {
            this.center.checkAndAdd(new Point(1, 0));
            collisions++;
        }
        if ((this.center.getX() + radius) >= Cave.WIDTH - 1) {
            this.center.checkAndAdd(new Point(-1, 0));
            collisions++;
        }

        if ((this.center.getY() - radius) <= 0) {
            this.center.checkAndAdd(new Point(0, 1));
            collisions++;
        }

        if ((this.center.getY() + radius) >= Cave.HEIGHT - 1) {
            this.center.checkAndAdd(new Point(0, -1));
            collisions++;
        }
        return collisions;
    }

    /**
     * Palauttaa tämän Room-Olion keskipistettä kuvaavan Point -olion
     *
     * @return Point tämän Room-Olion keskipistettä kuvaava Point -olion
     */
    public Point getCenter() {
        return this.center;
    }
    
    
    /**
     * Setteri Room-Olion keskipisteelle
     * @param center 
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * Palauttaa tämän Room-Olion "kuplan" säteen int muodossa
     *
     * @return int tämän Room-Olion "kuplan" säde
     */
    public int getRadius() {
        return (int) this.radius;
    }
    
    /**
     * Palauttaa tämän Room-Olion "kuplan" säteen
     *
     * @return int tämän Room-Olion "kuplan" säde
     */
    public double getRadiusRaw() {
        return this.radius;
    }

    /**
     * Palauttaa tämän Room-Olion id:n
     *
     * @return int id
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Setteri Room-Olion id:lle
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }

    /**
     * Generoi Satunnaisen kokoisen huoneen satunnaiseen paikkaan kartalla
     *
     * @param i int luotavan huoneen id
     * @return Room Luotu huone
     */
    public static Room generateRandomRoom(int i) {
        Point center = Point.randomPoint();
        int width = new Random().nextInt(4) + 3;
        int height = new Random().nextInt(4) + 3;
        double radius = (Math.sqrt((width) + (height))) + 2;
        return new Room(center, width, height, radius, i);
    }
    
    /**
     * Generoi random huoneen, jonka keskipiste on annettuna
     * @param center    Generoitavan huoneen keskipiste
     * @param i         Generoitavan huoneen id
     * @return Room Luotu huone
     */
    public static Room generateRandomRoom(Point center, int i) {
        int width = new Random().nextInt(4) + 3;
        int height = new Random().nextInt(4) + 3;
        double a = width / 2.0;
        double b = height / 2.0;
        double radius = (Math.sqrt((a * a) + (b * b)));
        return new Room(center, width, height, radius, i);
    }

    /**
     * Metodi selvittää mihin suuntaan törmääviä huoneita pitäisi liikuttaa,
     * jotta ne liikkuvat poispäin toisistaan.
     *
     * @param room Room Tarkasteltava Room-Olio
     * @return Point Point-olio joka kuvastaa törmäyksen aiheuttaman liikkeen
     * suuntaa
     */
    private Point solveCollision(Room room) {
        double angle = Math.toDegrees(Math.atan2(room.getCenter().getY() - this.center.getY(), room.getCenter().getX() - this.center.getX()));
        if (angle < 0) {
            angle = 180 + (180 + angle);
        }
        return new Point(Math.cos(Math.toRadians(angle)), Math.sin(Math.toRadians(angle)));
    }
}
