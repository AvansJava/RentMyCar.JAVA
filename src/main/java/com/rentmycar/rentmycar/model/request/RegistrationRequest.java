package com.rentmycar.rentmycar.model.request;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    @NotNull
    private final String firstName;
    @NotNull
    private final String lastName;
    @NotNull
    private final String email;
    @NotNull @Size(min = 6)
    private final String password;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
