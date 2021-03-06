package com.example.Quiz.JWTModel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JwtRequest {

    private String username;
    private String password;
}
