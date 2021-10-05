package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.exception.CarAlreadyExistsException;
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

@Service
public class CarService {

    private final CarRepository carRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;

    @Autowired
    public CarService(CarRepository carRepository, LocationRepository locationRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
    }

    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public Car createCar(Car car, String email) {
        String licensePlateNumber = car.getLicensePlateNumber();
        Optional<Car> carOptional = carRepository.findCarByLicensePlateNumber(licensePlateNumber);

        if (carOptional.isPresent()) {
            throw new CarAlreadyExistsException(licensePlateNumber);
        }

        User user = userRepository.findByEmail(email).stream().findFirst().orElse(null);
        car.setUser(user);

        Location location = car.getLocation();
        locationRepository.save(location);

        return carRepository.save(car);
    }
}
