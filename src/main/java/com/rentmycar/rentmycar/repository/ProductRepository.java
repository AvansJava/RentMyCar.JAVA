package com.rentmycar.rentmycar.repository;

import com.rentmycar.rentmycar.model.Product;
import com.rentmycar.rentmycar.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> getByReservation(Reservation reservation);
}
