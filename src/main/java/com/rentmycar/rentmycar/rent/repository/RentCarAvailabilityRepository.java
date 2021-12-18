package com.rentmycar.rentmycar.rent.repository;

import com.rentmycar.rentmycar.model.Car;
import com.rentmycar.rentmycar.model.CarTimeslotAvailability;
import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.rent.dto.RentCarAvailabilityDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentCarAvailabilityRepository extends JpaRepository<CarTimeslotAvailability, Long> {

    @Query("SELECT c " +
            "FROM CarTimeslotAvailability c " +
            "WHERE c.car = ?1 " +
            "ORDER BY c.startAt ASC ")
    Page<CarTimeslotAvailability> getCarAvailability(Car car, Pageable page);

//    Page<CarTimeslotAvailability> findAllByCar(Car car, Pageable page);
}
