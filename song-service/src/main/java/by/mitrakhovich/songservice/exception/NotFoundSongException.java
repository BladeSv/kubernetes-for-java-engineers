package by.mitrakhovich.songservice.exception;

public class NotFoundSongException extends RuntimeException {
    public NotFoundSongException(String message) {
        super(message);
    }
}
