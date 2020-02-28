
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
    
    public void put(Room r) {
        RoomStack newRoomStack = new RoomStack(this.next, this.room);
        this.next = newRoomStack;
        this.room = r;
    }
    
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
    
    public boolean hasNext() {
        if (this.next == null) {
            return false;
        }
        return true;
    }
    
    public RoomStack getNext() {
        return this.next;
    }
    
    public Room peek() {
        return this.room;
    }
}
