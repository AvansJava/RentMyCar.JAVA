package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.model.Location;
import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.repository.LocationRepository;
import com.rentmycar.rentmycar.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location createLocation(Location location, User user) {
        location.setUser(user);

        return locationRepository.save(location);
    }

    public Location updateLocation(Long id, Location newLocation, User user) {
        Location location = locationRepository.findById(id).stream().findFirst().orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Location could not be found."));

        if (user != location.getUser()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Location does not belong to user");
        }

        location.setStreet(newLocation.getStreet());
        location.setHouseNumber(newLocation.getHouseNumber());
        location.setPostalCode(newLocation.getPostalCode());
        location.setCity(newLocation.getCity());
        location.setCountry(newLocation.getCountry());
        location.setLatitude(newLocation.getLatitude());
        location.setLongitude(newLocation.getLongitude());

        return locationRepository.save(location);
    }

    public List<Location> getLocationsByUser(User user) {
        return locationRepository.findAllByUser(user);
    }

    public Location getLocationById(Long id, User user) {
        Location location = locationRepository.findById(id).stream().findFirst().orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Location could not be found."));

        if (location.getUser() != user) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Location does not belong to user");
        }

        return location;
    }
}
