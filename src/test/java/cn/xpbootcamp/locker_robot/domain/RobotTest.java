package cn.xpbootcamp.locker_robot.domain;

import cn.xpbootcamp.locker_robot.exception.LockersAreFullException;
import cn.xpbootcamp.locker_robot.exception.TicketIsInvalidForRobotException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RobotTest {

    @Test
    void should_thrown_exception_when_put_given_all_lockers_full() {
        Locker locker1 = new Locker(0);
        Locker locker2 = new Locker(0);
        Robot robot = new Robot(locker1, locker2);
        Bag bag = new Bag();

        assertThrows(LockersAreFullException.class, () -> robot.put(bag));
    }

    @Test
    void should_return_ticket_of_locker2_when_put_given_locker2_has_most_free_rooms() {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(2);
        Robot robot = new Robot(locker1, locker2);
        Bag bag = new Bag();

        Ticket ticket = robot.put(bag);

        assertNotNull(ticket);
        assertEquals(1, locker1.getStatus());
        assertEquals(1, locker2.getStatus());
        assertEquals(bag, locker2.take(ticket));
    }

    @Test
    void should_return_ticket_of_locker1_or_locker2_when_put_given_locker1_and_locker2_have_same_free_rooms() {
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(2);
        Robot robot = new Robot(locker1, locker2);
        Bag bag = new Bag();
        Ticket ticket = robot.put(bag);

        assertNotNull(ticket);
        int status1 = locker1.getStatus();
        int status2 = locker2.getStatus();
        assertTrue((status1 == 2 && status2 == 1) || (status1 == 1 && status2 == 2));
    }

    @Test
    void should_throw_exception_when_take_given_ticket_invalid() {
        Locker locker1 = new Locker(0);
        Locker locker2 = new Locker(2);
        Robot robot = new Robot(locker1, locker2);
        Ticket ticket = new Ticket();

        assertThrows(TicketIsInvalidForRobotException.class, () -> robot.take(ticket));
    }

    @Test
    void should_return_bag_exception_when_take_given_ticket_valid() {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(2);
        Robot robot = new Robot(locker1, locker2);
        Bag bag = new Bag();

        Ticket ticket = robot.put(bag);

        assertEquals(bag, robot.take(ticket));
    }
}
