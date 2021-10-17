package com.rentmycar.rentmycar.rent.repository;

import org.springframework.data.jpa.repository.Query;
import com.rentmycar.rentmycar.model.Car;
import com.rentmycar.rentmycar.model.CarTimeslotAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RentCarRepository extends JpaRepository<Car, Long> {

}
