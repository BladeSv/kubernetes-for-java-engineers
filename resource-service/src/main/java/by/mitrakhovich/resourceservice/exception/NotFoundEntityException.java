package by.mitrakhovich.resourceservice.exception;

public class NotFoundEntityException extends RuntimeException {
    public NotFoundEntityException(String message) {
        super(message);
    }
}
