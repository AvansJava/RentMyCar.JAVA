package com.rentmycar.rentmycar.repository;

import com.rentmycar.rentmycar.model.CarTimeslotAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarTimeslotAvailabilityRepository extends JpaRepository<CarTimeslotAvailability, Long> {
}
