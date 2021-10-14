package com.rentmycar.rentmycar.controller;

import com.rentmycar.rentmycar.dto.AvailabilityStatusDto;
import com.rentmycar.rentmycar.dto.CarTimeslotAvailabilityDto;
import com.rentmycar.rentmycar.model.CarTimeslotAvailability;
import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.service.CarTimeslotAvailabilityService;
import com.rentmycar.rentmycar.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1.0/availability/")
public class CarTimeslotAvailabilityController {

    private final CarTimeslotAvailabilityService carTimeslotAvailabilityService;
    private final UserService userService;

    public CarTimeslotAvailabilityController(CarTimeslotAvailabilityService carTimeslotAvailabilityService, UserService userService) {
        this.carTimeslotAvailabilityService = carTimeslotAvailabilityService;
        this.userService = userService;
    }

    @PutMapping(path = "{id}/")
    public CarTimeslotAvailabilityDto updateTimeslotStatus(@PathVariable("id") Long id, @RequestBody AvailabilityStatusDto availabilityStatusDto) {
        User user = userService.getAuthenticatedUser();
        return carTimeslotAvailabilityService.updateTimeslotStatus(id, availabilityStatusDto, user);
    }
}
