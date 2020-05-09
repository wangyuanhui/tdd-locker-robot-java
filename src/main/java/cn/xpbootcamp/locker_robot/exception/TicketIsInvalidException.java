package cn.xpbootcamp.locker_robot.exception;

public class TicketIsInvalidException extends RuntimeException {
    public TicketIsInvalidException() {
        super("Ticket is invalid");
    }
}
