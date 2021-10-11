package com.rentmycar.rentmycar.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LocationDto {
    private Long id;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;
    private String country;
    private Float latitude;
    private Float longitude;
}
