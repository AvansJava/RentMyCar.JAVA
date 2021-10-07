package com.rentmycar.rentmycar.controller;

import com.rentmycar.rentmycar.model.Location;
import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.service.LocationService;
import com.rentmycar.rentmycar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="api/v1.0/location/")
public class LocationController {

    private final LocationService locationService;
    private final UserService userService;

    @Autowired
    public LocationController(LocationService locationService, UserService userService) {
        this.locationService = locationService;
        this.userService = userService;
    }

    //TODO fix validation, @valid doesn't seem to work now
    @PostMapping
    public Location postLocation(@Valid @RequestBody Location location) {
        User user = userService.getAuthenticatedUser();
        return locationService.createLocation(location, user);
    }

    @PutMapping(path = "{id}/")
    public Location putLocation(@Valid @PathVariable("id") Long id, @RequestBody Location newLocation) {
        User user = userService.getAuthenticatedUser();
        return locationService.updateLocation(id, newLocation, user);
    }

    @GetMapping
    public List<Location> getLocationsByUser() {
        User user = userService.getAuthenticatedUser();
        return locationService.getLocationsByUser(user);
    }

    @GetMapping(path = "{id}/")
    public Location getLocation(@PathVariable("id") Long id) {
        User user = userService.getAuthenticatedUser();
        return locationService.getLocationById(id, user);
    }
}
