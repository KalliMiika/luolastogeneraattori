package luolastogeneraattori.utils;

import luolastogeneraattori.objects.Point;
import luolastogeneraattori.objects.Room;

public class Triangle {

    /**
     * Huoneet joiden keskipisteet muodostavat kolmion
     */
    private Room vertex1;
    private Room vertex2;
    private Room vertex3;

    /**
     * Triangle -luokan default konstruktori
     */
    public Triangle() {

    }

    /**
     * Triangle -luokan konstruktori
     *
     * @param vertex1 Room Kolmion kärki 'A'
     * @param vertex2 Room Kolmion kärki 'B'
     * @param vertex3 Room Kolmion kärki 'C'
     */
    public Triangle(Room vertex1, Room vertex2, Room vertex3) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;

    }

    /**
     * Metodi selvittää onko annettu piste sen ympyrän sisällä,
     * jonka kehä kulkee kaikkien kolmion kärkipisteiden kautta
     * @param point     Point   Tarkasteltava Point -olio
     * @return          boolean true jos on, false muutoin
     */
    public boolean pointIsWithinCircumCircle(Point point) {
        Point center = getCircumCenter();
        double radius = getRadius();
        double dx = center.getXVal() - point.getX();
        double dy = center.getYVal() - point.getY();
        if ((dx * dx + dy * dy) <= (radius * radius)) {
            return true;
        }
        return false;
    }
    
    /**
     * Metodi laskee sen ympyrän, jonka kehä kulkee kaikkien
     * Kolmion kärkipisteiden kautta ja palauttaa sen ympyrän keskipisteen
     * @return          Point   Kolmion circumcirclen keskipiste
     */
    private Point getCircumCenter() {
        Point a = vertex1.getCenter();
        Point b = vertex2.getCenter();
        Point c = vertex3.getCenter();
        double ab = norm(a);
        double cd = norm(b);
        double ef = norm(c);

        double ax = a.getX();
        double ay = a.getY();
        double bx = b.getX();
        double by = b.getY();
        double cx = c.getX();
        double cy = c.getY();

        double circum_x = (ab * (cy - by) + cd * (ay - cy) + ef * (by - ay)) / (ax * (cy - by) + bx * (ay - cy) + cx * (by - ay));
        double circum_y = (ab * (cx - bx) + cd * (ax - cx) + ef * (bx - ax)) / (ay * (cx - bx) + by * (ax - cx) + cy * (bx - ax));
        return new Point(circum_x / 2, circum_y / 2);
    }

    /**
     * Metodi laskee sen ympyrän säteen, joka
     * kulkee kaikkien kolmion kärkipisteiden kautta
     * @return          double  Kolmion circumcirclen säde
     */
    private double getRadius() {
        Point center = getCircumCenter();
        Point tmp = new Point(center.getXVal(), center.getYVal());
        return vertex1.getCenter().dist(tmp);
    }

    /**
     * Normalisoi annetun pisteen, käytetään 
     * CircumCenterin laskemisessa
     * @param point     Point   Tarkasteltava Point -Olio
     * @return          double  normalisoitu piste
     */
    private double norm(Point point) {
        double x = point.getX();
        double y = point.getY();

        return (x * x) + (y * y);
    }

    /**
     * Palauttaa kolmion kärkipisteen 'A'
     *
     * @return Room kolmion kärkipiste 'A'
     */
    public Room getVertex1() {
        return vertex1;
    }

    /**
     * Asettaa kolmion kärkipisteen 'A'
     * @param vertex1   Room    Asetettava kärkipiste
     */
    public void setVertex1(Room vertex1) {
        this.vertex1 = vertex1;
    }

    /**
     * Palauttaa kolmion kärkipisteen 'B'
     *
     * @return Room kolmion kärkipiste 'B'
     */
    public Room getVertex2() {
        return vertex2;
    }

    /**
     * Asettaa kolmion kärkipisteen 'B'
     * @param vertex2   Room    Asetettava kärkipiste
     */
    public void setVertex2(Room vertex2) {
        this.vertex2 = vertex2;
    }

    /**
     * Palauttaa kolmion kärkipisteen 'C'
     *
     * @return Room kolmion kärkipiste 'C'
     */
    public Room getVertex3() {
        return vertex3;
    }

    /**
     * Asettaa kolmion kärkipisteen 'C'
     * @param vertex3   Room    Asetettava kärkipiste
     */
    public void setVertex3(Room vertex3) {
        this.vertex3 = vertex3;
    }
}
