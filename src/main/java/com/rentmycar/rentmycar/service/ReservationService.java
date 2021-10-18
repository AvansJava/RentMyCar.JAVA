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

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class ReservationService implements ReservationNumberGenerator {

    private final ReservationRepository reservationRepository;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ResponseEntity<ReservationDto> createReservation(User user, ProductRequestDto productRequest) {
        Reservation reservation = new Reservation(
                randomString(10),
                user,
                ReservationStatus.PENDING
        );
        Reservation createdReservation = reservationRepository.save(reservation);
        ProductDto product = productService.createProduct(productRequest, reservation);

        BigDecimal totalPrice = product.getPrice().add(product.getInsurancePrice());
        createdReservation.setPrice(totalPrice);
        ReservationDto reservationDto = modelMapper.map(reservationRepository.save(createdReservation), ReservationDto.class);

        reservationDto.setProduct(product);

        return new ResponseEntity<>(modelMapper.map(reservationDto, ReservationDto.class), HttpStatus.CREATED);
    }
}
