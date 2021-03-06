package com.rentmycar.rentmycar.repository;

import com.rentmycar.rentmycar.model.Car;
import com.rentmycar.rentmycar.model.Location;
import com.rentmycar.rentmycar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findCarByLicensePlateNumber(String licensePlateNumber);

    List<Car> findAllByUser(User user);

    Optional<Car> findCarsByLocation(Location location);
}