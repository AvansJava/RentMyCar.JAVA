package com.rentmycar.rentmycar.repository;

import com.rentmycar.rentmycar.model.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TimeslotRepository extends JpaRepository<Timeslot, Long> {
}
