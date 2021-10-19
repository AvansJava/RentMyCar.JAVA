package com.rentmycar.rentmycar.controller;

import com.rentmycar.rentmycar.dto.DriverActivityDto;
import com.rentmycar.rentmycar.model.DriverActivity;
import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.service.DriverActivityService;
import com.rentmycar.rentmycar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1.0/activity/")
public class DriverActivityController {

    private final UserService userService;
    private final DriverActivityService driverActivityService;

    @Autowired
    public DriverActivityController(UserService userService, DriverActivityService driverActivityService) {
        this.userService = userService;
        this.driverActivityService = driverActivityService;
    }

    @PostMapping
    public ResponseEntity<DriverActivityDto> postDriverActivity(@RequestBody DriverActivity driverActivity) {
        User user = userService.getAuthenticatedUser();
        return driverActivityService.postDriverActivity(driverActivity, user);
    }

    @GetMapping(path = "car/{carId}/")
    public List<DriverActivityDto> getDriverActivityByCar(@PathVariable("carId") Long carId) {
        User user = userService.getAuthenticatedUser();
        return driverActivityService.getActivityByCar(user, carId);
    }

    @GetMapping(path = "reservation/{reservationNumber}/")
    public DriverActivityDto getDriverActivityByReservation(@PathVariable("reservationNumber") String reservationNumber) {
        User user = userService.getAuthenticatedUser();
        return driverActivityService.getActivityByReservation(user, reservationNumber);
    }

    @PutMapping(path = "{id}/")
    public DriverActivityDto syncDriverActivity(@PathVariable("id") Long id, @RequestBody DriverActivity log) {
        User user = userService.getAuthenticatedUser();
        return driverActivityService.syncDriverActivity(id, user, log);
    }
}
