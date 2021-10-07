package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.exception.*;
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

    public Car createCar(Car car, User user) {
        String licensePlateNumber = car.getLicensePlateNumber();
        Optional<Car> carOptional = carRepository.findCarByLicensePlateNumber(licensePlateNumber);

        if (carOptional.isPresent()) {
            throw new CarAlreadyExistsException(licensePlateNumber);
        }
        car.setUser(user);

        Location location = car.getLocation();
        if (location != null) {
            locationService.createLocation(location, user);
        }

        return carRepository.save(car);
    }

    public Car updateCar(Long id, Car newCar, User user) {
        Car car = carRepository.findById(id).stream().findFirst().orElseThrow(()
                -> new CarNotFoundException("Car could not be found."));

        if (user != car.getUser()) {
            throw new CarUserMismatchException("Car does not belong to user");
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
        Car car = carRepository.findById(id).stream().findFirst().orElseThrow(()
                -> new CarNotFoundException("Car could not be found."));

        if (car.getUser() != user) {
            throw new CarUserMismatchException("Car does not belong to current user.");
        }

        return car;
    }
}
