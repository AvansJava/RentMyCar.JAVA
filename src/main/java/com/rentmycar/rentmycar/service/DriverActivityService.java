package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.dto.DriverActivityDto;
import com.rentmycar.rentmycar.model.Car;
import com.rentmycar.rentmycar.model.DriverActivity;
import com.rentmycar.rentmycar.model.Reservation;
import com.rentmycar.rentmycar.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DriverActivityService {

    private final CarRepository carRepository;

    public ResponseEntity<DriverActivityDto> postDriverActivity(DriverActivity driverActivity) {
        Optional<Car> carOptional = carRepository.findById(driverActivity.getCar().getId());
//        Optional<Reservation> =

        if (carOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car does not exist.");
        }
        Car car = carOptional.get();

        return null;
//        TODO: reservation calls

    }
}
