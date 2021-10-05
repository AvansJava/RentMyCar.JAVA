package com.rentmycar.rentmycar.repository;

import com.rentmycar.rentmycar.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findCarBylicensePlateNumber(String licensePlateNumber);
}
