package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.model.Location;
import com.rentmycar.rentmycar.repository.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }
}
