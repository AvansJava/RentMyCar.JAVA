package com.rentmycar.rentmycar.exception;

public class CarAlreadyExistsException extends RuntimeException {
    public CarAlreadyExistsException(String licensePlateNumber) {
        super("A car with license plate number " + licensePlateNumber + " already exists.");
    }
}
