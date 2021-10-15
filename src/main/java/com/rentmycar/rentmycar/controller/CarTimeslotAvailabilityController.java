package com.rentmycar.rentmycar.controller;

import com.rentmycar.rentmycar.dto.AvailabilityStatusDto;
import com.rentmycar.rentmycar.dto.CarDto;
import com.rentmycar.rentmycar.dto.CarTimeslotAvailabilityDto;
import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.service.CarTimeslotAvailabilityService;
import com.rentmycar.rentmycar.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    @GetMapping
    public List<CarDto> getAvailability(@RequestParam String startDate,
                                        @RequestParam String endDate) {
        return carTimeslotAvailabilityService.getAvailabilityBetweenDates(LocalDate.parse(startDate), LocalDate.parse(endDate));
    }
}
