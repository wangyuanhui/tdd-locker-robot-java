package cn.xpbootcamp.locker_robot.exception;

public class LockersAreFullException extends RuntimeException {
    public LockersAreFullException() {
        super("All lockers are full.");
    }
}
