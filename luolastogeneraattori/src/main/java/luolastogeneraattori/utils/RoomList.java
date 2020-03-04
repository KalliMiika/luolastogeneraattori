package luolastogeneraattori.utils;

import luolastogeneraattori.objects.Room;

public class RoomList {

    private Room[] list;
    private int pointer;

    public RoomList() {
        list = new Room[8];
        pointer = 0;
    }

    /**
     * Lisää parametrina annettu Room -objekti listaan
     *
     * @param r Room lisättävä Room -objekti
     */
    public void add(Room r) {
        list[pointer] = r;
        pointer++;
        if (pointer == list.length) {
            expand();
        }
    }

    /**
     * Poistaa parametrina annetun Room -Objektin listasta
     *
     * @param r Room Poistettava Room -Objekti
     */
    public void remove(Room r) {
        Room[] newList = new Room[list.length];
        int fromIndex = 0;
        int toIndex = 0;
        int newPointer = pointer;
        while (fromIndex < pointer) {
            if (!list[fromIndex].equals(r)) {
                newList[toIndex] = list[fromIndex];
                toIndex++;
            } else {
                newPointer--;
            }
            fromIndex++;
        }
        pointer = newPointer;
        list = newList;
    }

    /**
     * Palauttaa indeksiä vastaavan Room -objektin listasta
     *
     * @param i int Palautettavan Room -Objektin indeksi
     * @return Room Indexiä vastaava Room -Objekti
     */
    public Room get(int i) {
        return list[i];
    }
    
    public boolean contains(Room r) {
        for (int i = 0; i < pointer; i++) {
            if (list[i].equals(r)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Palauttaa listan koon
     *
     * @return int Listan koko
     */
    public int size() {
        return pointer;
    }

    /**
     * Palauttaa trimmatun taulukon listan sisällöstä
     *
     * @return Room[] trimmattu taulukko listan sisällöstä
     */
    public Room[] toArray() {
        Room[] ret = new Room[pointer];
        for (int i = 0; i < pointer; i++) {
            ret[i] = list[i];
        }
        return ret;
    }

    private void expand() {
        Room[] newList = new Room[list.length * 2];
        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        list = newList;
    }
}
