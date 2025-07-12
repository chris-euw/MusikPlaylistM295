package com.christopher.musikplaylistappchristopher.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        // fängt alle RuntimeExceptions und liefert HTTP 404
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Fehler: " + ex.getMessage());
    }
}