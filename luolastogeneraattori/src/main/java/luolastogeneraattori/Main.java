package luolastogeneraattori;

import luolastogeneraattori.objects.Cave;

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
        cave = Cave.generateUsingCaveGenerator();
        drawMap(cave.getMap());
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
