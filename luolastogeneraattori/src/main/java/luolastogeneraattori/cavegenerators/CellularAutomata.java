package luolastogeneraattori.cavegenerators;

import luolastogeneraattori.objects.Cave;
import luolastogeneraattori.objects.Point;

public class CellularAutomata {
    
    /**
     * Cellular Automatan Luolastongeneraation aloitusfunktio.
     * Saa Parametriksi todennäköisyyden jolla ruutu on seinä luola-pohjan generointia varten
     * Generointi tapahtuu iteratiivisesti siten, että edellisen kierroksen tulos on seuraavan
     * kierroksen syöte. Eri kierroksilla käytetään eri parametreja erilaisten tavotteiden
     * saavuttamiseksi
     * @param wallPercentage Todennäköisyys että ruutu on seinäpala luola-pohjan generoinnissa
     * @return Palauttaa CellularAutomatalla generoidun char[][] luolaston
     */
    public static char[][] generateMap(int wallPercentage) {
        char[][] map = Cave.generateRandomMap(wallPercentage);
        map = makeCaverns(map, 1, 5, 0, 0);
        map = makeCaverns(map, 1, 5, 0, 0);
        map = makeCaverns(map, 1, 5, 0, 0);
        map = makeCaverns(map, 1, 5, 3, 4);
        map = makeCaverns(map, 1, 5, 0, 0);
        map = makeCaverns(map, 1, 5, 0, 0);
        map = makeCaverns(map, 1, 5, 0, 0);
        map = makeCaverns(map, 1, 5, 3, 4);
        map = makeCaverns(map, 1, 5, 0, 0);
        map = makeCaverns(map, 1, 5, 0, 0);
        map = makeCaverns(map, 1, 5, 0, 0);
        map = makeCaverns(map, 1, 5, 3, 4);
        map = makeCaverns(map, 1, 5, 0, 0);
        map = makeCaverns(map, 1, 5, 0, 0);
        map = makeCaverns(map, 1, 5, 0, 0);
        return map;
    }
    
    /**
     * Cellular Automatan Luolastongeneraation aloitusfunktio.
     * Saa Parametriksi char[][] kartan, jota käytetään seedinä algoritmin suorituksessa
     * Generointi tapahtuu iteratiivisesti siten, että edellisen kierroksen tulos on seuraavan
     * kierroksen syöte. Eri kierroksilla käytetään eri parametreja erilaisten tavotteiden
     * saavuttamiseksi
     * @param wallPercentage Todennäköisyys että ruutu on seinäpala luola-pohjan generoinnissa
     * @return Palauttaa CellularAutomatalla generoidun char[][] luolaston
     */
    public static char[][] generateMap(char[][] map) {
        map = makeCaverns(map, 1, 5, 0, 0);
        map = makeCaverns(map, 1, 5, 0, 0);
        map = makeCaverns(map, 1, 5, 0, 0);
        map = makeCaverns(map, 1, 5, 2, 2);
        map = makeCaverns(map, 1, 5, 0, 0);
        map = makeCaverns(map, 1, 5, 0, 0);
        map = makeCaverns(map, 1, 5, 0, 0);
        map = makeCaverns(map, 1, 5, 2, 2);
        map = makeCaverns(map, 1, 5, 0, 0);
        map = makeCaverns(map, 1, 5, 0, 0);
        map = makeCaverns(map, 1, 5, 0, 0);
        map = makeCaverns(map, 1, 5, 2, 2);
        map = makeCaverns(map, 1, 5, 0, 0);
        map = makeCaverns(map, 1, 5, 0, 0);
        map = makeCaverns(map, 1, 5, 0, 0);
        return map;
    }
    
    /**
     * CellularAutomata -algoritmin toteutus
     * Seediksi saatu luolasto(char[][] map) käydään läpi ruutu ruudulta.
     * Lasketaan ympärillä olevien seinien lukumäärä kaksilla eri parametreilla.
     * Jos ensimmäisellä parametriparilla saatujen seinien lukumäärä on WallCutOff1 tai enemmän,
     * Tai, jos toinen parametripari on käytössä (jos wallCutOff2 == 0, niin toista paria ei käytetä),
     * toinen parametriparilla saatujen seinien lukumäärä on wallCutOff2 tai vähemmän, niin 
     * ruutu on SEINÄ, muutoin ruutu on LATTIA.
     * Lopuksi palautetaan käsitelty luolasto.
     * @param map               char[][]    Seediksi annettu Luolasto
     * @param wallSearchRange   int         Ensimmäisen seinienlaskennan seinien etsintä etäisyys
     * @param wallCutOff1       int         Ensimmäisen seinienlaskennan raja (Täytyy olla enemmän seiniä kuin tämä), jolloin ruutu on SEINÄ
     * @param wallSearchRange2  int         Toisen seinienlaskennan seinien etsintä etäisyys
     * @param wallCutOff2       int         Toisen seinienlaskennan raja (Täytyy olla vähemmän seiniä kuin tämä), jolloin ruutu on SEINÄ
     * @return                  char[][]    Algoritmin siistimä luolasto seedin pohjalta
     */
    private static char[][] makeCaverns(char[][] map, int wallSearchRange1, int wallCutOff1, int wallSearchRange2, int wallCutOff2) {
        char[][] result = new char[Cave.HEIGHT][Cave.WIDTH];
        int wallCount = 0;
        int wallCount2 = 0;
        for (int column, row = 0; row < Cave.HEIGHT; row++) {
            for (column = 0; column < Cave.WIDTH; column++) {   
                wallCount = countAdjacentWalls(map, new Point(column, row), wallSearchRange1);
                wallCount2 = countAdjacentWalls(map, new Point(column, row), wallSearchRange2);
                if (wallCount >= wallCutOff1 || (wallCount2 <= wallCutOff2 && wallCutOff2 > 0)) {
                    result[row][column] = '#';
                } else {
                    result[row][column] = '.';
                }
            }
        }
        return result;
    }
    
    /**
     * Laskee parametreilla annetussa luolastossa(char[][] map) annettua kohtaa(Point location)
     * ympäröivien seinien lukumäärän annetulla säteellä(int range)
     * @param map       char[][]    Tarkasteltava luolasto
     * @param location  Point       Tarkasteltava kohta luolastossa
     * @param range     int         Tarkastelun säde annestusta kohdasta.
     * @return          int         Löydettyjen seinien lukumäärä
     */
    private static int countAdjacentWalls(char[][] map, Point location, int range) {
        Point start = location.clone();
        start.subtract(new Point(range, range));
        Point end = location.clone();
        end.add(new Point(range, range));
    
        int counter = 0;

        for (int column, row = start.getY(); row <= end.getY(); row++) {
            for (column = start.getX(); column <= end.getX(); column++) {
                if (row <= 0 || column <= 0 || row >= Cave.HEIGHT || column >= Cave.WIDTH) {
                    counter++;
                } else if (map[row][column] == '#') {
                    counter++;
                }
            }
        }
        
        return counter;
    }
}
