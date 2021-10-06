package com.rentmycar.rentmycar.exception;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String email) {
        super("E-mail address " + email + " is invalid.");
    }
}
