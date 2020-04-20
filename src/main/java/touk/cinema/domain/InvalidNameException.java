package touk.cinema.domain;

public class InvalidNameException extends RuntimeException {

    public InvalidNameException() {
        super("Invalid name format");
    }
}
