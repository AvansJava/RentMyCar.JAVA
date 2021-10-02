package com.rentmycar.rentmycar.validation;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String email) {
        // TODO: regex to validate email
        return true;
    }
}
