package luolastogeneraattori.utils;

import java.util.Random;
import luolastogeneraattori.objects.Corridor;
import luolastogeneraattori.objects.Room;

public class SpanningTrees {

    public CorridorList basic(Room[] rooms, CorridorList corridors) {
        CorridorList newCorridors = new CorridorList();
        boolean[] help = new boolean[rooms.length];
        RoomStack rs = new RoomStack();
        rs.put(rooms[0]);
        help[rooms[0].getId()] = true;
        while (rs.hasNext()) {
            Room r = rs.pop();
            Room from = null;
            Room to = null;
            for (Corridor c : corridors.toArray()) {
                from = c.getFrom();
                to = c.getTo();
                if (from.getId() == r.getId() && !help[to.getId()]) {
                    rs.put(to);
                    newCorridors.add(c);
                    help[to.getId()] = true;
                } else if (to.getId() == r.getId() && !help[from.getId()]) {
                    rs.put(from);
                    newCorridors.add(c);
                    help[from.getId()] = true;
                }
            }
        }
        Random rnd = new Random();
        int cutoff = 25;
        for (Corridor c : corridors.toArray()) {
            if (!newCorridors.contains(c) && rnd.nextInt(100) < cutoff) {
                newCorridors.add(c);
            }
        }
        return newCorridors;
    }

    public CorridorList random(Room[] rooms, CorridorList corridors) {
        CorridorList newCorridors = new CorridorList();
        boolean[] help = new boolean[rooms.length];
        Random rnd = new Random();

        Room r = rooms[rnd.nextInt(rooms.length)];
        Room from;
        Room to;
        help[r.getId()] = true;

        int counter = 1;
        CorridorList tmp = new CorridorList();
        while (counter < rooms.length) {
            for (Corridor c : corridors.toArray()) {
                from = c.getFrom();
                to = c.getTo();
                if (from.getId() == r.getId() && !help[to.getId()]) {
                    if (!tmp.contains(c)) {
                        tmp.add(c);
                    }
                } else if (to.getId() == r.getId() && !help[from.getId()]) {
                    if (!tmp.contains(c)) {
                        tmp.add(c);
                    }
                }
            }
            Corridor cor = tmp.get(rnd.nextInt(tmp.size()));
            tmp.remove(cor);
            from = cor.getFrom();
            to = cor.getTo();
            if (help[from.getId()] && !help[to.getId()]) {
                r = to;
                help[r.getId()] = true;
                newCorridors.add(cor);
                counter++;
            } else if (help[to.getId()] && !help[from.getId()]) {
                r = from;
                help[r.getId()] = true;
                newCorridors.add(cor);
                counter++;
            }
        }
        int cutoff = 20;
        for (Corridor c : corridors.toArray()) {
            if (!newCorridors.contains(c) && rnd.nextInt(100) < cutoff) {
                newCorridors.add(c);
            }
        }
        return newCorridors;
    }
}
