package com.example.Quiz.JWT;


import org.springframework.stereotype.Component;

@Component

public class JwtResponse {

    private  String jwtToken;

    public JwtResponse(String token) {
        jwtToken = token;
    }

    public JwtResponse() {
    }

    public String getJwtToken() {
        return jwtToken;
    }

}
