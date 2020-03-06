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

    
    private CorridorList cors;
    boolean[] help;
    
    /**
     * 1. Otetaan random Room Objekti Room[] taulusta
     * 2. Etsitään kaikki ne käytävät, jotka johtavat huoneeseen jossa ei olla vielä käyty
     * 3. Valitaan löydetyistä käytävistä randomilla yksi ja tarkastellaan sitä huonetta, johon se johtaa
     * 4. Toistetaan steppejä 2. ja 3. kunnes ollaan löydetty kaikki huoneet
     * 5. lisätään osa niistä käytävistä joita ei valittu
     * @param rooms     Room[]
     * @param corridors CorridorList
     * @param maxRoomId suurin mahdollinen id
     * @param treeCutOff  todennäköisyys jolla puuhun kuulumaton käytävä otetaan mukaan
     * 
     * @return CorridorList     Randomilla generoitu virittävä puu + muutama extran muodostavat corridor -objektit
     */
    public CorridorList random(Room[] rooms, CorridorList corridors, int maxRoomId, int treeCutOff) {
        CorridorList newCorridors = new CorridorList();
        help = new boolean[maxRoomId];
        Random rnd = new Random();
        
        cors = new CorridorList();
        cors.add(corridors.get(rnd.nextInt(corridors.size())));
        
        while (cors.size() > 0) {
            Corridor c = cors.get(0);
            cors.remove(c);
            boolean ham = false;
            if (!help[c.getFrom().getId()]) {
                findConnectedCorridors(corridors, c.getFrom());
                help[c.getFrom().getId()] = true;
                ham = true;
            }
            if (!help[c.getTo().getId()]) {
                findConnectedCorridors(corridors, c.getTo());
                help[c.getTo().getId()] = true;
                ham = true;
            }
            if (ham) {
                newCorridors.add(c);
            }
        }
        
        for (Corridor c : corridors.toArray()) {
            if (!newCorridors.contains(c) && rnd.nextInt(100) < treeCutOff) {
                newCorridors.add(c);
            }
        }
        return newCorridors;
    }
    
    /**
     * Metodi etsii parametrina annetuista COrridor-Olioista ne, 
     * joiden toinen pää on kiinni parametrina annetussa Room-Oliossa r
     * @param corridors Tarkasteltavat Corridor -Oliot
     * @param r Tarkasteltava Room -Olio
     */
    private void findConnectedCorridors(CorridorList corridors, Room r) {
        Room from;
        Room to;
        for (Corridor c : corridors.toArray()) {
            from = c.getFrom();
            to = c.getTo();
            if (from.getId() == r.getId() && !help[to.getId()]) {
                if (!cors.contains(c)) {
                    cors.add(c);
                }
            } else if (to.getId() == r.getId() && !help[from.getId()]) {
                if (!cors.contains(c)) {
                    cors.add(c);
                }
            }
        }
    }
}
