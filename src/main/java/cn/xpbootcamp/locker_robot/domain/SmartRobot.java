package cn.xpbootcamp.locker_robot.domain;

import cn.xpbootcamp.locker_robot.exception.LockersAreFullException;
import cn.xpbootcamp.locker_robot.exception.TicketIsInvalidException;
import cn.xpbootcamp.locker_robot.exception.TicketIsInvalidForRobotException;

import java.util.Arrays;
import java.util.List;

public class SmartRobot {
    private List<Locker> lockers;

    public SmartRobot(Locker... lockers) {
        this.lockers = Arrays.asList(lockers);
    }

    public Ticket put(Bag bag) {
        int max = 0;
        Locker putLocker = null;
        for (Locker locker : lockers) {
            if (locker.getFreeNum() > max) {
                max = locker.getFreeNum();
                putLocker = locker;
            }
        }

        if (putLocker == null) {
            throw new LockersAreFullException();
        }

        return putLocker.put(bag);
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
