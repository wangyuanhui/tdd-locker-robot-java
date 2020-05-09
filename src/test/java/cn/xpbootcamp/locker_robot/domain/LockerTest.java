package cn.xpbootcamp.locker_robot.domain;

import cn.xpbootcamp.locker_robot.exception.NoLockerAvailableException;
import cn.xpbootcamp.locker_robot.exception.TicketIsInvalidException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LockerTest {

    @Test
    void should_throw_exception_when_put_given_no_empty_room_left() {
        Locker locker = new Locker(2);
        locker.put(new Bag());
        locker.put(new Bag());

        assertThrows(NoLockerAvailableException.class, () -> locker.put(new Bag()));
    }

    @Test
    void should_get_ticket_when_put_given_has_room_available() {
        Locker locker = new Locker(2);
        Ticket ticket = locker.put(new Bag());

        assertNotNull(ticket);
    }

    @Test
    void should_get_bag_when_take_given_ticket_is_valid() {
        Locker locker = new Locker(1);
        Bag bag = new Bag();
        Ticket ticket = locker.put(bag);

        assertEquals(bag, locker.take(ticket));
    }

    @Test
    void should_get_null_bag_when_take_given_ticket_is_valid() {
        Locker locker = new Locker(1);
        Ticket ticket = locker.put(null);

        assertNull(locker.take(ticket));
    }


    @Test
    void should_throw_exception_when_take_given_ticket_is_invalid() {
        Locker locker = new Locker(1);
        Ticket ticket = new Ticket();

        Assertions.assertThrows(TicketIsInvalidException.class, () -> locker.take(ticket));
    }

    @Test
    void should_return_room_count_increase_when_check_locker_status_given_bag_in_it() {
        Locker locker = new Locker(2);
        assertEquals(2, locker.getStatus());

        locker.put(new Bag());
        assertEquals(1, locker.getStatus());
    }

    @Test
    void should_return_room_count_decrease_when_check_locker_status_given_bag_out() {
        Locker locker = new Locker(2);
        assertEquals(2, locker.getStatus());

        Ticket ticket = locker.put(new Bag());
        assertEquals(1, locker.getStatus());

        locker.take(ticket);
        assertEquals(2, locker.getStatus());
    }
}
