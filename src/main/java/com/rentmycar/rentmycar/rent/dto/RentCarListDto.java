package com.rentmycar.rentmycar.rent.dto;


import com.rentmycar.rentmycar.enums.CarType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RentCarListDto {
    private Long id;
    private String brand;
    private String brandType;
    private String model;
    private CarType carType;
    private RentLocationDto Location;
}
