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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RobotTest {

    @Mock
    private Locker locker1;

    @Mock
    private Locker locker2;

    @Test
    void should_thrown_exception_when_put_given_all_lockers_full() {
        Robot robot = new Robot(locker1, locker2);
        when(locker1.getStatus()).thenReturn(0);
        when(locker2.getStatus()).thenReturn(0);
        Bag bag = mock(Bag.class);

        assertThrows(LockersAreFullException.class, () -> robot.put(bag));

        verify(locker1).getStatus();
        verify(locker2).getStatus();
        verify(locker1, never()).put(bag);
        verify(locker2, never()).put(bag);
    }

    @Test
    void should_return_ticket_of_locker1_when_put_given_locker1_available_and_locker2_available() {
        Robot robot = new Robot(locker1, locker2);
        Bag bag = mock(Bag.class);
        Ticket ticket = mock(Ticket.class);
        when(locker1.getStatus()).thenReturn(2);
        when(locker2.getStatus()).thenReturn(3);
        when(locker1.put(bag)).thenReturn(ticket);

        assertEquals(ticket, robot.put(bag));

        verify(locker1).getStatus();
        verify(locker2, never()).getStatus();
        verify(locker1).put(bag);
        verify(locker2, never()).put(bag);
    }


    @Test
    void should_return_ticket_of_locker1_when_put_given_locker1_is_full_and_locker2_available() {
        Robot robot = new Robot(locker1, locker2);
        Bag bag = mock(Bag.class);
        Ticket ticket = mock(Ticket.class);
        when(locker1.getStatus()).thenReturn(0);
        when(locker2.getStatus()).thenReturn(3);
        when(locker2.put(bag)).thenReturn(ticket);

        assertEquals(ticket, robot.put(bag));

        verify(locker1).getStatus();
        verify(locker2).getStatus();
        verify(locker1, never()).put(bag);
        verify(locker2).put(bag);
    }

    @Test
    void should_throw_exception_when_take_given_ticket_invalid() {
        Robot robot = new Robot(locker1, locker2);
        Ticket ticket = mock(Ticket.class);
        when(locker1.take(ticket)).thenThrow(TicketIsInvalidException.class);
        when(locker2.take(ticket)).thenThrow(TicketIsInvalidException.class);

        assertThrows(TicketIsInvalidForRobotException.class, () -> robot.take(ticket));

        verify(locker1).take(ticket);
        verify(locker2).take(ticket);
    }

    @Test
    void should_return_bag_exception_when_take_given_ticket_valid() {
        Robot robot = new Robot(locker1, locker2);
        Bag bag = mock(Bag.class);
        Ticket ticket = mock(Ticket.class);
        when(locker1.take(ticket)).thenThrow(TicketIsInvalidException.class);
        when(locker2.take(ticket)).thenReturn(bag);

        assertEquals(bag, robot.take(ticket));

        verify(locker1).take(ticket);
        verify(locker2).take(ticket);
    }
}
