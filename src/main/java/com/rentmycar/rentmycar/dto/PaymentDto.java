package com.rentmycar.rentmycar.dto;

import com.rentmycar.rentmycar.enums.PaymentMethod;
import com.rentmycar.rentmycar.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentDto {
    private Long id;
    private String reservationNumber;
    private Long userId;
    private BigDecimal price;
    private PaymentStatus paymentStatus;
    private PaymentMethod paymentMethod;
    private LocalDateTime paidAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
