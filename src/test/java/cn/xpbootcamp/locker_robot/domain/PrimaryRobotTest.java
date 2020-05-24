package cn.xpbootcamp.locker_robot.domain;

import cn.xpbootcamp.locker_robot.exception.LockersAreFullException;
import cn.xpbootcamp.locker_robot.exception.TicketIsInvalidForRobotException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrimaryRobotTest {

    @Test
    void should_thrown_exception_when_put_given_all_lockers_full() {
        Locker locker1 = new Locker(0);
        Locker locker2 = new Locker(0);
        PrimaryRobot robot = new PrimaryRobot(locker1, locker2);
        Bag bag = new Bag();

        assertThrows(LockersAreFullException.class, () -> robot.put(bag));
    }

    @Test
    void should_return_ticket_of_locker1_when_put_given_locker1_available_and_locker2_available() {
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(3);
        PrimaryRobot robot = new PrimaryRobot(locker1, locker2);
        Bag bag = new Bag();

        Ticket ticket = robot.put(bag);

        assertNotNull(ticket);
        assertEquals(1, locker1.getFreeNum());
        assertEquals(bag, locker1.take(ticket));
    }

    @Test
    void should_return_ticket_of_locker2_when_put_given_locker1_is_full_and_locker2_available() {
        Locker locker1 = new Locker(0);
        Locker locker2 = new Locker(3);
        PrimaryRobot robot = new PrimaryRobot(locker1, locker2);
        Bag bag = new Bag();

        Ticket ticket = robot.put(bag);

        assertNotNull(ticket);
        assertEquals(2, locker2.getFreeNum());
        assertEquals(bag, locker2.take(ticket));
    }

    @Test
    void should_throw_exception_when_take_given_ticket_invalid() {
        Locker locker1 = new Locker(0);
        Locker locker2 = new Locker(2);
        PrimaryRobot robot = new PrimaryRobot(locker1, locker2);
        Ticket ticket = new Ticket();

        assertThrows(TicketIsInvalidForRobotException.class, () -> robot.take(ticket));
    }

    @Test
    void should_return_bag_exception_when_take_given_ticket_valid() {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(2);
        PrimaryRobot robot = new PrimaryRobot(locker1, locker2);
        Bag bag = new Bag();

        Ticket ticket = robot.put(bag);

        assertEquals(bag, robot.take(ticket));
    }
}
