package com.rentmycar.rentmycar.rent.service;

import com.rentmycar.rentmycar.dto.CarDto;
import com.rentmycar.rentmycar.model.Car;
import com.rentmycar.rentmycar.rent.repository.RentCarRepository;
import com.rentmycar.rentmycar.service.CarTimeslotAvailabilityService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentCarFilter {
    private final RentCarRepository rentCarRepository;
    private final CarTimeslotAvailabilityService carTimeslotAvailabilityService;
    private final ModelMapper modelMapper;

    public RentCarFilter(CarTimeslotAvailabilityService carTimeslotAvailabilityService, RentCarRepository rentCarRepository, ModelMapper modelMapper) {
        this.carTimeslotAvailabilityService = carTimeslotAvailabilityService;
        this.rentCarRepository = rentCarRepository;
        this.modelMapper = modelMapper;
    }

    public List<CarDto> getFilteredCars(LocalDate startDate, LocalDate endDate, String brand) {
        List<CarDto> availableCars = rentCarRepository.findAll()
                .stream()
                .map(obj -> modelMapper.map(obj, CarDto.class))
                .collect(Collectors.toList());

        if (startDate != null && endDate != null) {
            availableCars = carTimeslotAvailabilityService.getAvailabilityBetweenDates(startDate, endDate);
        }

        if (brand != null) {
            System.out.println(brand);
            availableCars.removeIf(car -> !car.getBrand().contains(brand));
        }
        return availableCars.stream()
                .map(obj -> modelMapper.map(obj, CarDto.class))
                .collect(Collectors.toList());
    }
}
