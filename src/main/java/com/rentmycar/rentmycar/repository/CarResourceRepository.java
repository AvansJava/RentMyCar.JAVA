package com.rentmycar.rentmycar.repository;

import com.rentmycar.rentmycar.model.CarResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarResourceRepository extends JpaRepository<CarResource, Long> {
}
