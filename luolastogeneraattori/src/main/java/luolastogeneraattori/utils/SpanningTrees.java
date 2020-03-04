package luolastogeneraattori.utils;

import java.util.Random;
import luolastogeneraattori.objects.Corridor;
import luolastogeneraattori.objects.Room;

public class SpanningTrees {

    
    /**
     * 1. Lisätään Room -taulukon ensimmäinen alkio Room kekoon
     * 2. loopataan niin kauan kunnes keko on tyhjä
     *  3. otetaan keon päällimmäinen Room -Objekti 
     *  4. etsitään kaikki ne käytävät, jotka vievät johonkin toiseen huoneeseen,
     *     jossa ei olla vielä käyty ja lisätään uudet huoneet Room kekoon
     * 5. Lisätään sattumanvaraisesti huoneita, joita ei valittu algoritmissa
     * @param rooms     Room[] 
     * @param corridors CorridorList
     * @return CorridorList     Virittävän puun + muutaman extran muodostavat Corridor -Objektit
     */
    public CorridorList basic(Room[] rooms, CorridorList corridors, int maxRoomId, int treeCutOff) {
        CorridorList newCorridors = new CorridorList();
        boolean[] help = new boolean[maxRoomId];
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
        for (Corridor c : corridors.toArray()) {
            if (!newCorridors.contains(c) && rnd.nextInt(100) < treeCutOff) {
                newCorridors.add(c);
            }
        }
        return newCorridors;
    }

    /**
     * 1. Otetaan random Room Objekti Room[] taulusta
     * 2. Etsitään kaikki ne käytävät, jotka johtavat huoneeseen jossa ei olla vielä käyty
     * 3. Valitaan löydetyistä käytävistä randomilla yksi ja tarkastellaan sitä huonetta, johon se johtaa
     * 4. Toistetaan steppejä 2. ja 3. kunnes ollaan löydetty kaikki huoneet
     * 5. lisätään osa niistä käytävistä joita ei valittu
     * @param rooms     Room[]
     * @param corridors CorridorList
     * @return CorridorList     Randomilla generoitu virittävä puu + muutama extran muodostavat corridor -objektit
     */
    public CorridorList random(Room[] rooms, CorridorList corridors, int maxRoomId, int treeCutOff) {
        CorridorList newCorridors = new CorridorList();
        boolean[] help = new boolean[maxRoomId];
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
        for (Corridor c : corridors.toArray()) {
            if (!newCorridors.contains(c) && rnd.nextInt(100) < treeCutOff) {
                newCorridors.add(c);
            }
        }
        return newCorridors;
    }
}
