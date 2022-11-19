package com.example.vhs.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class EntityDoesNotExistExceptionHandler {

    Logger log = LoggerFactory.getLogger(EntityDoesNotExistExceptionHandler.class);

    @ExceptionHandler(value={EntityDoesNotExistException.class})
    public ResponseEntity<Object> handleEntityDoesNotExistException(EntityDoesNotExistException e){
        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        log.error(e.getMessage());

        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
}
