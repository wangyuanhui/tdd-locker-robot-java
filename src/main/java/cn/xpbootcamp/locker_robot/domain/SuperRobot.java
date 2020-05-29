package cn.xpbootcamp.locker_robot.domain;

import cn.xpbootcamp.locker_robot.exception.LockersAreFullException;
import cn.xpbootcamp.locker_robot.exception.TicketIsInvalidException;
import cn.xpbootcamp.locker_robot.exception.TicketIsInvalidForRobotException;

import java.util.Arrays;
import java.util.List;

public class SuperRobot {

    private List<Locker> lockers;

    public SuperRobot(Locker... lockers) {
        this.lockers = Arrays.asList(lockers);
    }

    public Ticket put(Bag bag) {
        float max = 0;
        Locker putLocker = null;
        for (Locker locker : lockers) {
            if (locker.getFreeRate() > max) {
                max = locker.getFreeRate();
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
