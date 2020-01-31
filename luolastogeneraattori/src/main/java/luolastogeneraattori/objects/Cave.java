package luolastogeneraattori.objects;

import java.util.Random;
import luolastogeneraattori.cavegenerators.CellularAutomata;
import luolastogeneraattori.cavegenerators.RandomWalk;

public class Cave {

    /**
     * Luolastongeneroinnissa käytettävien karttojen
     * Leveys ja Korkeus
     */
    public static final int WIDTH = 64;
    public static final int HEIGHT = 20;

    private char[][] map;

    /**
     * Cave-luokan Konstruktori
     */
    public Cave() {
        
    }
    
    /**
     * Cave-Luokan Konstruktori, jolle annetaan
     * parametrina käytettävä kartta
     * @param map   char[][]    Käytettävä kartta
     */
    public Cave(char[][] map) {
        this.map = map;
    }
    
    /**
     * Asettaa luokan käytettäväksi kartaksi
     * parametrina annetun char[][] kartan
     * @param map   char[][]    Käytettävä kartta
     */
    public void setMap(char[][] map) {
        this.map = map;
    }
    
    /**
     * Tulostaa Cave-Olion kartan
     */
    public void drawMap() {
        for (char[] ca : map) {
            for (char c : ca) {
                System.out.print(c);
            }
            System.out.println("");
        }
    }

    /**
     * Generoi uuden kartan, joka koostuu vain seinistä.
     * @return 2 ulotteinen char taulukko -Kartta joka koostuu vain seinistä
     */
    public static char[][] generateEmptyMap() {
        char[][] map = new char[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            for (int o = 0; o < WIDTH; o++) {
                map[i][o] = '#';
            }
        }
        return map;
    }
    
    /**
     * Generoi uuden kartan, jossa on satunnainen määrä seiniä
     * @param wallPercentage solun todennäköisyys olla seinä
     * @return randomgeneroitu kartta
     */
    public static char[][] generateRandomMap(int wallPercentage) {
        char[][] map = new char[HEIGHT][WIDTH];
        Random rnd = new Random();
        for (int column, row = 0; row < HEIGHT; row++) {
            for (column = 0; column < WIDTH; column++) {
                if (row == 0 || column == 0 || row == HEIGHT - 1 || column == WIDTH - 1) {
                    map[row][column] = '#';
                } else if (wallPercentage >= rnd.nextInt(100) + 1) {
                    map[row][column] = '#';
                } else {
                    map[row][column] = '.';
                }
            }
        }
        return map;
    }
    
    /**
     * Luo uuden Cave-olion ja generoi sille käytettävän kartan käyttäen
     * RandomWalk algoritmia annetuilla parametreilla
     * @param tunnelCount       int     Kaivettavien tunneleiden lukumäärä
     * @param maxTunnelLength   int     Kaivettavien tunneleiden maksimipituus
     * @param minTunnelLength   int     Kaivettavien tunneleiden minimipituus
     * @return                  Cave    Cave-Olio joka käyttää generoitua karttaa
     */
    public static Cave generateUsingRandomWalk(int tunnelCount, int maxTunnelLength, int minTunnelLength) {
        return new Cave(RandomWalk.generateMap(tunnelCount, maxTunnelLength, minTunnelLength));
    }
    
    /**
     * Luo uuden Cave-olion ja generoi sille käytettävän kartan käyttäen
     * RandomWalk-algoritmia default parametreilla
     * @return                  Cave    Cave-Olio joka käyttää generoitua karttaa
     */
    public static Cave generateUsingRandomWalk() {
        return new Cave(RandomWalk.generateMap(50, 8, 0));
    }
    
    /**
     * Luo uuden Cave-olion ja generoi sille käytettävän kartan käyttäen
     * Cellular Automata-algoritmia default parametreilla
     * @return                  Cave    Cave-Olio joka käyttää generoitua karttaa
     */
    public static Cave generateUsingCellularAutomata() {
        return new Cave(CellularAutomata.generateMap(40));
    }
    
    /**
     * Luo uuden Cave-olion ja generoi sille käytettävän kartan käyttäen
     * RandomWalk-algoritmia seedin luomiseen johon käytetään
     * Cellular Automata algoritmia.
     * @return                  Cave    Cave-Olio joka käyttää generoitua karttaa
     */
    public static Cave generateCombiningCellularAutomataAndRandomWalk() {
        return new Cave(CellularAutomata.generateMap(RandomWalk.generateMap(150, 16 , 8)));
    }
}
