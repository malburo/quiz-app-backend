package com.example.Quiz.JWT;


import org.springframework.stereotype.Component;

@Component
public class JwtRequest {

    private String userName;
    private String password;

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
