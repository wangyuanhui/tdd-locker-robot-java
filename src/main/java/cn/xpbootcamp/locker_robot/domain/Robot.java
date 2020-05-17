package cn.xpbootcamp.locker_robot.domain;

import cn.xpbootcamp.locker_robot.exception.LockersAreFullException;
import cn.xpbootcamp.locker_robot.exception.TicketIsInvalidException;
import cn.xpbootcamp.locker_robot.exception.TicketIsInvalidForRobotException;

import java.util.Arrays;
import java.util.List;

public class Robot {
    private List<Locker> lockers;

    public Robot(Locker... lockers) {
        this.lockers = Arrays.asList(lockers);
    }

    public Ticket put(Bag bag) {
        for (Locker locker : lockers) {
            if (locker.getStatus() > 0) {
                return locker.put(bag);
            }
        }

        throw new LockersAreFullException();
    }

    public Bag take(Ticket ticket) {
        Bag bag = null;
        boolean success = false;
        for (Locker locker : lockers) {
            try {
                bag = locker.take(ticket);
                success = true;
            } catch (TicketIsInvalidException ignore) {
            }
        }
        if (!success) {
            throw new TicketIsInvalidForRobotException();
        }
        return bag;
    }
}
