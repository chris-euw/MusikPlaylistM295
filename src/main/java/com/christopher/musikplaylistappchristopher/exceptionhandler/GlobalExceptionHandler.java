package com.christopher.musikplaylistappchristopher.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Globale Fehlerbehandlung für REST-Controller.
 * Diese Klasse behandelt RuntimeExceptions zentral und gibt standardisierte HTTP-Antworten zurück.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Behandelt alle zur Laufzeit auftretenden RuntimeExceptions.
     * Gibt dem Client eine verständliche Fehlermeldung und den HTTP-Status 404 zurück.
     *
     * @param ex Die aufgetretene Ausnahme
     * @return ResponseEntity mit HTTP-Status 404 und Fehlermeldung im Body
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        // Gibt eine Antwort mit Status 404 (Not Found) und einer Fehlernachricht zurück
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Fehler: " + ex.getMessage());
    }
}
