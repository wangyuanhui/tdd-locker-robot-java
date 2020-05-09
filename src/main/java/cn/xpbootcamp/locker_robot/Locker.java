package cn.xpbootcamp.locker_robot;

import java.util.HashSet;
import java.util.Set;

public class Locker {

    private int roomsCount;
    private Set<Ticket> rooms = new HashSet<>();

    public Locker(int roomsCount) {
        this.roomsCount = roomsCount;
    }

    public Ticket put() {
        if (rooms.size() + 1 <= roomsCount) {
            Ticket ticket = new Ticket();
            rooms.add(ticket);
            return ticket;
        } else {
            throw new RuntimeException("");
        }
    }

    public void take(Ticket ticket) {
        if (!rooms.contains(ticket)) {
            throw new RuntimeException("");
        }
        rooms.remove(ticket);
    }
}
