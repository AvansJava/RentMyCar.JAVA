package com.rentmycar.rentmycar.dto;

import com.rentmycar.rentmycar.enums.CarType;
import com.rentmycar.rentmycar.model.Location;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CarDto {
    private Long id;
    private String brand;
    private String brandType;
    private String model;
    private String licensePlateNumber;
    private Double consumption;
    private int costPrice;
    private CarType carType;
    private Long userId;
    private Long locationId;
    private String imagePath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
