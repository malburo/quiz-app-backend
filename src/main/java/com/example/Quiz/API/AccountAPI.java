package com.example.Quiz.API;

import com.example.Quiz.JWT.JwtRequest;
import com.example.Quiz.JWT.JwtResponse;
import com.example.Quiz.Models.Account;
import com.example.Quiz.Quick_Pojo_Class.Message;
import com.example.Quiz.Ultility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import java.security.Principal;
import java.util.Map;


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
    UserService userService_2;


    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register") //
    public ResponseEntity Register(@RequestBody Account account) {


        return accountService.register(account);

        // regiser
    }

    //    @GetMapping ("/test2")
//    public String test ()
//    {
//        return "hello";
//    }
    @GetMapping("/loginFacebook")
    public void facebooklogin(Object object) {

        // info login client send to us

    }

    @PostMapping("/login")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {

        doAuthenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

        final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token = jwtUtility.generateToken(userDetails);

        return new JwtResponse(token);

    }

    private void doAuthenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(username, password));
            //SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    // user get user info by using jwt
    @RequestMapping(value = "/getme", method = {RequestMethod.GET}) // lay thong tin nguoi dung theo jwt
    public Object UserinfoByJwt_GET(Principal principal) {
        return userService_2.Getuser(principal.getName());
    }

    @PostMapping("/forgot_password")
    public ResponseEntity Forgotpassword(@RequestParam(required = false) String jwttoken, @RequestBody(required = false) Map<String, String> Jsonrequest)
            throws MessagingException, IOException {

        Map<String, String> EmailorNewpassword = Jsonrequest;
        String key = EmailorNewpassword.keySet().toString();
        key = key.substring(1, key.length() - 1);
        String entity = EmailorNewpassword.get(key);
        if (key.equals("password")) {
            try {
                String username = jwtUtility.getUsernameFromToken(jwttoken);
                return accountService.Updatepassword(username, entity); // cap nhap mat khau
            } catch (Exception ex) {
                // bat loi jwt ko hop le
                return new ResponseEntity(new Message("jwt không hợp lệ hoặc quá hạn", "Jwt error"), HttpStatus.BAD_REQUEST);
            }
        }
        if (key.equals("email"))
            return accountService.GenerateMail(entity);
            // send gmail

        else
            return new ResponseEntity(new Message("key must be email or password", "Key error"), HttpStatus.BAD_REQUEST);
        // keyword loi


    }

//    @PostMapping("/Reset_password")


}

