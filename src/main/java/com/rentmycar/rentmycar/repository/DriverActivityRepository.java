package com.rentmycar.rentmycar.repository;

import com.rentmycar.rentmycar.model.DriverActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverActivityRepository extends JpaRepository<DriverActivity, Long> {
}
