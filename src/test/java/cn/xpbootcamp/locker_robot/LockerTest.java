package cn.xpbootcamp.locker_robot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LockerTest {

    @Test
    void should_throw_exception_when_open_given_no_empty_room_left() {
        Locker locker = new Locker(2);
        locker.open();
        locker.open();

        assertThrows(RuntimeException.class, locker::open);
    }
}