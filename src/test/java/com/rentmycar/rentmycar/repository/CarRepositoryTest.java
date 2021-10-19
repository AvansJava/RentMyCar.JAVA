package com.rentmycar.rentmycar.repository;

import com.rentmycar.rentmycar.model.Car;
import com.rentmycar.rentmycar.model.Location;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class CarRepositoryTest {
    @Test
    void findCarByLicensePlateNumber() {
        CarRepository carRepository = mock(CarRepository.class);
        Car car = mock(Car.class);
        when(car.getLicensePlateNumber()).thenReturn("4-YY-685");

        Optional<Car> findCar = carRepository.findCarByLicensePlateNumber(car.getLicensePlateNumber());
        assertNotNull(findCar);
    }

    @Test
    void findCarsByLocation() {
        CarRepository carRepository = mock(CarRepository.class);
        Location location = mock(Location.class);
        when(location.getId()).thenReturn(1L);

        Car car = mock(Car.class);
        car.setLocation(location);
        carRepository.save(car);

        Optional<Car> findCar = carRepository.findCarsByLocation(location);
        assertNotNull(findCar);
    }
}