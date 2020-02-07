package luolastogeneraattori.cavegenerators;

import luolastogeneraattori.objects.Room;

public class CaveGenerator {
    
    private Room[] rooms;
    
    /**
     * Tämän huoneengenerointi algoritmin päämetodi
     * 1. Aloitetaan generoimalla parametrina annetun roomsToGenerate verran huoneita.
     * 2. Ajetaan simulaatio jossa huoneet kimpoilevat seinistä ja toisistaan törmätessään,
     *      kunnes törmäilyjä ei enään tapahdu. Oletetaan, että tällöin yksikään huone ei
     *      ole toisen huoneen päällä tai osittain seinän sisällä.
     * 
     */
    public void generateMap(int roomsToGenerate) {
        this.rooms = new Room[roomsToGenerate];             //Alustetaan Room[] rooms sopivan kokoiseksi
        for (int i = 0; i < roomsToGenerate; i++) {         //Generoidaan satunnaiset huoneet
            this.rooms[i] = Room.generateRandomRoom(i);
        }
        while (checkCollisions() > 0) {                     //Huoneiden törmäilysimulaatio
        }                    
        draw();                                             //Piirtää generoinnin lopputuloksen Cave-Olion karttaan
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
//        for (Room room : this.rooms) {
//            room.drawCircle();
//        }
        for (Room room : this.rooms) {
            room.drawCenter();
        }
        for (Room room : this.rooms) {
            room.drawRoom();
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
