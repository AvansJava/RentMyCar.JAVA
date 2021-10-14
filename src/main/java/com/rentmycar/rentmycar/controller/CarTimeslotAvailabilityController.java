package com.rentmycar.rentmycar.controller;

import com.rentmycar.rentmycar.service.CarTimeslotAvailabilityService;
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
