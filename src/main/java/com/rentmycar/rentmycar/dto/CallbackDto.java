package com.rentmycar.rentmycar.dto;

import com.rentmycar.rentmycar.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CallbackDto {
    private String externalReference;
    private PaymentStatus status;
}
