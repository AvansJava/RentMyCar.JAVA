package com.rentmycar.rentmycar.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String first_name;
    private final String last_name;
    private final String email;
    private final String password;

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }
}
