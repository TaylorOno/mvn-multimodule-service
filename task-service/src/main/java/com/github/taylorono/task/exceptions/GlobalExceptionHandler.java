package com.github.taylorono.task.exceptions;

import com.github.taylorono.task.exceptions.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Error> handleTaskNotFoundException(TaskNotFoundException taskNotFoundException) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}