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

    public RentalPlanDto getRentalPlan(Long id) {
        RentalPlan rentalPlan = rentalPlanRepository.getById(id);

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
        rentalPlan.setUser(user);

        // Get all days on which the car can be rented out
        List<LocalDate> availableDates = getRentalPlanAvailableDates(rentalPlan.getAvailableFrom(), rentalPlan.getAvailableUntil());

        // For each day generate timeslots
        for (LocalDate day: availableDates) {
            carTimeslotAvailabilityService.postAvailability(day, car, rentalPlan);
        }

        rentalPlanRepository.save(rentalPlan);
        return new ResponseEntity<>(modelMapper.map(rentalPlan, RentalPlanDto.class), HttpStatus.CREATED);
    }

    public List<LocalDate> getRentalPlanAvailableDates(LocalDate startDate, LocalDate endDate) {
        return startDate.datesUntil(endDate).collect(Collectors.toList());
    }

    public ResponseEntity<String> deleteRentalPlan(Long id, User user) {
        Optional<RentalPlan> rentalPlanOptional = rentalPlanRepository.findById(id);

        if (rentalPlanOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rental plan does not exist.");
        }

        RentalPlan rentalPlan = rentalPlanOptional.get();

        if (rentalPlan.getUser() != user) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Rental plan does not belong to user.");
        }

        // If car has already been rented out rental plan can't be deleted
        if (carTimeslotAvailabilityService.getRentedTimeslotsRentalPlan(rentalPlan).size() > 0) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Cannot delete rental plan because timeslots have been booked");
        }

        // Also delete all timeslot availability moments
        carTimeslotAvailabilityService.deleteTimeslotsRentalPlan(rentalPlan);

        rentalPlanRepository.delete(rentalPlan);
        return new ResponseEntity<>("Rental plan successfully deleted.", HttpStatus.OK);
    }
}