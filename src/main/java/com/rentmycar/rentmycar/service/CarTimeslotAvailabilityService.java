package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.enums.TimeSlotAvailabilityStatus;
import com.rentmycar.rentmycar.model.*;
import com.rentmycar.rentmycar.repository.CarTimeslotAvailabilityRepository;
import com.rentmycar.rentmycar.repository.TimeslotRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CarTimeslotAvailabilityService {

    private final CarTimeslotAvailabilityRepository carTimeslotAvailabilityRepository;
    private final TimeslotRepository timeslotRepository;
    private final UserService userService;

    public CarTimeslotAvailabilityService(CarTimeslotAvailabilityRepository carTimeslotAvailabilityRepository,
                                          TimeslotRepository timeslotRepository, UserService userService) {
        this.carTimeslotAvailabilityRepository = carTimeslotAvailabilityRepository;
        this.timeslotRepository = timeslotRepository;
        this.userService = userService;
    }

    public void postAvailability(LocalDate day, Car car, RentalPlan rentalPlan) {
        User user = userService.getAuthenticatedUser();

        if (car.getUser() != user) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "User has no permissions to create timeslots for this car.");
        }

        // Get all available default timeslots
        List<Timeslot> timeslots = timeslotRepository.findAll();

        // Generate availability moments for each available timeslot
        for (Timeslot timeslot: timeslots) {
            LocalDateTime startAt = LocalDateTime.of(day, timeslot.getStartAt());
            LocalDateTime endAt = LocalDateTime.of(day, timeslot.getEndAt());

            CarTimeslotAvailability carTimeslotAvailability = new CarTimeslotAvailability();
            carTimeslotAvailability.setStartAt(startAt);
            carTimeslotAvailability.setEndAt(endAt);
            carTimeslotAvailability.setStatus(TimeSlotAvailabilityStatus.OPEN);
            carTimeslotAvailability.setCar(car);
            carTimeslotAvailability.setTimeslot(timeslot);
            carTimeslotAvailability.setRentalPlan(rentalPlan);

            carTimeslotAvailabilityRepository.save(carTimeslotAvailability);
        }
    }

    public List<CarTimeslotAvailability> getRentedTimeslotsRentalPlan(RentalPlan rentalPlan) {
        return carTimeslotAvailabilityRepository.findAllRentedByRentalPlan(rentalPlan);
    }

    public void deleteTimeslotsRentalPlan(RentalPlan rentalPlan) {
        carTimeslotAvailabilityRepository.deleteAllByRentalPlan(rentalPlan);
    }
}
