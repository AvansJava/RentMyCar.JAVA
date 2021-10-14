package com.rentmycar.rentmycar.rent.repository;

import com.rentmycar.rentmycar.common.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RentCarRepository extends JpaRepository<Car, Long> {

}
