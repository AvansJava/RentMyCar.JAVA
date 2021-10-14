package com.rentmycar.rentmycar.controller;

import com.rentmycar.rentmycar.dto.CarDto;
import com.rentmycar.rentmycar.model.Car;
import com.rentmycar.rentmycar.dto.CarList;
import com.rentmycar.rentmycar.model.Car;
import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.service.CarService;

import com.rentmycar.rentmycar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public List<CarDto> getCarsList() {
        User user = userService.getAuthenticatedUser();
        return carService.getCarsList();
    }

    @PostMapping
    public ResponseEntity<CarDto> postCar(@RequestBody Car car) {
        User user = userService.getAuthenticatedUser();

       return carService.createCar(car, user);
   }

    @PutMapping(path = "{id}/")
    public CarDto putCar(@PathVariable("id") Long id, @RequestBody Car newCar) {
        User user = userService.getAuthenticatedUser();
        return carService.updateCar(id, newCar, user);
    }

    @GetMapping
    public List<CarDto> getCarsByUser() {
        User user = userService.getAuthenticatedUser();
        return carService.getCarsByUser(user);
    }

    @GetMapping(path = "{id}/")
    public CarDto getCarByUser(@PathVariable("id") Long id) {
        User user = userService.getAuthenticatedUser();
        return carService.getCarByUser(id,user);
    }

    @DeleteMapping(path = "{id}/")
    public ResponseEntity<String> deleteCar(@PathVariable("id") Long id) {
        User user = userService.getAuthenticatedUser();
        return carService.deleteCar(id, user);
    }
}
