package luolastogeneraattori;

import luolastogeneraattori.cavegenerators.TinyKeepish;
import luolastogeneraattori.objects.Cave;
import luolastogeneraattori.ui.Graph;

public class Main {

    public static void main(String[] args) {
        Cave cave = new Cave();
//        cave = Cave.generateUsingCellularAutomata();
//        drawMap(cave.getMap());
//        System.out.println("================================================================");
//        System.out.println("================================================================");
//        cave = Cave.generateUsingRandomWalk(150, 16 , 8);
//        drawMap(cave.getMap());
//        System.out.println("================================================================");
//        System.out.println("================================================================");
//        cave = Cave.generateCombiningCellularAutomataAndRandomWalk();
//        drawMap(cave.getMap());
//        System.out.println("================================================================");
//        System.out.println("================================================================");
        //Cave.generateUsingCaveGenerator();
        //drawMap(cave.getMap());
        //new TinyKeepish().generateMap(100, 3, 30);
        //drawMap(cave.getMap());
        
        Graph g = new Graph();
        //g.main(rooms, largeRooms, corridors);
        g.main();
    }

    private static void drawMap(char[][] map) {
        for (char[] ca : map) {
            for (char c : ca) {
                System.out.print(c);
            }
            System.out.println("");
        }
    }
}
