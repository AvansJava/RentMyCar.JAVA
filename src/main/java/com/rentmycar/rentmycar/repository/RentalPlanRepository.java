package com.rentmycar.rentmycar.repository;

import com.rentmycar.rentmycar.model.RentalPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalPlanRepository extends JpaRepository<RentalPlan, Long> {
}
