package com.rentmycar.rentmycar.exception;

public class NoLocationsFoundException extends RuntimeException {
    public NoLocationsFoundException(String message) {
        super(message);
    }
}
