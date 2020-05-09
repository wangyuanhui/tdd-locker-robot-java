package cn.xpbootcamp.locker_robot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LockerTest {

    @Test
    void should_throw_exception_when_open_given_no_empty_room_left() {
        Locker locker = new Locker(2);
        locker.open();
        locker.open();

        assertThrows(RuntimeException.class, locker::open);
    }

    @Test
    void should_get_ticket_when_open_given_has_room_available() {
        Locker locker = new Locker(2);
        Ticket ticket = locker.open();

        assertNotNull(ticket);
    }

    @Test
    void should_get_package_when_take_given_ticket_is_valid() {
        Locker locker = new Locker(1);
        Ticket ticket = locker.open();

        assertDoesNotThrow(() -> locker.take(ticket));
    }

}
