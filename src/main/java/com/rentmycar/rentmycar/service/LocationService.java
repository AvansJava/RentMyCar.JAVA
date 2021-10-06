package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.exception.LocationNotFoundException;
import com.rentmycar.rentmycar.exception.LocationUserMismatchException;
import com.rentmycar.rentmycar.exception.NoLocationsFoundException;
import com.rentmycar.rentmycar.exception.UserNotFoundException;
import com.rentmycar.rentmycar.model.Location;
import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.repository.LocationRepository;
import com.rentmycar.rentmycar.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;

    public LocationService(LocationRepository locationRepository, UserRepository userRepository) {
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
    }

    public Location createLocation(Location location, String email) {
        User user = userRepository.findByEmail(email).stream().findFirst().orElseThrow(()
                -> new UserNotFoundException(email));
        location.setUser(user);

        return locationRepository.save(location);
    }

    public Location updateLocation(Long id, Location newLocation, String email) {
        User user = userRepository.findByEmail(email).stream().findFirst().orElseThrow(()
                -> new UserNotFoundException(email));
        Location location = locationRepository.findById(id).stream().findFirst().orElseThrow(()
                -> new LocationNotFoundException(id));

        if (user != location.getUser()) {
            throw new LocationUserMismatchException("Location does not belong to user");
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

    public List<Location> getLocationsByUser(String email) {
        User user = userRepository.findByEmail(email).stream().findFirst().orElseThrow(
                () -> new UserNotFoundException(email));

        return locationRepository.findAllByUser(user);
    }
}
