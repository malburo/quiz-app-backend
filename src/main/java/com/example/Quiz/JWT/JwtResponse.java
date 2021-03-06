package com.example.Quiz.JWT;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
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
