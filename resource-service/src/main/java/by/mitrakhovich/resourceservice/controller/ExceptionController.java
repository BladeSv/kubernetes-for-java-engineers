package by.mitrakhovich.resourceservice.controller;

import by.mitrakhovich.resourceservice.exception.NotFoundEntityException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
@Slf4j
public class ExceptionController {


    @ExceptionHandler
    public ResponseEntity validationExceptionHandler(ConstraintViolationException exception) {
        String exceptionMessage = exception.getConstraintViolations().stream().findFirst().get().getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMessage);
    }

    @ExceptionHandler
    public ResponseEntity notFoundEntityExceptionHandler(NotFoundEntityException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity internalServerExceptionHandler(IOException exception) {
        log.warn("An internal server error has occurred", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal server error has occurred");
    }
}
