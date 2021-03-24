package com.example.Quiz.API;
import com.example.Quiz.JWT.JwtRequest;
import com.example.Quiz.JWT.JwtResponse;
import com.example.Quiz.Models.Account;
import com.example.Quiz.Ultility.JWTUtility;
import com.example.Quiz.Ultility.JavaMailUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import java.io.IOException;
import java.time.chrono.JapaneseChronology;


@RestController
@RequestMapping("/auth")
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
        return accountService.register(account);
        // regiser
    }
//    @GetMapping ("/test2")
//    public String test ()
//    {
//        return "hello";
//    }
    @GetMapping("/loginFacebook")
    public void  facebooklogin(Object object)
    {

      // info login client send to us

    }

    @PostMapping("/login")
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
    @GetMapping("/Authenticate_email")
    public String sendEmail() throws IOException, MessagingException {
        JavaMailUtility javaMailUtility = new JavaMailUtility();
        javaMailUtility.sendmail();
        return "Email sent successfully";
    }



}

