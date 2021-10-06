package com.rentmycar.rentmycar.exception;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(Long id) {
        super("Location with id " + id + " does not exist.");
    }
}