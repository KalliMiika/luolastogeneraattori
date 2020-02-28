
package luolastogeneraattori.utils;

import luolastogeneraattori.objects.Room;

public class RoomStack {

    private RoomStack next;
    private Room room;
    
    public RoomStack() {
        next = null;
        room = null;
    }
    
    public RoomStack(RoomStack next, Room room) {
        this.next = next;
        this.room = room;
    }
    
    /**
     * Lisää parametrina annetun Room -Objektin kekoon
     * @param r Room    Parametrina annettu Room -Objekti
     */
    public void put(Room r) {
        RoomStack newRoomStack = new RoomStack(this.next, this.room);
        this.next = newRoomStack;
        this.room = r;
    }
    
    /**
     * Palauttaa keon päällimmäisen Room -Objektin
     * @return  Room    Päällimmäinen Room -Objekti
     */
    public Room pop() {
        if (this.hasNext()) {
           Room ret = this.room;
           this.room = next.peek();
           this.next = next.getNext();
           return ret;
        }
        System.out.println("Can't pop from empty stack");
        return null;
    }
    
    /**
     * Tarkastaa onko keossa päällimmäisen objekin jälkeisiä objekteja
     * @return true jos on, false muutoin
     */
    public boolean hasNext() {
        if (this.next == null) {
            return false;
        }
        return true;
    }
    
    /**
     * Palauttaa seuraavana olevan Room-Objektin
     * @return  
     */
    public RoomStack getNext() {
        return this.next;
    }
    
    /**
     * Palauttaa tämän Room-Objektin
     * @return 
     */
    public Room peek() {
        return this.room;
    }
}
