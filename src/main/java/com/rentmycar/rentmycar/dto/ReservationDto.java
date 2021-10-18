package com.rentmycar.rentmycar.dto;

import com.rentmycar.rentmycar.enums.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationDto {
    private String reservationNumber;
    private Long userId;
    private BigDecimal price;
    private ReservationStatus status;
    private LocalDateTime paidAt;
    private ProductDto product;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
