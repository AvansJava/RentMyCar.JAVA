package com.rentmycar.rentmycar.dto;

import com.rentmycar.rentmycar.enums.ProductStatus;
import com.rentmycar.rentmycar.model.Insurance;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String reservationNumber;
    private BigDecimal price;
    private Long rentalPlanId;
    private String insuranceTypeId;
    private BigDecimal insurancePrice;
    private ProductStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
