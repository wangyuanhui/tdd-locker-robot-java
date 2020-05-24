package cn.xpbootcamp.locker_robot.domain;

import cn.xpbootcamp.locker_robot.exception.LockersAreFullException;
import cn.xpbootcamp.locker_robot.exception.TicketIsInvalidForRobotException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SmartRobotTest {

    @Test
    void should_thrown_exception_when_put_given_all_lockers_full() {
        Locker locker1 = new Locker(0);
        Locker locker2 = new Locker(0);
        SmartRobot smartRobot = new SmartRobot(locker1, locker2);
        Bag bag = new Bag();

        assertThrows(LockersAreFullException.class, () -> smartRobot.put(bag));
    }

    @Test
    void should_return_ticket_of_locker2_when_put_given_locker2_has_2_free_locker1_has_1_free() {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(2);
        SmartRobot smartRobot = new SmartRobot(locker1, locker2);
        Bag bag = new Bag();

        Ticket ticket = smartRobot.put(bag);

        assertNotNull(ticket);
        assertEquals(1, locker1.getFreeNum());
        assertEquals(1, locker2.getFreeNum());
        assertEquals(bag, locker2.take(ticket));
    }

    @Test
    void should_return_ticket_of_locker1_when_put_given_locker2_has_1_free_locker1_has_2_free() {
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(1);
        SmartRobot smartRobot = new SmartRobot(locker1, locker2);
        Bag bag = new Bag();

        Ticket ticket = smartRobot.put(bag);

        assertNotNull(ticket);
        assertEquals(1, locker1.getFreeNum());
        assertEquals(1, locker2.getFreeNum());
        assertEquals(bag, locker1.take(ticket));
    }

    @Test
    void should_return_ticket_of_locker1_or_locker2_when_put_given_locker1_and_locker2_have_same_most_free_rooms() {
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(2);
        Locker locker3 = new Locker(1);
        SmartRobot smartRobot = new SmartRobot(locker1, locker2);
        Bag bag = new Bag();
        Ticket ticket = smartRobot.put(bag);

        assertNotNull(ticket);
        int free1 = locker1.getFreeNum();
        int free2 = locker2.getFreeNum();
        int free3 = locker3.getFreeNum();
        assertEquals(1, free3);
        assertTrue((free1 == 2 && free2 == 1) || (free1 == 1 && free2 == 2));
    }

    @Test
    void should_throw_exception_when_take_given_ticket_invalid() {
        Locker locker1 = new Locker(0);
        Locker locker2 = new Locker(2);
        SmartRobot smartRobot = new SmartRobot(locker1, locker2);
        Ticket ticket = new Ticket();

        assertThrows(TicketIsInvalidForRobotException.class, () -> smartRobot.take(ticket));
    }

    @Test
    void should_return_bag_exception_when_take_given_ticket_valid() {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(2);
        SmartRobot smartRobot = new SmartRobot(locker1, locker2);
        Bag bag = new Bag();

        Ticket ticket = smartRobot.put(bag);

        assertEquals(bag, smartRobot.take(ticket));
    }
}
