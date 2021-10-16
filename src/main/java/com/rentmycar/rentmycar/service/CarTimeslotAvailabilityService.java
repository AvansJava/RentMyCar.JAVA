package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.dto.AvailabilityStatusDto;
import com.rentmycar.rentmycar.dto.CarDto;
import com.rentmycar.rentmycar.dto.CarTimeslotAvailabilityDto;
import com.rentmycar.rentmycar.enums.TimeSlotAvailabilityStatus;
import com.rentmycar.rentmycar.model.*;
import com.rentmycar.rentmycar.repository.CarTimeslotAvailabilityRepository;
import com.rentmycar.rentmycar.repository.TimeslotRepository;
import com.rentmycar.rentmycar.validation.DateValidator;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarTimeslotAvailabilityService {

    private final CarTimeslotAvailabilityRepository carTimeslotAvailabilityRepository;
    private final TimeslotRepository timeslotRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final DateValidator dateValidator;

    public CarTimeslotAvailabilityService(CarTimeslotAvailabilityRepository carTimeslotAvailabilityRepository,
                                          TimeslotRepository timeslotRepository, UserService userService,
                                          ModelMapper modelMapper, DateValidator dateValidator) {
        this.carTimeslotAvailabilityRepository = carTimeslotAvailabilityRepository;
        this.timeslotRepository = timeslotRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.dateValidator = dateValidator;
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

    public CarTimeslotAvailabilityDto updateTimeslotStatus(Long id, AvailabilityStatusDto availabilityStatusDto, User user) {
        CarTimeslotAvailability timeslot = carTimeslotAvailabilityRepository.getById(id);
        User timeslotUser = timeslot.getRentalPlan().getUser();

        if (timeslotUser != user) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User has no permission to edit this timeslot.");
        }

        timeslot.setStatus(availabilityStatusDto.getStatus());
        return modelMapper.map(carTimeslotAvailabilityRepository.save(timeslot), CarTimeslotAvailabilityDto.class);
    }

    public List<CarDto> getAvailabilityBetweenDates(LocalDate startDate, LocalDate endDate) {
        dateValidator.validateDates(startDate, endDate);

        // Retrieve a list of days between the specified rental period
        List<LocalDate> days = startDate.datesUntil(endDate).collect(Collectors.toList());

        // Amount of timeslots that need to be free for the car to be available the whole day
        int count = timeslotRepository.findAll().size();

        /* Retrieve all cars that are available for rent, and instantiate an empty arraylist of cars to be filled
         with cars that are free during the rental period
         */
        List<Car> cars = carTimeslotAvailabilityRepository.getAvailableCars();
        List<Car> availableCars = new ArrayList<>();

        for (Car car: cars) {
            /* Instantiate empty list of booleans, to be filled with days that the car is available
               Loop over the days and if the car is free, add to the list.
             */
            List<Boolean> fullDaysAvailable = new ArrayList<>();
            for (LocalDate day : days) {
                int freeTimeSlots = carTimeslotAvailabilityRepository.findAllFreeByDates(day.atStartOfDay(), day.plusDays(1).atStartOfDay(), car).size();
                if (freeTimeSlots == count) {
                    fullDaysAvailable.add(true);
                }
            }

            // If the amount of days the car is available is the same as the requested rental period, the car is available for rental.
            if (days.size() == fullDaysAvailable.size()) {
                availableCars.add(car);
            }
        }

        return availableCars.stream()
                .map(obj -> modelMapper.map(obj, CarDto.class))
                .collect(Collectors.toList());
    }
}
