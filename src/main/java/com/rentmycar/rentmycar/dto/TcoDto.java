package com.rentmycar.rentmycar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TcoDto {
    private Long CarId;
    private Double consumption;
    private int kilometers;
    private int costPrice;
    private Double depreciation;
    private Double yearlyFuelCost;
    private Double totalCostOwnership;
    private Double costPerKilometer;
}
