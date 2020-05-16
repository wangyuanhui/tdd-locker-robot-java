package cn.xpbootcamp.locker_robot.domain;

import java.util.Arrays;
import java.util.List;

public class Robot {
    private List<Locker> lockers;

    public Robot(Locker... lockers) {
        this.lockers = Arrays.asList(lockers);
    }

    public void put(Bag bag) {
        for (Locker locker : lockers) {
            if (locker.getStatus() > 0) {
                locker.put(bag);
                return;
            }
        }

        throw new RuntimeException("All lockers are full");
    }
}