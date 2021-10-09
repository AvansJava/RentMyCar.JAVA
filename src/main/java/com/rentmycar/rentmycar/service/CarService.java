package com.rentmycar.rentmycar.service;

import org.modelmapper.ModelMapper;

import com.rentmycar.rentmycar.dto.CarList;
import com.rentmycar.rentmycar.model.Car;
import com.rentmycar.rentmycar.model.Location;
import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final LocationService locationService;
    private ModelMapper modelMapper;

    @Autowired
    public CarService(CarRepository carRepository, LocationService locationService, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.locationService = locationService;
    }

    public List<CarList> getCarList() {
        return ((List<Car>) carRepository.findAll())
                .stream()
                .map(obj -> modelMapper.map(obj, CarList.class))
                .collect(Collectors.toList());
    }

    public ResponseEntity<Car> createCar(Car car, User user) {
        String licensePlateNumber = car.getLicensePlateNumber();
        Optional<Car> carOptional = carRepository.findCarByLicensePlateNumber(licensePlateNumber);

        if (carOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A car with license plate number " + licensePlateNumber + "already exists.");
        }
        car.setUser(user);

        Location location = car.getLocation();
        if (location != null) {
            locationService.createLocation(location, user);
        }
        Car createdCar = carRepository.save(car);
        return new ResponseEntity<>(createdCar, HttpStatus.CREATED);
    }

    public Car updateCar(Long id, Car newCar, User user) {
        Car car = carRepository.getById(id);

        if (user != car.getUser()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Car does not belong to user");
        }
        car.setBrand(newCar.getBrand());
        car.setBrandType(newCar.getBrandType());
        car.setModel(newCar.getModel());
        car.setLicensePlateNumber(newCar.getLicensePlateNumber());
        car.setConsumption(newCar.getConsumption());
        car.setCarType(newCar.getCarType());

        return carRepository.save(car);
    }

    public List<Car> getCarsByUser(User user) {
        return carRepository.findAllByUser(user);
    }

    public Car getCarByUser(Long id, User user) {
        Car car = carRepository.getById(id);

        if (car.getUser() != user) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Car does not belong to user.");
        }

        return car;
    }

    public ResponseEntity<String> deleteCar(Long id, User user) {
        Car car = carRepository.getById(id);

        if (car.getUser() != user) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Car does not belong to user.");
        }
        carRepository.delete(car);

        return new ResponseEntity<>("Car successfully deleted.", HttpStatus.OK);
    }
}
