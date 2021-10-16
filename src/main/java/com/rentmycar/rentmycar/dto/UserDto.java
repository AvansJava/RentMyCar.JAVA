package com.rentmycar.rentmycar.dto;

import lombok.*;

@Getter
@Setter
public class UserDto {
    private String firstName;
    private String lastName;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;
    private String country;
    private String phoneNumber;
    private String iban;
    private String email;
}
