package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.model.Location;
import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.repository.CarRepository;
import com.rentmycar.rentmycar.repository.LocationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final CarRepository carRepository;

    public LocationService(LocationRepository locationRepository, CarRepository carRepository) {
        this.locationRepository = locationRepository;
        this.carRepository = carRepository;
    }

    public Location createLocation(Location location, User user) {
        location.setUser(user);

        return locationRepository.save(location);
    }

    public Location updateLocation(Long id, Location newLocation, User user) {
        Location location = locationRepository.getById(id);

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
        Location location = locationRepository.getById(id);

        if (location.getUser() != user) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Location does not belong to user");
        }
        return location;
    }

    public ResponseEntity<String> deleteLocationById(Long id, User user) {
        Location location = locationRepository.getById(id);

        if (location.getUser() != user) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Location does not belong to user");
        }

        if (carRepository.findCarsByLocation(location).isPresent()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Location cannot be deleted because it still has cars assigned to it.");
        }
        locationRepository.delete(location);

        return new ResponseEntity<>("Location successfully deleted.", HttpStatus.OK);
    }
}
