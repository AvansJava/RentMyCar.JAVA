package com.rentmycar.rentmycar.rent.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RentLocationDto {
    private Long id;
    private String street;
    private String city;
    private String country;
}