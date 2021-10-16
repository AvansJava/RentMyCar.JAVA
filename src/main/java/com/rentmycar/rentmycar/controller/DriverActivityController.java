package com.rentmycar.rentmycar.controller;

import com.rentmycar.rentmycar.dto.DriverActivityDto;
import com.rentmycar.rentmycar.model.DriverActivity;
import com.rentmycar.rentmycar.service.DriverActivityService;
import com.rentmycar.rentmycar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return driverActivityService.postDriverActivity(driverActivity);
    }
}
