package com.rentmycar.rentmycar.dto;

import com.rentmycar.rentmycar.enums.CarType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CarDto {
    private String brand;
    private String brandType;
    private String model;
    private CarType carType;
    private LocationDto Location;
}


