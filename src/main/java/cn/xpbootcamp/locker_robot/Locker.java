package cn.xpbootcamp.locker_robot;

import java.util.HashSet;
import java.util.Set;

public class Locker {

    private int roomsCount;
    private Set<Ticket> rooms = new HashSet<>();

    public Locker(int roomsCount) {
        this.roomsCount = roomsCount;
    }

    public void open() {
        if (rooms.size() + 1 <= roomsCount) {
            rooms.add(new Ticket());
        } else {
            throw new RuntimeException("");
        }
    }
}
