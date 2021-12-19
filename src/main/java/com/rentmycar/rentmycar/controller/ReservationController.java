package com.rentmycar.rentmycar.controller;

import com.rentmycar.rentmycar.dto.CarTimeslotAvailabilityDto;
import com.rentmycar.rentmycar.dto.ProductRequestDto;
import com.rentmycar.rentmycar.dto.ReservationDto;
import com.rentmycar.rentmycar.enums.ReservationStatus;
import com.rentmycar.rentmycar.model.CarTimeslotAvailability;
import com.rentmycar.rentmycar.model.Reservation;
import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.service.ReservationService;
import com.rentmycar.rentmycar.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path="api/v1.0/reservation/")
public class ReservationController {

    private final UserService userService;
    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationDto> postReservation(@RequestBody ProductRequestDto productRequest) {
        User user = userService.getAuthenticatedUser();
        return reservationService.createReservation(user, productRequest);
    }

    @GetMapping(path = "{reservationNumber}/")
    public ReservationDto getReservation(@PathVariable("reservationNumber") String reservationNumber) {
        User user = userService.getAuthenticatedUser();
        return reservationService.getReservationByUser(reservationNumber, user);
    }

    @GetMapping
    public List<ReservationDto> getReservations(@RequestParam(required=false) ReservationStatus status) {
        User user = userService.getAuthenticatedUser();
        return reservationService.getAllReservationsByUser(user, status);
    }

    @GetMapping(path = "{reservationNumber}/availability")
    public List<CarTimeslotAvailabilityDto> getReservationAvailability(@PathVariable("reservationNumber") String reservationNumber) {
        User user = userService.getAuthenticatedUser();
        return reservationService.getReservationAvailabilityByReservation(reservationNumber, user);
    }
}
