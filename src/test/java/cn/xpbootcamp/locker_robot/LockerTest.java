package cn.xpbootcamp.locker_robot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LockerTest {

    @Test
    void should_throw_exception_when_put_given_no_empty_room_left() {
        Locker locker = new Locker(2);
        locker.put(new Bag());
        locker.put(new Bag());

        assertThrows(RuntimeException.class, () -> locker.put(new Bag()));
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
    void should_throw_exception_when_take_given_ticket_is_invalid() {
        Locker locker = new Locker(1);
        Ticket ticket = new Ticket();

        assertThrows(RuntimeException.class, () -> locker.take(ticket));
    }
}
