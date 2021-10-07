package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.exception.CarAlreadyExistsException;
import com.rentmycar.rentmycar.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;

import com.rentmycar.rentmycar.datalayer.CarList;
import com.rentmycar.rentmycar.model.Car;
import com.rentmycar.rentmycar.model.Location;
import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.repository.CarRepository;
import com.rentmycar.rentmycar.repository.LocationRepository;
import com.rentmycar.rentmycar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CarService(CarRepository carRepository, LocationRepository locationRepository, 
     UserRepository userRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
    }

    public List<CarList> getCarList() {
        return ((List<Car>) carRepository.findAll())
                .stream()
                .map(obj -> modelMapper.map(obj, CarList.class))
                .collect(Collectors.toList());
    }

    public Car createCar(Car car, User user) {
        String licensePlateNumber = car.getLicensePlateNumber();
        Optional<Car> carOptional = carRepository.findCarByLicensePlateNumber(licensePlateNumber);

        if (carOptional.isPresent()) {
            throw new CarAlreadyExistsException(licensePlateNumber);
        }

        car.setUser(user);

        Location location = car.getLocation();
        locationRepository.save(location);

        return carRepository.save(car);
    }
}
