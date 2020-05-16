package cn.xpbootcamp.locker_robot.exception;

public class TicketIsInvalidForRobotException extends RuntimeException{
    public TicketIsInvalidForRobotException() {
        super("Ticket is invalid for this robot");
    }
}
