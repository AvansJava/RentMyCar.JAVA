package com.rentmycar.rentmycar.rent.dto;

import com.rentmycar.rentmycar.enums.TimeSlotAvailabilityStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RentCarAvailabilityDto {
    private Long id;
    private Long carId;
    private Long rentalPlanId;
    private Long productId;
    private Long timeslotId;
    private TimeSlotAvailabilityStatus status;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
