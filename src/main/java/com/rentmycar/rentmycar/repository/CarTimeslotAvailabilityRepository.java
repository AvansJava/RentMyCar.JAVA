package com.rentmycar.rentmycar.repository;

import com.rentmycar.rentmycar.model.CarTimeslotAvailability;
import com.rentmycar.rentmycar.model.RentalPlan;
import org.hibernate.annotations.OnDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CarTimeslotAvailabilityRepository extends JpaRepository<CarTimeslotAvailability, Long> {

    @Query("SELECT c FROM CarTimeslotAvailability c WHERE c.rentalPlan = ?1 AND c.product IS NOT NULL")
    List<CarTimeslotAvailability> findAllRentedByRentalPlan(RentalPlan rentalPlan);

    @Transactional
    @Modifying
    @Query("DELETE FROM CarTimeslotAvailability WHERE rentalPlan = ?1")
    void deleteAllByRentalPlan(RentalPlan rentalPlan);
}
