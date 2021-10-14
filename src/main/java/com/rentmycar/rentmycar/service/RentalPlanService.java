package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.dto.RentalPlanDto;
import com.rentmycar.rentmycar.model.Car;
import com.rentmycar.rentmycar.model.RentalPlan;
import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.repository.CarRepository;
import com.rentmycar.rentmycar.repository.RentalPlanRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalPlanService {

    private final RentalPlanRepository rentalPlanRepository;
    private final ModelMapper modelMapper;
    private final CarTimeslotAvailabilityService carTimeslotAvailabilityService;
    private final CarRepository carRepository;

    @Autowired
    public RentalPlanService(RentalPlanRepository rentalPlanRepository, ModelMapper modelMapper, CarTimeslotAvailabilityService carTimeslotAvailabilityService, CarRepository carRepository) {
        this.rentalPlanRepository = rentalPlanRepository;
        this.modelMapper = modelMapper;
        this.carTimeslotAvailabilityService = carTimeslotAvailabilityService;
        this.carRepository = carRepository;
    }

    public List<RentalPlanDto> getRentalPlansByUser(User user) {
        return rentalPlanRepository.findAllByUser(user)
                .stream()
                .map(obj -> modelMapper.map(obj, RentalPlanDto.class))
                .collect(Collectors.toList());
    }

    public RentalPlanDto getRentalPlan(Long id, User user) {
        RentalPlan rentalPlan = rentalPlanRepository.getById(id);

        if (rentalPlan.getUser() != user) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Rental plan does not belong to user.");
        }

        return modelMapper.map(rentalPlan, RentalPlanDto.class);
    }

    public ResponseEntity<RentalPlanDto> createRentalPlan(RentalPlan rentalPlan, User user) {
        Optional<Car> carOptional = carRepository.findById(rentalPlan.getCar().getId());

        if (carOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car does not exist.");
        }

        Car car = carOptional.get();
        Optional<RentalPlan> rentalPlanOptional = rentalPlanRepository.findAllByCar(car);

        if (rentalPlanOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This car already has a rental plan.");
        }

        if (car.getUser() != user) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "User has no permissions to create a rental plan for this car.");
        }

        List<LocalDate> availableDates = getRentalPlanAvailableDates(rentalPlan.getAvailableFrom(), rentalPlan.getAvailableUntil());

        for (LocalDate day: availableDates) {
            carTimeslotAvailabilityService.postAvailability(day, car);
        }
        rentalPlan.setUser(user);
        rentalPlanRepository.save(rentalPlan);
        return new ResponseEntity<>(modelMapper.map(rentalPlan, RentalPlanDto.class), HttpStatus.CREATED);
    }

    public List<LocalDate> getRentalPlanAvailableDates(LocalDate startDate, LocalDate endDate) {
        return startDate.datesUntil(endDate).collect(Collectors.toList());
    }
}
