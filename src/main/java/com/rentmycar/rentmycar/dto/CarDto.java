package com.rentmycar.rentmycar.dto;

import com.rentmycar.rentmycar.enums.CarType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CarDto {
    private Long id;
    private String brand;
    private String brandType;
    private String model;
    private String licensePlateNumber;
    private Double consumption;
    private CarType carType;
    private Long userId;
    private Long locationId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}