package pl.pacinho.bustimetablesystem.exception;

public class InvalidBusStopNameException extends IllegalArgumentException {

    public InvalidBusStopNameException() {
        super("Starting and ending bus stops can't are not null and be empty!");
    }
}
