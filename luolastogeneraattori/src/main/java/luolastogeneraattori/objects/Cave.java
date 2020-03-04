package luolastogeneraattori.objects;

import java.util.ArrayList;
import java.util.Random;
import luolastogeneraattori.cavegenerators.CaveGenerator;
import luolastogeneraattori.cavegenerators.CellularAutomata;
import luolastogeneraattori.cavegenerators.RandomWalk;
import luolastogeneraattori.cavegenerators.TinyKeepish;

public class Cave {

    /**
     * Luolastongeneroinnissa käytettävien karttojen
     * Leveys ja Korkeus
     */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 80;

    char[][] map;

    public static Cave instance;
    
    /**
     * Cave-luokan Konstruktori
     */
    public Cave() {
        map = generateBlankMapWithBorders();
        instance = this;
    }
    
    /**
     * Cave-Luokan Konstruktori, jolle annetaan
     * parametrina käytettävä kartta
     * @param map   char[][]    Käytettävä kartta
     */
    public Cave(char[][] map) {
        this.map = map;
        instance = this;
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
     * Generoi uuden kartan, joka koostuu lattioista ja niitä ympäröivästä seinästä.
     * @return 2 ulotteinen char taulukko -Kartta joka koostuu lattioista ja niitä ympäröivästä seinästä.
     */
    public static char[][] generateBlankMapWithBorders() {
        char[][] map = new char[HEIGHT][WIDTH];
        for (int row = 0; row < HEIGHT; row++) {
            for (int column = 0; column < WIDTH; column++) {
                if (column == 0 || column == WIDTH - 1) {
                    map[row][column] = '|';
                } else if (row == 0 || row == HEIGHT - 1) {
                    map[row][column] = '-';
                } else {
                    map[row][column] = ' ';
                }
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
                    map[row][column] = ' ';
                } else if (wallPercentage >= rnd.nextInt(100) + 1) {
                    map[row][column] = ' ';
                } else {
                    map[row][column] = '.';
                }
            }
        }
        return map;
    }
    
    public static Cave getInstance() {
        return instance;
    }
    
    /**
     * Luo uuden Cave-olion ja generoi sille käytettävän kartan käyttäen
     * RandomWalk algoritmia annetuilla parametreilla
     * @param tunnelCount       int     Kaivettavien tunneleiden lukumäärä
     * @param maxTunnelLength   int     Kaivettavien tunneleiden maksimipituus
     * @param minTunnelLength   int     Kaivettavien tunneleiden minimipituus
     * @return                  Cave    Cave-Olio joka käyttää generoitua karttaa
     */
    public static Raport generateUsingRandomWalk(int tunnelCount, int maxTunnelLength, int minTunnelLength) {
        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long timeBefore = System.currentTimeMillis();
        new Cave(RandomWalk.generateMap(tunnelCount, maxTunnelLength, minTunnelLength));
        long timeAfter = System.currentTimeMillis();
        long actualTimeSpent = timeAfter - timeBefore;
        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long actualMemUsed=afterUsedMem-beforeUsedMem;
        return new Raport(actualTimeSpent, actualMemUsed);
    }
    
    /**
     * Luo uuden Cave-olion ja generoi sille käytettävän kartan käyttäen
     * RandomWalk-algoritmia default parametreilla
     * @return                  Cave    Cave-Olio joka käyttää generoitua karttaa
     */
    public static Raport generateUsingRandomWalk() {
        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long timeBefore = System.currentTimeMillis();
        new Cave(RandomWalk.generateMap(50, 8, 0));
        long timeAfter = System.currentTimeMillis();
        long actualTimeSpent = timeAfter - timeBefore;
        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long actualMemUsed=afterUsedMem-beforeUsedMem;
        return new Raport(actualTimeSpent, actualMemUsed);
    }
    
    /**
     * Luo uuden Cave-olion ja generoi sille käytettävän kartan käyttäen
     * Cellular Automata-algoritmia default parametreilla
     * @return                  Cave    Cave-Olio joka käyttää generoitua karttaa
     */
    public static Raport generateUsingCellularAutomata(int wallPercentage, int iterations, int wallSearchRange1, int wallCutOff1, int wallSearchRange2, int wallCutOff2) {
        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long timeBefore = System.currentTimeMillis();
        new Cave(CellularAutomata.generateMap(wallPercentage, iterations, wallSearchRange1, wallCutOff1, wallSearchRange2, wallCutOff2));
        long timeAfter = System.currentTimeMillis();
        long actualTimeSpent = timeAfter - timeBefore;
        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long actualMemUsed=afterUsedMem-beforeUsedMem;
        return new Raport(actualTimeSpent, actualMemUsed);
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
    
    /**
     * Luo uuden Cave-olion ja generoi sille käytettävän kartan käyttäen
     * CaveGenerator-luokkaa
     * @return  Cave    Cave-Olio joka käyttää CaveGenerator -luokan generoimaa karttaa
     */
    public static Raport generateUsingCaveGenerator(int roomsToGenerate, String spanningTreeType, int treeCutOff) {
        Cave cave = new Cave();
        return new CaveGenerator().generateMap(roomsToGenerate, spanningTreeType, treeCutOff);
    }
    
    public static Raport generateUsingTinyKeepish(int roomsToGenerate, int rounds, int largeCutoff, String spanningTreeType, int treeCutOff) {
        Cave cave = new Cave();
        return new TinyKeepish().generateMap(roomsToGenerate, rounds, largeCutoff, spanningTreeType, treeCutOff);
    }
    
    /**
     * Metodi JUnit -testejä varten
     * Metodi palauttaa Cave-Olion käyttämän kartan
     * @return     char[][] kuvaus cave olion kartasta
     */
    public char[][] getMap() {
        return this.map;
    }
}
