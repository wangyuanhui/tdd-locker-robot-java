package cn.xpbootcamp.locker_robot.domain;

import cn.xpbootcamp.locker_robot.exception.NoLockerAvailableException;
import cn.xpbootcamp.locker_robot.exception.TicketIsInvalidException;

import java.util.HashMap;
import java.util.Map;

public class Locker {

    private int roomsCount;
    private Map<Ticket, Bag> rooms = new HashMap<>();

    public Locker(int roomsCount) {
        this.roomsCount = roomsCount;
    }

    public Ticket put(Bag bag) {
        if (rooms.size() == roomsCount) {
            throw new NoLockerAvailableException();
        }
        Ticket ticket = new Ticket();
        rooms.put(ticket, bag);
        return ticket;
    }

    public Bag take(Ticket ticket) {
        if (!rooms.containsKey(ticket)) {
            throw new TicketIsInvalidException();
        }
        return rooms.remove(ticket);
    }

    public int getFreeNum() {
        return roomsCount - rooms.size();
    }
}
