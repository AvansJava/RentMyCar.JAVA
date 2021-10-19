package com.rentmycar.rentmycar.repository;

import com.rentmycar.rentmycar.dto.DriverActivityDto;
import com.rentmycar.rentmycar.model.Car;
import com.rentmycar.rentmycar.model.DriverActivity;
import com.rentmycar.rentmycar.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Driver;
import java.util.List;
import java.util.Optional;

@Repository
public interface DriverActivityRepository extends JpaRepository<DriverActivity, Long> {
    List<DriverActivity> findAllByCar(Car car);

    Optional<DriverActivity> findByReservation(Reservation reservation);
}
