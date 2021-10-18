package com.rentmycar.rentmycar.repository;

import com.rentmycar.rentmycar.model.Car;
import com.rentmycar.rentmycar.model.CarTimeslotAvailability;
import com.rentmycar.rentmycar.model.Product;
import com.rentmycar.rentmycar.model.RentalPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CarTimeslotAvailabilityRepository extends JpaRepository<CarTimeslotAvailability, Long> {

    @Query("SELECT c FROM CarTimeslotAvailability c WHERE c.rentalPlan = ?1 AND c.product IS NOT NULL")
    List<CarTimeslotAvailability> findAllRentedByRentalPlan(RentalPlan rentalPlan);

    @Transactional
    @Modifying
    @Query("DELETE FROM CarTimeslotAvailability WHERE rentalPlan = ?1")
    void deleteAllByRentalPlan(RentalPlan rentalPlan);

    @Query("SELECT c " +
            "FROM CarTimeslotAvailability c " +
            "WHERE c.startAt BETWEEN ?1 AND ?2 " +
            "AND c.status = 'OPEN' " +
            "AND c.car = ?3 " +
            "AND c.product IS NULL ")
    List<CarTimeslotAvailability> findAllFreeByDates(LocalDateTime startOfDay, LocalDateTime endOfDay, Car car);

    @Query("SELECT DISTINCT c.car FROM CarTimeslotAvailability c WHERE c.status = 'OPEN' AND c.product IS NULL")
    List<Car> getAvailableCars();

    @Transactional
    @Modifying
    @Query("UPDATE CarTimeslotAvailability " +
            "SET product = ?1 WHERE id = ?2")
    void updateWithProduct(Product createdProduct, Long timeslotId);
}
