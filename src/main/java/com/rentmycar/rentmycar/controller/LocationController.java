package com.rentmycar.rentmycar.controller;

import com.rentmycar.rentmycar.dto.LocationDto;
import com.rentmycar.rentmycar.model.Location;
import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.service.LocationService;
import com.rentmycar.rentmycar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public LocationDto postLocation(@RequestBody Location location) {
        User user = userService.getAuthenticatedUser();
        return locationService.createLocation(location, user);
    }

    @PutMapping(path = "{id}/")
    public LocationDto putLocation(@PathVariable("id") Long id, @RequestBody Location newLocation) {
        User user = userService.getAuthenticatedUser();
        return locationService.updateLocation(id, newLocation, user);
    }

    @GetMapping
    public List<LocationDto> getLocationsByUser() {
        User user = userService.getAuthenticatedUser();
        return locationService.getLocationsByUser(user);
    }

    @GetMapping(path = "{id}/")
    public LocationDto getLocation(@PathVariable("id") Long id) {
        User user = userService.getAuthenticatedUser();
        return locationService.getLocationById(id, user);
    }

    @DeleteMapping(path = "{id}/")
    public ResponseEntity<String> deleteLocation(@PathVariable("id") Long id) {
        User user = userService.getAuthenticatedUser();
        return locationService.deleteLocationById(id, user);
    }
}
