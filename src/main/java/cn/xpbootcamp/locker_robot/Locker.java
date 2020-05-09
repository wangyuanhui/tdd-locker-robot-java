package cn.xpbootcamp.locker_robot;

import java.util.HashMap;
import java.util.Map;

public class Locker {

    private int roomsCount;
    private Map<Ticket, Bag> rooms = new HashMap<>();

    public Locker(int roomsCount) {
        this.roomsCount = roomsCount;
    }

    public Ticket put(Bag bag) {
        if (rooms.size() + 1 <= roomsCount) {
            Ticket ticket = new Ticket();
            rooms.put(ticket, bag);
            return ticket;
        } else {
            throw new RuntimeException("No locker available.");
        }
    }

    public Bag take(Ticket ticket) {
        if (!rooms.containsKey(ticket)) {
            throw new RuntimeException("Ticket is invalid");
        }
        return rooms.remove(ticket);
    }
}
