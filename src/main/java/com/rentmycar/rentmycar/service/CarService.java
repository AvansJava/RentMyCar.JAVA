package com.rentmycar.rentmycar.service;

import org.modelmapper.ModelMapper;

import com.rentmycar.rentmycar.datalayer.CarList;
import com.rentmycar.rentmycar.model.Car;
import com.rentmycar.rentmycar.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarList> getCarList() {
        return ((List<Car>) carRepository.findAll())
                .stream()
                .map(obj -> modelMapper.map(obj, CarList.class))
                .collect(Collectors.toList());
    }

}
