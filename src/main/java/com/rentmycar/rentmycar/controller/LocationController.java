package com.rentmycar.rentmycar.controller;

import com.rentmycar.rentmycar.model.Location;
import com.rentmycar.rentmycar.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1.0/location/")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public Location postLocation(@RequestBody Location location) {
        return locationService.createLocation(location);
    }
}
