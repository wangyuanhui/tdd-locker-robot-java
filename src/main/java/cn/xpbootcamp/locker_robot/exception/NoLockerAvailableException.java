package cn.xpbootcamp.locker_robot.exception;

public class NoLockerAvailableException extends RuntimeException {
    public NoLockerAvailableException() {
        super("No locker available.");
    }
}
