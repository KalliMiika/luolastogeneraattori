package luolastogeneraattori.objects;

public class Corridor {

    private Room from;
    private Room to;

    public Corridor(Room from, Room to) {
        this.from = from;
        this.to = to;
    }

    public Room getFrom() {
        return this.from;
    }

    public Room getTo() {
        return this.to;
    }

    public boolean equals(Corridor corridor) {
        if (this.from.getId() == corridor.getFrom().getId() && this.to.getId() == corridor.getTo().getId()) {
            return true;
        }
        else if (this.to.getId() == corridor.getFrom().getId() && this.from.getId() == corridor.getTo().getId()) {
            return true;
        }
        return false;
    }
}
