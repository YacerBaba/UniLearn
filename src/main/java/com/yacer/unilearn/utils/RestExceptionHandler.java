package com.yacer.unilearn.utils;

import com.yacer.unilearn.auth.pojos.Message;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Message> handleEntityNotFoundException(EntityNotFoundException ex) {
        var message = new Message(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(message.getStatus()).body(message);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Message> handleConstrainViolationException(SQLIntegrityConstraintViolationException ex) {
        var message = new Message(HttpStatus.CONFLICT, ex.getMessage());
        return ResponseEntity.status(message.getStatus()).body(message);
    }
}
