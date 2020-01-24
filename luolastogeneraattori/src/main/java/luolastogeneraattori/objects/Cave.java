package luolastogeneraattori.objects;

import luolastogeneraattori.cavegenerators.RandomWalk;

public class Cave {

    /**
     * Luolastongeneroinnissa käytettävien karttojen
     * Leveys ja Korkeus
     */
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;

    private char[][] map;

    /**
     * Cave-luokan Konstruktori
     */
    public Cave() {
        this.map = RandomWalk.generateMap(50, 8, 0);
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
}
