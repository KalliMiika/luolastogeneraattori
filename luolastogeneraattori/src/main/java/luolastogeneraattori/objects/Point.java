package luolastogeneraattori.objects;

import java.util.Random;

public class Point {

    private int x;
    private int y;

    /**
     * Point -luokan default -konstruktori
     */
    public Point() {
        this.x = 0;
        this.y = 0;
    }
    /**
     * Point -luokan konstruktori
     * @param x     Point olion x koordinaatti
     * @param y     Point olion y koordinaatti
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 
     * @return Palauttaa Point -olion X-koordinaatin
     */
    public int getX() {
        return this.x;
    }

    /**
     * 
     * @return Palauttaa Point -olion Y-koordinaatin
     */
    public int getY() {
        return this.y;
    }

    /**
     * Asettaa Point -olion X-koordinaatin
     * @param x     asetettava x-koordinaatti
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * Asettaa Point -olion Y-koordinaatin
     * @param y     asetettava y-koordinaatti
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Lisää parametrina annetun Point-olion koordinaatit
     * tämän Point-olion koordinaatteihin.
     * @param point     Lisättävä Point-olio
     */
    public void add(Point point) {
        this.x += point.getX();
        this.y += point.getY();
    }
    
    /**
     * Tarkistaa voidaanko parametrina annettu Point-olio
     * lisätä tähän Point-olioon.
     * Jos voidaan, lisätään se tähän Point-olioon ja palautetaan false
     * Jos ei voida, lisäystä ei tehdä ja palautetaan true
     * @param point     Tarkistettava ja Lisättävä Point-olio
     * @return true jos lisäys ei onnistu, false muutoin
     */
    public boolean checkAndAdd(Point point) {
        if (check(point)) {
            return true;
        }
        add(point);
        return false;
    }
    
    /**
     * Tarkistaa voidaanko parametrina annettu Point-olio
     * lisätä tähän Point-olioon.
     * Jos voidaan, palautetaan false
     * Jos ei voida, palautetaan true
     * @param point     Tarkistettava Point-olio
     * @return true jos lisäys ei onnistu, false muutoin
     */
    public boolean check(Point point) {
        int x = this.x + point.getX();
        if (x < 0 || x >= Cave.WIDTH) {
            return true;
        }
        int y = this.y + point.getY();
        if (y < 0 || y >= Cave.HEIGHT) {
            return true;
        }
        return false;
    }

    /**
     * Luodaan ja palautetaan uusi satunnaiseen lokaatioon
     * luotu Piste-olio
     * @return randomgeneroitu point-olio
     */
    public static Point randomPoint() {
        Random rnd = new Random();
        int x = rnd.nextInt(Cave.WIDTH);
        int y = rnd.nextInt(Cave.HEIGHT);
        return new Point(x, y);
    }

    /**
     * Palauttaa String-tyyppisen kuvauksen Point-oliosta.
     * Muodossa "(x, y)"
     * @return String-tyyppinen kuvaus Point-oliosta
     */
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
