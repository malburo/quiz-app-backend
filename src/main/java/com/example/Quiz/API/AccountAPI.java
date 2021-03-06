package com.example.Quiz.API;

import com.example.Quiz.JWTModel.JwtRequest;
import com.example.Quiz.JWTModel.JwtResponse;
import com.example.Quiz.Models.Account;
import com.example.Quiz.Ultility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;


import java.util.HashMap;


@RestController
@RequestMapping("/")
public class AccountAPI {
    @Autowired
    AccountService accountService;
    @Autowired
    UserDetailsService userService;
    @Autowired
    JWTUtility jwtUtility;

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

        return "hello";
    }

    @PostMapping("/login")
    public JwtResponse authenticate (@RequestBody JwtRequest jwtRequest) throws Exception {
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                jwtRequest.getUsername(),
                                jwtRequest.getPassword()
                        )
                );
            }catch (BadCredentialsException exception){
                throw new Exception("authentication request is rejected,the account is neither not existed,locked nor disabled.",exception);
            }

            final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());

            final String token = jwtUtility.generateToken(userDetails);

            return new JwtResponse(token);

    }


}
