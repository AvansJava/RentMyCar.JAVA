package com.rentmycar.rentmycar.dto;

import com.rentmycar.rentmycar.model.CarTimeslotAvailability;
import com.rentmycar.rentmycar.model.Insurance;
import com.rentmycar.rentmycar.model.RentalPlan;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductRequestDto {
    private RentalPlan rentalPlan;
    private String insuranceTypeId;
    private BigDecimal insurancePrice;
    private List<CarTimeslotAvailability> timeslots;
}
