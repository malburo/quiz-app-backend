package com.example.Quiz.JWTModel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private final String jwtToken;

    public JwtResponse(String token) {
        jwtToken = token;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
