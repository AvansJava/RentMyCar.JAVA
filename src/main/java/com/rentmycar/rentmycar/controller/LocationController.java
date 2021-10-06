package com.rentmycar.rentmycar.controller;

import com.rentmycar.rentmycar.model.Location;
import com.rentmycar.rentmycar.service.LocationService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path="api/v1.0/location/")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public Location postLocation(@Valid @RequestBody Location location) {
        String email = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal().toString();

        return locationService.createLocation(location, email);
    }

    @PutMapping(path = "{id}/")
    public Location putLocation(@PathVariable("id") Long id, @NotNull @RequestBody Location newLocation) {
        String email = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal().toString();

        return locationService.updateLocation(id, newLocation, email);
    }
}
