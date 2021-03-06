package com.example.Quiz.API;

import com.example.Quiz.JWTModel.JwtRequest;
import com.example.Quiz.JWTModel.JwtResponse;
import com.example.Quiz.Models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;


import java.util.HashMap;


@RestController
@RequestMapping("/")
public class AccountAPI {
    @Autowired
    AccountService accountService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/test")
    public HashMap Register ( @RequestBody Account account )
    {
         return accountService.register(account);
         // regiser
    }
    @GetMapping ("/test2")
    public String test ()
    {

        return "helo";
    }

    @PostMapping("/login")
    public JwtResponse authenticate (@RequestBody JwtRequest jwtRequest){
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                jwtRequest.
                        )
                )
            }
    }


}
