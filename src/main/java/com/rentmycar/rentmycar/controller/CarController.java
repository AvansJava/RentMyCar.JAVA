package com.rentmycar.rentmycar.controller;

import com.rentmycar.rentmycar.model.Car;
import com.rentmycar.rentmycar.datalayer.CarList;
import com.rentmycar.rentmycar.service.CarService;

import com.rentmycar.rentmycar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1.0/cars/")
public class CarController {

    private final CarService carService;
    private final UserService userService;

    @Autowired
    public CarController(CarService carService, UserService userService) {
        this.carService = carService;
        this.userService = userService;
    }

    @GetMapping(path="list/")
    public List<CarList> getCars() {
        List <CarList> cars = carService.getCarList();

        return cars;
    }

    @PostMapping
    public Car postCar(@RequestBody Car car) {
        String email = userService.getAuthenticatedUserEmail();

        return carService.createCar(car, email);
    }
}
