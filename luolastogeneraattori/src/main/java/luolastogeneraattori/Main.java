package luolastogeneraattori;

import luolastogeneraattori.objects.Cave;

public class Main {

    public static void main(String[] args) {
        Cave cave = new Cave();
        cave = Cave.generateUsingCellularAutomata();
        cave.drawMap();
        System.out.println("================================================================");
        System.out.println("================================================================");
        cave = Cave.generateUsingRandomWalk(150, 16 , 8);
        cave.drawMap();
        System.out.println("================================================================");
        System.out.println("================================================================");
        cave = Cave.generateCombiningCellularAutomataAndRandomWalk();
        cave.drawMap();
    }
}
