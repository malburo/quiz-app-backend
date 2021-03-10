package com.example.Quiz.API;

import com.example.Quiz.JWT.JwtRequest;
import com.example.Quiz.JWT.JwtResponse;
import com.example.Quiz.Models.Account;
import com.example.Quiz.Quick_Pojo_Class.changePassword;
import com.example.Quiz.Ultility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import com.example.Quiz.Quick_Pojo_Class.changePassword;

import java.security.Principal;


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

    @PostMapping("/register") //
    public ResponseEntity Register (@RequestBody Account account )
    {
       return accountService.register(account); // tra ve respone


         // regiser
    }
    @GetMapping ("/test2")
    public String test ()
    {
    
        return "hello";
    }
    @GetMapping("/loginFacebook")
    public void  facebooklogin(Object object)
    {

      // info login client send to us

    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate (@RequestBody JwtRequest jwtRequest) throws Exception {

            doAuthenticate(jwtRequest.getUsername(),jwtRequest.getPassword());

            final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());

            final String token = jwtUtility.generateToken(userDetails);


             return new JwtResponse(token);

    }

    private void doAuthenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(username,password));
             //SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    @GetMapping("/user")
    public Principal userhome (Principal principal)
    {
        return principal;
    }
    @PostMapping("/user/changepassword")
    public ResponseEntity changepassword(@RequestBody changePassword change,Principal principal ) {
        return accountService.changepassword(change,principal.getName());

    }

}

