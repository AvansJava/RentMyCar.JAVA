package com.rentmycar.rentmycar.dto;

import com.rentmycar.rentmycar.enums.TimeSlotAvailabilityStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class AvailabilityStatusDto {
    @Enumerated(EnumType.STRING)
    private TimeSlotAvailabilityStatus status;
}
