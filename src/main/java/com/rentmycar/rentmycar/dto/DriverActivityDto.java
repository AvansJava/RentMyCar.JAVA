package com.rentmycar.rentmycar.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DriverActivityDto {
    private Long id;
    private Long carId;
    private String reservationNumber;
    private Double distanceDriven;
    private Double topSpeed;
    private Double averageSpeed;
    private Double acceleration;
    private LocalDateTime lastSyncedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
