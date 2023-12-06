package by.mitrakhovich.songservice.controller;

import by.mitrakhovich.songservice.exception.NotFoundSongException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler
    public ResponseEntity handleNotFoundSongException(NotFoundSongException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Song metadata missing validation error");
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleSongServiceException() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal server error has occurred");
    }
}
