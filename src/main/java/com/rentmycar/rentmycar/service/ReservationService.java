package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.dto.*;
import com.rentmycar.rentmycar.enums.ReservationStatus;
import com.rentmycar.rentmycar.model.CarTimeslotAvailability;
import com.rentmycar.rentmycar.model.Product;
import com.rentmycar.rentmycar.model.Reservation;
import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.repository.CarTimeslotAvailabilityRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationService implements ReservationNumberGenerator {

    private final ReservationRepository reservationRepository;
    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final CarTimeslotAvailabilityService carTimeslotAvailabilityService;
    private final CarTimeslotAvailabilityRepository carTimeslotAvailabilityRepository;

    public ResponseEntity<ReservationDto> createReservation(User user, ProductRequestDto productRequest) {
        // Check availability of timeslots
        boolean isAvailable = carTimeslotAvailabilityService.checkAvailability(productRequest.getTimeslots());

        if (!isAvailable) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Selected timeslots are not available.");
        }

        // Create a new empty reservation
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

    public ReservationDto getReservationByUser(String reservationNumber, User user) {
        Reservation reservation = findReservationByUser(reservationNumber, user);
        return getProductAndMapToDto(reservation);
    }

    public Reservation findReservationByUser(String reservationNumber, User user) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationNumber);

        if (reservationOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found.");
        }
        Reservation reservation = reservationOptional.get();

        if (reservation.getUser() != user) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Reservation does not belong to user.");
        }
        return reservation;
    }

    private ReservationDto getProductAndMapToDto(Reservation reservation) {
        ProductDto productDto = modelMapper.map(productService.getProduct(reservation), ProductDto.class);
        ReservationDto reservationDto = modelMapper.map(reservation, ReservationDto.class);
        reservationDto.setProduct(productDto);

        return reservationDto;
    }

    public List<ReservationDto> getAllReservationsByUser(User user, ReservationStatus status) {
        List<Reservation> reservations = reservationRepository.findAllByUser(user);

        List<ReservationDto> reservationDtoList = new ArrayList<>();
        for (Reservation reservation: reservations) {
            reservationDtoList.add(getProductAndMapToDto(reservation));
        }

        if (status != null) {
            reservationDtoList.removeIf(reservation -> !reservation.getStatus().toString().contains(status.toString()));
        }

        return reservationDtoList;
    }

    public List<CarTimeslotAvailabilityDto> getReservationAvailabilityByReservation(String reservationNumber, User user) {
        Reservation reservation = findReservationByUser(reservationNumber, user);
        Product product = productService.getProduct(reservation);
        return carTimeslotAvailabilityRepository.findAllByProduct(product).stream()
                .map(obj -> modelMapper.map(obj, CarTimeslotAvailabilityDto.class))
                .collect(Collectors.toList());
    }
}
