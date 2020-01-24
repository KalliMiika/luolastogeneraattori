package luolastogeneraattori.cavegenerators;

import java.util.Random;
import luolastogeneraattori.objects.Cave;
import luolastogeneraattori.objects.Point;

public class RandomWalk {

    //Liike Vaakasuuntaan
    private static final Point[] HORIZONTAL_DIRECTIONS = {new Point(1, 0), new Point(-1, 0)};
    //Liike Pystysuuntaan
    private static final Point[] VERTICAL_DIRECTIONS = {new Point(0, 1), new Point(0, -1)};

    /**
     * Metodi saa parametreina kaivettavien tunneleiden lukumäärän,
     * tunnelien maksimi- ja minimipituudet. 
     * Metodi aloittaa pyytämällä Cave-Luokalta tyhjän kartan joka koostuu ainoastaan seinistä ('#').
     * Valitaan satunnainen solu kartalta ja muutetaan se lattiaksi ('.').
     * Valitaan satunnainen pituus kaivettavalle tunnelille väliltä minTunnelLength ~ maxTunnelLength
     * Pyydetään flipACoin -metodilta kaivettava suunta.
     * Kaivetaan annettuun suuntaan ja muutetaan seinät lattioiksi kunnes törmätään kartan rajaan tai 
     * ollaan kaivettu satunnaisen pituuden verran.
     * Kun ollaan kaivettu maxTunnels määrittämä määrä tunneleita, 
     * palautetaan tuloksena muodostunut kartta.
     * @param maxTunnels        Kaivettavien tunneleiden lukumäärä
     * @param maxTunnelLength   Kaivettavien tunneleiden maksimipituus
     * @param minTunnelLength   Kaivettavien tunneleiden minimipituus
     * @return                  Palauttaa RandomWalk -algoritmilla generoidun kartan
     */
    public static char[][] generateMap(int maxTunnels, int maxTunnelLength, int minTunnelLength) {
        Random rnd = new Random();
        char[][] map = Cave.generateEmptyMap();
        boolean currentDirection = true;
        Point location = Point.randomPoint();
        map[location.getY()][location.getX()] = '.';
        while (maxTunnels > 0) {
            int length = rnd.nextInt(maxTunnelLength - minTunnelLength) + minTunnelLength;
            Point direction = flipACoin(location, rnd.nextInt(2), currentDirection);
            for (int i = 0; i <= length; i++) {
                if (location.checkAndAdd(direction)) {
                    break;
                }
                map[location.getY()][location.getX()] = '.';
            }
            currentDirection = !currentDirection;
            maxTunnels--;
        }
        return map;
    }

    /**
     * Metodi valitsee kulkusuunnan annettujen parametrien perusteella.
     * Jos muuttuja currentDirection = true, liikutaan vaakasuunnassa.
     * Jos muuttuja currentDirection = false, liikutaan pystysuunnassa.
     * Muuttuja rnd päättää että kumpaan suuntaan valitulla akselilla liikutaan,
     * mutta jos valittu suunta menisi heti kartan rajojen ulkopuolelle, valitaan
     * päinvastainen kulkusuunta.
     * @param location          Kaivajan tämänhetkinen sijainti
     * @param rnd               Saa arvoja 0~1, määrittää mihin suuntaan valitulla akselilla liikutaan.
     * @param currentDirection  Määrittää akselin jolla liikutaan, true = Horizontal | false = Vertical
     * @return                  Palauttaa Point -olion jota käytetään kaivajan liikuttamiseen.
     */
    private static Point flipACoin(Point location, int rnd, boolean currentDirection) {
        if (currentDirection) {
            if (location.check(HORIZONTAL_DIRECTIONS[rnd])) {
                if (rnd == 0) {
                    return HORIZONTAL_DIRECTIONS[1];
                } else {
                    return HORIZONTAL_DIRECTIONS[0];
                }
            }
            return HORIZONTAL_DIRECTIONS[rnd];
        } else {
            if (location.check(VERTICAL_DIRECTIONS[rnd])) {
                if (rnd == 0) {
                    return VERTICAL_DIRECTIONS[1];
                } else {
                    return VERTICAL_DIRECTIONS[0];
                }
            }
            return VERTICAL_DIRECTIONS[rnd];
        }
    }
}
