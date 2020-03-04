package luolastogeneraattori.cavegenerators;

import luolastogeneraattori.objects.Corridor;
import luolastogeneraattori.objects.Raport;
import luolastogeneraattori.objects.Room;
import luolastogeneraattori.utils.Delaunay;
import luolastogeneraattori.utils.CorridorList;
import luolastogeneraattori.utils.SpanningTrees;

public class CaveGenerator {
    
    private Room[] rooms;
    private CorridorList corridors;
    
    /**
     * Tämän huoneengenerointi algoritmin päämetodi
     * 1. Aloitetaan generoimalla parametrina annetun roomsToGenerate verran huoneita.
     * 2. Ajetaan simulaatio jossa huoneet kimpoilevat seinistä ja toisistaan törmätessään,
     *      kunnes törmäilyjä ei enään tapahdu. Oletetaan, että tällöin yksikään huone ei
     *      ole toisen huoneen päällä tai osittain seinän sisällä.
     * 3. Muodostetaan generoiduista huoneista verkko Delaunay Triangulation algoritmilla
     * 4. Muodostetaan generoidusta verkosta virittävä puu
     * 5. generoidaan käytävät a* haulla
     */
    public Raport generateMap(int roomsToGenerate, String spanningTreeType, int treeCutOff) {
        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long t1 = System.currentTimeMillis();
        this.rooms = new Room[roomsToGenerate];             //Alustetaan Room[] rooms sopivan kokoiseksi
        for (int i = 0; i < roomsToGenerate; i++) {         //Generoidaan satunnaiset huoneet
            this.rooms[i] = Room.generateRandomRoom(i);
        }
        while (checkCollisions() > 0) {                     //Huoneiden törmäilysimulaatio
        }            
        
        corridors = new Delaunay().triangulate(rooms).clearDuplicates();        
        switch (spanningTreeType) {
            case "BASIC":
                corridors = new SpanningTrees().basic(rooms, corridors, roomsToGenerate, treeCutOff);
                break;
            case "RANDOM":
                corridors = new SpanningTrees().random(rooms, corridors, roomsToGenerate, treeCutOff);
                break;
            default:
                break;
        }
        //corridors = new SpanningTrees().random(rooms, corridors, roomsToGenerate);
        
        draw();                                             //Piirtää generoinnin lopputuloksen Cave-Olion karttaan
        long actualTimeSpent = System.currentTimeMillis() - t1;
        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long actualMemUsed=afterUsedMem-beforeUsedMem;
        return new Raport(actualTimeSpent, actualMemUsed);
    }
    
    /**
     * Metodi kutsuu Room-olioiden checkCollision metodia kaikkien huoneiden kesken
     * ja laskee tapahtuneiden törmäyksien lukumäärän
     * @return  int     Tapahtuneiden törmäysten lukumäärä
     */
    private int checkCollisions() {
        int collisions = 0;
        for (int i = 0; i < rooms.length; i++) {
            for (int o = i + 1; o < rooms.length; o++) {
                collisions += rooms[i].checkCollision(rooms[o]);
            }
            collisions += rooms[i].checkCollisionWithWalls();
        }
        return collisions;
    }
 
    /**
     * Metodi piirtää generoidut huoneet Cave-Olion kartalle
     */
    private void draw() {
        for (Room room : this.rooms) {
            room.drawRoom();
        } 
        for (Corridor corridor : this.corridors.toArray()) {
            corridor.drawCorridorWithPriorityList();
        }
    }
    
    /**
     * JUnit -testejä varten
     * Metodi palauttaa Room[] taulukon generoiduista huoneista
     * @return      Room[]  Taulukko joka sisältää generoidut huoneet
     */
    public Room[] getRooms() {
        return this.rooms;
    }
}
