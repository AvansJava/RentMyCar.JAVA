package com.rentmycar.rentmycar.controller;

import com.rentmycar.rentmycar.datalayer.CarList;
import com.rentmycar.rentmycar.service.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/v1.0/cars/")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(path="list/")
    public List<CarList> getCars() {
        List <CarList> cars = carService.getCarList();
        return cars;
    }
}
