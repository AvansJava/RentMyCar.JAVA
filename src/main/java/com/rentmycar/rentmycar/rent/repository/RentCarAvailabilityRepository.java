package com.rentmycar.rentmycar.rent.repository;

import com.rentmycar.rentmycar.model.Car;
import com.rentmycar.rentmycar.model.CarTimeslotAvailability;
import com.rentmycar.rentmycar.rent.dto.RentCarAvailabilityDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentCarAvailabilityRepository extends JpaRepository<CarTimeslotAvailability, Long> {

    @Query("SELECT c " +
            "FROM CarTimeslotAvailability c " +
            "WHERE c.car = ?1 ")
    List<CarTimeslotAvailability> getCarAvailability(Car car);
}
