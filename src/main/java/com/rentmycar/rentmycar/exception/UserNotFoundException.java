package com.rentmycar.rentmycar.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
        super("User with e-mail address " + email + " does not exist.");
    }
}