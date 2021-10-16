package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.dto.LocationDto;
import com.rentmycar.rentmycar.model.Location;
import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.repository.CarRepository;
import com.rentmycar.rentmycar.repository.LocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    public LocationService(LocationRepository locationRepository, CarRepository carRepository, ModelMapper modelMapper) {
        this.locationRepository = locationRepository;
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    public LocationDto createLocation(Location location, User user) {
        location.setUser(user);

        return modelMapper.map(locationRepository.save(location),LocationDto.class);
    }

    public LocationDto updateLocation(Long id, Location newLocation, User user) {
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

        return modelMapper.map(locationRepository.save(location),LocationDto.class);
    }

    public List<LocationDto> getLocationsByUser(User user) {
        return locationRepository.findAllByUser(user)
                .stream()
                .map(obj -> modelMapper.map(obj, LocationDto.class))
                .collect(Collectors.toList());
    }

    public LocationDto getLocationById(Long id, User user) {
        Location location = locationRepository.getById(id);

        if (location.getUser() != user) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Location does not belong to user");
        }
        return modelMapper.map(location, LocationDto.class);
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
