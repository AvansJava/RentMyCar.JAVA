package com.rentmycar.rentmycar.controller;

import com.rentmycar.rentmycar.model.Car;
import com.rentmycar.rentmycar.model.CarTimeslotAvailability;
import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.service.CarTimeslotAvailabilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1.0/availability/")
public class CarTimeslotAvailabilityController {

    private final CarTimeslotAvailabilityService carTimeslotAvailabilityService;

    public CarTimeslotAvailabilityController(CarTimeslotAvailabilityService carTimeslotAvailabilityService) {
        this.carTimeslotAvailabilityService = carTimeslotAvailabilityService;
    }
}
