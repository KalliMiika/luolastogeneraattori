package luolastogeneraattori.utils;

import java.util.ArrayList;
import luolastogeneraattori.objects.Corridor;
import luolastogeneraattori.objects.Point;
import luolastogeneraattori.objects.Room;

public class Delaunay {

    private int roomCount;
    private int trmax;
    private CorridorList edges = new CorridorList();
    private TriangleList triangles = new TriangleList();

    /**
     * Metodi laskee Triangule -metodille alkuarvot
     *
     * @param rooms Room[] Tuotettavan verkon solmut
     */
    private void setup(Room[] rooms) {
        roomCount = rooms.length;
        trmax = roomCount * 4;
        int minX = rooms[0].getCenter().getX();
        int minY = rooms[0].getCenter().getY();
        int maxX = minY;
        int maxY = minX;
        for (Room r : rooms) {
            minX = Math.min(minX, r.getCenter().getX());
            minY = Math.min(minY, r.getCenter().getY());
            maxX = Math.max(maxX, r.getCenter().getX());
            maxY = Math.max(maxY, r.getCenter().getY());
        }
        int convexMultiplier = 1000;
        int dx = (maxX - minX) * convexMultiplier;
        int dy = (maxY - minY) * convexMultiplier;
        int deltaMax = Math.max(dx, dy);
        Double midX = (minX + maxX) * 0.5;
        Double midY = (minY + maxY) * 0.5;
        Room p1 = new Room();
        p1.setCenter(new Point(midX - (2 * deltaMax), midY - deltaMax));
        p1.setId(1000);
        Room p2 = new Room();
        p2.setCenter(new Point(midX, midY + (2 * deltaMax)));
        p2.setId(1001);
        Room p3 = new Room();
        p3.setCenter(new Point(midX + (2 * deltaMax), midY - deltaMax));
        p3.setId(1002);
        triangles.add(new Triangle(p1, p2, p3));
    }

    /**
     * Metodi generoi parametrina annettujen verkon solmujen välille kaaria,
     * niin että mitkään kaksi kaarta eivät leikkaa toisiaan. Metodin toiminta
     * lyhyesti: 
     * 1. Luodaan supertriangle joka ympäröi kaikkia verkon solmuja.
     * 2. Käydään kaikki verkon solmut läpi yksi kerrallaan 
     *   3. Käydään kaikki löydetyt kolmiot läpi yksi kerrallaan 
     *     4. Jos tarkasteltava piste on tarkasteltavan kolmion sisällä, 
     *        Poistetaan tarkasteltava kolmio, mutta pidetään kolmion muodostavat 
     *        kaaret vielä muistissa. 
     *   5. Verrataan kaikkia kaaria toisiinsa 
     *     6. Jos verrattavat kaaret ovat samat, poistetaan molemmat 
     *   7. Generoidaan uusia kolmioita poistettujen kolmioiden kaarista
     *      niin, että vedetään kaaren molemmista päistä kaaret tarkasteltavaan
     *      pisteeseen 
     * 8. Lopuksi palautetaan se lista kaaria, jotka muodostavat
     *    kaikki generoidut kolmiot, poislukien ne, jotka sisältävät alkuperäisen
     *    superkolmion kaaria.
     *
     * @param rooms Room[] Tarkasteltavan verkon solmut
     * @return ArrayList<Corridor> Solmujen välille generoidut kaaret
     */
    public CorridorList triangulate(Room[] rooms) {
        setup(rooms);
        if (roomCount <= 2) {
            //ei toimi
            return null;
        } else if (roomCount == 3) {
            edges = new CorridorList();
            edges.add(new Corridor(rooms[0], rooms[1]));
            edges.add(new Corridor(rooms[0], rooms[2]));
            edges.add(new Corridor(rooms[2], rooms[1]));
            return edges;
        }
        for (int i = 0; i < roomCount; i++) {
            edges = new CorridorList();
            for (int j = triangles.size() - 1; j >= 0; j--) {
                Triangle triangle = triangles.get(j);
                if (triangle.pointIsWithinCircumCircle(rooms[i].getCenter())) {
                    edges.add(new Corridor(triangle.getVertex1(), triangle.getVertex2()));
                    edges.add(new Corridor(triangle.getVertex1(), triangle.getVertex3()));
                    edges.add(new Corridor(triangle.getVertex3(), triangle.getVertex2()));
                    triangles.remove(j);
                }
            }

            CorridorList setForDel = new CorridorList();
            for (int j = edges.size() - 2; j >= 0; j--) {
                for (int k = edges.size() - 1; k >= j + 1; k--) {
                    if (j != k && edges.get(j).equals(edges.get(k))) {
                        setForDel.add(edges.get(j));
                        setForDel.add(edges.get(k));
                    }
                }
            }
            for (Corridor c : setForDel.toArray()) {
                edges.remove(c);
            }

            for (int j = 0; j < edges.size(); j++) {
                triangles.add(new Triangle(edges.get(j).getFrom(), edges.get(j).getTo(), rooms[i]));
            }
        }
        edges = new CorridorList();
        for (int i = triangles.size() - 1; i >= 0; i--) {
            Triangle tmp = triangles.get(i);
            if (tmp.getVertex1().getId() >= 1000 || tmp.getVertex2().getId() >= 1000 || tmp.getVertex3().getId() >= 1000) {
                continue;
            }
            edges.add(new Corridor(tmp.getVertex1(), tmp.getVertex2()));
            edges.add(new Corridor(tmp.getVertex1(), tmp.getVertex3()));
            edges.add(new Corridor(tmp.getVertex3(), tmp.getVertex2()));
        }
        return edges;
    }

}
