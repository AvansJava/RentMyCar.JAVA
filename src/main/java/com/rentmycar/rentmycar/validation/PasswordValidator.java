package com.rentmycar.rentmycar.validation;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PasswordValidator implements Predicate<String> {
    @Override
    public boolean test(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d\\w\\W]{8,}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);

        return matcher.find();
    }
}
