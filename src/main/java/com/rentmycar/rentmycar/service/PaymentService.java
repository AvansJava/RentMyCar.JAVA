package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.dto.CallbackDto;
import com.rentmycar.rentmycar.dto.PaymentDto;
import com.rentmycar.rentmycar.enums.PaymentStatus;
import com.rentmycar.rentmycar.model.Payment;
import com.rentmycar.rentmycar.model.Reservation;
import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.repository.PaymentRepository;
import com.rentmycar.rentmycar.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;
    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;

    public ResponseEntity<PaymentDto> createPayment(Payment payment, User user) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(payment.getReservation().getReservationNumber());

        if (reservationOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found.");
        }
        Reservation reservation = reservationOptional.get();

        if (reservation.getUser() != user) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User cannot pay for this reservation.");
        }

        boolean alreadyPaid = paymentRepository.findBySuccessfulPayment(reservation).isPresent();
        if (alreadyPaid) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reservation is already paid.");
        }

        boolean alreadyPending = paymentRepository.findByPendingPayment(reservation).isPresent();
        if (alreadyPending) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A payment for this reservation is already in progress.");
        }

        payment.setPrice(reservation.getPrice());
        payment.setPaymentStatus(PaymentStatus.PENDING);

        /* This is where a POST request to an external payment service provider would take place,
           with reservation number as an external reference.
         */

        PaymentDto paymentDto = modelMapper.map(paymentRepository.save(payment), PaymentDto.class);

        return new ResponseEntity<>(paymentDto, HttpStatus.CREATED);
    }

    public ResponseEntity<String> processCallback(Long id, CallbackDto callback) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(callback.getExternalReference());
        Optional<Payment> paymentOptional = paymentRepository.findById(id);

        if (reservationOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found.");
        }
        Reservation reservation = reservationOptional.get();

        if (paymentOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found.");
        }
        Payment payment = paymentOptional.get();

        if (payment.getReservation() != reservation) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment does not match reservation.");
        }

        if (!payment.getPaymentStatus().equals(PaymentStatus.PENDING)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment already has a final status. Payment is immutable.");
        }

        PaymentStatus callbackStatus = callback.getStatus();
        payment.setPaymentStatus(callbackStatus);

        if (callbackStatus.equals(PaymentStatus.SUCCESS)) {
            payment.setPaidAt(LocalDateTime.now());
            reservationService.completeReservation(reservation);
        } else {
            reservationService.cancelReservation(reservation);
        }

        paymentRepository.save(payment);
        return new ResponseEntity<>("Callback successfully processed.", HttpStatus.OK);
    }
}
