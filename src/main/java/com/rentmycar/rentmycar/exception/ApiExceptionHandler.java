package com.rentmycar.rentmycar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CarAlreadyExistsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleCarAlreadyExistsException(CarAlreadyExistsException e) {
        return e.getMessage();
    }

    @ExceptionHandler(InvalidEmailException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidEmailException(InvalidEmailException e) {
        return e.getMessage();
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotFoundException(UserNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleLocationNotFoundException(LocationNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(LocationUserMismatchException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleLocationUserMismatchException(LocationUserMismatchException e) {
        return e.getMessage();
    }
}
