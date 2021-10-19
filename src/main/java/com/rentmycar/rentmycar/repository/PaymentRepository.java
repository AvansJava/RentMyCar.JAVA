package com.rentmycar.rentmycar.repository;

import com.rentmycar.rentmycar.model.Payment;
import com.rentmycar.rentmycar.model.Reservation;
import com.rentmycar.rentmycar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query("SELECT p FROM Payment p WHERE p.reservation = ?1 AND p.paymentStatus = 'SUCCESS' ")
    Optional<Payment> findBySuccessfulPayment(Reservation reservation);

    @Query("SELECT p FROM Payment p WHERE p.reservation = ?1 AND p.paymentStatus = 'PENDING' ")
    Optional<Object> findByPendingPayment(Reservation reservation);

    List<Payment> findAllByUser(User user);
}
