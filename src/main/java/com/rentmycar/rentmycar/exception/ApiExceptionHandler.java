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
}
