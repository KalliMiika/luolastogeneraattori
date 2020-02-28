package luolastogeneraattori;

import luolastogeneraattori.objects.Cave;
import luolastogeneraattori.objects.Corridor;
import luolastogeneraattori.objects.Point;
import luolastogeneraattori.objects.Room;

public class Main {

    public static void main(String[] args) {
        //Cave cave = new Cave();
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
        //cave = Cave.generateUsingCaveGenerator();
        //drawMap(cave.getMap());
        Cave cave = new Cave();
        Room r1 = new Room(new Point(10, 30), 3, 3, 6, 0);
        r1.drawRoom();
        Room r2 = new Room(new Point(30, 10), 3, 3, 6, 1);
        r2.drawRoom();
        Corridor c = new Corridor(r1, r2);
        c.drawCorridorWithPriorityListAllowDiagonal();
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
