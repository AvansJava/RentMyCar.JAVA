package com.rentmycar.rentmycar.util;

import java.security.SecureRandom;

public interface ReservationNumberGenerator {

    String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    SecureRandom rnd = new SecureRandom();

    default String randomString(int length){
        StringBuilder stringBuilder = new StringBuilder(length);
        for(int i = 0; i < length; i++)
            stringBuilder.append(chars.charAt(rnd.nextInt(chars.length())));
        return stringBuilder.toString();
    }
}
