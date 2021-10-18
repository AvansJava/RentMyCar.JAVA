package com.rentmycar.rentmycar.controller;

import com.rentmycar.rentmycar.dto.ProductRequestDto;
import com.rentmycar.rentmycar.dto.ReservationDto;
import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.service.ReservationService;
import com.rentmycar.rentmycar.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
