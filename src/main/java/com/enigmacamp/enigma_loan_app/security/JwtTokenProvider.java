package com.enigmacamp.enigma_loan_app.security;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

//    public String getUsernameFromToken(String token) {
//        DecodedJWT decodedJWT = getDecodedJWT(token);
//        return decodedJWT.getSubject();
//    }
//
//    public Boolean validationToken(String token) {
//        try {
//            DecodedJWT decodedJWT = getDecodedJWT(token);
//            return !decodedJWT.getExpiresAt().before(new Date());
//        } catch (Exception e) {
//            return false;
//        }
//    }
}
