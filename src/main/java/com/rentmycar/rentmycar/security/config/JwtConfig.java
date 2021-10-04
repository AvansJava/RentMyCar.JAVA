package com.rentmycar.rentmycar.security.config;

public class JwtConfig {
    public static final String SECRET_KEY = "f9c48b57-24bb-4313-94c4-0b36aece7bd7";
    public static final int EXPIRATION_TIME = 86400000;
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
}
