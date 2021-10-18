package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.dto.ProductDto;
import com.rentmycar.rentmycar.dto.ProductRequestDto;
import com.rentmycar.rentmycar.dto.ReservationDto;
import com.rentmycar.rentmycar.enums.ReservationStatus;
import com.rentmycar.rentmycar.model.Product;
import com.rentmycar.rentmycar.model.Reservation;
import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.repository.ReservationRepository;
import com.rentmycar.rentmycar.util.ReservationNumberGenerator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ReservationService implements ReservationNumberGenerator {

    private final ReservationRepository reservationRepository;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ResponseEntity<ReservationDto> createReservation(User user, ProductRequestDto productRequest) {
        // First create a new empty reservation
        Reservation reservation = new Reservation(
                randomString(10),
                user,
                ReservationStatus.PENDING
        );
        Reservation createdReservation = reservationRepository.save(reservation);

        // create reservation product
        ProductDto product = productService.createProduct(productRequest, reservation);

        // update reservation price with calculation of product items.
        BigDecimal totalPrice = product.getPrice().add(product.getInsurancePrice());
        createdReservation.setPrice(totalPrice);
        ReservationDto reservationDto = modelMapper.map(reservationRepository.save(createdReservation), ReservationDto.class);

        reservationDto.setProduct(product);

        return new ResponseEntity<>(modelMapper.map(reservationDto, ReservationDto.class), HttpStatus.CREATED);
    }

    public void completeReservation(Reservation reservation) {
        reservation.setStatus(ReservationStatus.COMPLETED);
        reservation.setPaidAt(LocalDateTime.now());
        productService.completeProduct(reservation);

        try {
            reservationRepository.save(reservation);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Reservation could not be updated.");
        }
    }

    public void cancelReservation(Reservation reservation) {
        reservation.setStatus(ReservationStatus.CANCELED);
        productService.cancelProduct(reservation);

        try {
            reservationRepository.save(reservation);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Reservation could not be updated.");
        }
    }
}
