package com.rentmycar.rentmycar.validation;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserValidator {
    private final String firstName = null;
    private final String lastName = null;
    private final String street = null;
    private final String houseNumber = null;
    private final String city = null;
    private final String country = null;
    private final String phoneNumber = null;
    private final String iban = null;

    public void validateUser(String firstName, String lastName, String street, String houseNumber, String city,
                                String country, String phoneNumber, String iban) {
        if (!firstName.isEmpty()) {
            if (firstName.length() < 2 || firstName.length() > 50) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "First name should be between 2 and 50 characters.");
            }
        }

        if (!lastName.isEmpty()) {
            if (lastName.length() < 2 || lastName.length() > 100) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Last name should be between 2 and 100 characters.");
            }
        }

        if (!street.isEmpty()) {
            if (street.length() < 2 || street.length() > 100) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Street should be between 2 and 100 characters.");
            }
        }

        if (!houseNumber.isEmpty()) {
            if (houseNumber.length() > 8) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "House number can have a maximum of 8 characters.");
            }
        }

        if (!city.isEmpty()) {
            if (city.length() < 2 || city.length() > 50) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "City should be between 2 and 50 characters.");
            }
        }

        if (!country.isEmpty()) {
            if (country.length() < 2 || country.length() > 50) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Country should be between 2 and 50 characters.");
            }
        }

        if (!phoneNumber.isEmpty()) {
            if (phoneNumber.length() < 8 || phoneNumber.length() > 20) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone number should be between 8 and 20 characters.");
            }
        }

        if (!iban.isEmpty()) {
            Pattern pattern = Pattern.compile("^([A-Z]{2}[ '+'\\\\'+'-]?[0-9]{2})(?=(?:[ '+'\\\\'+'-]?[A-Z0-9]){9,30}$)((?:[ '+'\\\\'+'-]?[A-Z0-9]{3,5}){2,7})([ '+'\\\\'+'-]?[A-Z0-9]{1,3})?$",
                    Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(iban);

            if (!matcher.find()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Iban number has an incorrect format.");
            }
        }
    }
}
