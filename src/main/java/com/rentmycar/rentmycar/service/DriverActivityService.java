package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.dto.DriverActivityDto;
import com.rentmycar.rentmycar.model.Car;
import com.rentmycar.rentmycar.model.DriverActivity;
import com.rentmycar.rentmycar.model.Reservation;
import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.repository.CarRepository;
import com.rentmycar.rentmycar.repository.DriverActivityRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Driver;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DriverActivityService {

    private final ReservationService reservationService;
    private final CarRepository carRepository;
    private final DriverActivityRepository driverActivityRepository;
    private final ModelMapper modelMapper;
    private final CarService carService;

    public ResponseEntity<DriverActivityDto> postDriverActivity(DriverActivity driverActivity, User user) {
        // validate car/reservation
        reservationService.findReservationByUser(driverActivity.getReservation().getReservationNumber(), user);
        if (carRepository.findById(driverActivity.getCar().getId()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found.");
        }

        driverActivity.setLastSyncedAt(LocalDateTime.now());
        return new ResponseEntity<>(modelMapper.map(driverActivityRepository.save(driverActivity),DriverActivityDto.class), HttpStatus.CREATED);
    }

    // For car owner to retrieve all activity on car
    public List<DriverActivityDto> getActivityByCar(User user, Long carId) {
        Car car = carService.findCarByUser(carId, user);
        List<DriverActivity> driverActivityList = driverActivityRepository.findAllByCar(car);

        return driverActivityList.stream()
                .map(obj -> modelMapper.map(obj, DriverActivityDto.class))
                .collect(Collectors.toList());
    }

    // For car renter to see activity on rented car/reservation
    public DriverActivityDto getActivityByReservation(User user, String reservationNumber) {
        Reservation reservation = reservationService.findReservationByUser(reservationNumber, user);
        Optional<DriverActivity> driverActivityOptional = driverActivityRepository.findByReservation(reservation);

        if (driverActivityOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find driver activity log.");
        }
        return modelMapper.map(driverActivityOptional.get(), DriverActivityDto.class);
    }


    public DriverActivityDto syncDriverActivity(Long id, User user, DriverActivity log) {
        Optional<DriverActivity> driverActivityOptional = driverActivityRepository.findById(id);

        if (driverActivityOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find driver activity log.");
        }
        DriverActivity driverActivity = driverActivityOptional.get();

        if (driverActivity.getReservation().getUser() != user) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Driver activity log does not belong to user.");
        }

        driverActivity.setDistanceDriven(log.getDistanceDriven());
        driverActivity.setTopSpeed(log.getTopSpeed());
        driverActivity.setAverageSpeed(log.getAverageSpeed());
        driverActivity.setAcceleration(log.getAcceleration());
        driverActivity.setLastSyncedAt(LocalDateTime.now());

        return modelMapper.map(driverActivityRepository.save(driverActivity),DriverActivityDto.class);
    }
}
