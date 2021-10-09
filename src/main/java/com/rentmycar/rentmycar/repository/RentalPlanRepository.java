package com.rentmycar.rentmycar.repository;

import com.rentmycar.rentmycar.model.Car;
import com.rentmycar.rentmycar.model.RentalPlan;
import com.rentmycar.rentmycar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalPlanRepository extends JpaRepository<RentalPlan, Long> {
    List<RentalPlan> findAllByUser(User user);
}
