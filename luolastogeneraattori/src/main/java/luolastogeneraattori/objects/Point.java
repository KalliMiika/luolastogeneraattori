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
     * Vähentää parametrina annetun Point-olion koordinaatit
     * tämän Point-olion koordinaateista.
     * @param point     Vähennettävä Point-olio
     */
    public void subtract(Point point) {
        this.x -= point.getX();
        this.y -= point.getY();
    }
    
    
    /**
     * Kloonaa tämän Point-Olion
     * @return  Point   Kopio tästä Point-Oliosta
     */
    public Point clone() {
        return new Point(this.x, this.y);
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
        if (x < 1 || x >= Cave.WIDTH-1) {
            return true;
        }
        int y = this.y + point.getY();
        if (y < 1 || y >= Cave.HEIGHT-1) {
            return true;
        }
        return false;
    }

    /**
     * Luodaan ja palautetaan uusi satunnaiseen lokaatioon
     * luotu Piste-olio
     * Piste ei kuitenkaan saa osua ruudun reunoihin, joten
     * vähennetään satunnaisuuden maksimiarvoista 2 ja lisätään
     * minimiarvoon 1
     * @return randomgeneroitu point-olio
     */
    public static Point randomPoint() {
        Random rnd = new Random();
        int x = rnd.nextInt(Cave.WIDTH-2)+1;
        int y = rnd.nextInt(Cave.HEIGHT-2)+1;
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
    
    /**
     * Vertaa parametrina annettua Point-Oliota itseensä
     * Jos olioiden parametrit täsmäävät, palautetaan true
     * muutoin false
     * @param point     Point   Verrattava Point-Olio
     * @return          boolean true jos sama, false muutoin
     */
    public boolean equals(Point point) {
        if(this.x == point.getX() && this.y == point.y) {
            return true;
        }
        return false;
    }
}
