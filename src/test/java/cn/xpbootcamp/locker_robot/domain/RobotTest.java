package cn.xpbootcamp.locker_robot.domain;

import cn.xpbootcamp.locker_robot.exception.LockersAreFullException;
import cn.xpbootcamp.locker_robot.exception.TicketIsInvalidException;
import cn.xpbootcamp.locker_robot.exception.TicketIsInvalidForRobotException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    void should_return_ticket_of_locker2_when_put_given_locker1_is_full_and_locker2_available() {
        Locker locker1 = new Locker(0);
        Locker locker2 = new Locker(2);
        // TODO
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
