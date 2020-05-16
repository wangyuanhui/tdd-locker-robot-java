package cn.xpbootcamp.locker_robot.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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

        assertThrows(RuntimeException.class, () -> robot.put(bag));

        verify(locker1).getStatus();
        verify(locker2).getStatus();
        verify(locker1, never()).put(bag);
        verify(locker2, never()).put(bag);
    }
}
