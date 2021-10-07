package com.rentmycar.rentmycar.repository;

import com.rentmycar.rentmycar.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, String> {

}
