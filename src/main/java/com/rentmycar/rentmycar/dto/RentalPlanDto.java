package com.rentmycar.rentmycar.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class RentalPlanDto {
    private Long id;
    private Long carId;
    private Long userId;
    private LocalDate availableFrom;
    private LocalDate availableUntil;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
