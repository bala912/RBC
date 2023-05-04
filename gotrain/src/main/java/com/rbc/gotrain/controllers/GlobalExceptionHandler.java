package com.rbc.gotrain.controllers;

import exceptions.InvalidTimeFormatException;
import exceptions.NoTrainFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 *  Class intercepts exceptions and send appropriate response to client
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NoTrainFoundException.class)
    public final ResponseEntity<String> handleNoTrainFoundException(NoTrainFoundException ex) {
        log.info(ex.getMessage());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTimeFormatException.class)
    public final ResponseEntity<String> handleInvalidTimeFormatException(InvalidTimeFormatException ex) {
        log.info(ex.getMessage());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<String> handleAllExceptions(Exception ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
