package com.example.Quiz.API;
import com.example.Quiz.JWT.JwtRequest;
import com.example.Quiz.JWT.JwtResponse;
import com.example.Quiz.Models.Account;
import com.example.Quiz.Quick_Pojo_Class.Accountregister;
import com.example.Quiz.Quick_Pojo_Class.Message;
import com.example.Quiz.Ultility.JWTUtility;
import com.example.Quiz.Ultility.JavaMailUtility;
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
    UserService userService_2 ;


    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register") //
    public ResponseEntity Register (@RequestBody Accountregister accountregister)
    {
        return accountService.register(accountregister);
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
    // user get user info by using jwt
    @RequestMapping(value="/getme", method = {RequestMethod.GET}) // lay thong tin nguoi dung theo jwt
    public Object UserinfoByJwt_GET (Principal principal )
    {
        return  userService_2.Getuser(principal.getName());
    }

    @GetMapping("/users/{userId}/reset_password")
    public Object sendEmail( @RequestBody   String Jsonrequest) {
       String email=Jsonrequest.substring(10,Jsonrequest.length()-2); // lay email
       Account account = accountService.GenerateMail(email);
        if (account==null)
        {
            // gui loi neu khong co user or email loi
            return  new   ResponseEntity( new Message("Nếu chuỗi này không dư ký tự thì email ko tồn tại: "+email,
                    "Email or Json lenght did'nt correct"), HttpStatus.OK);
        }
        else {//do stuff
            return  null;}




    }
    @GetMapping("/mail")
    public Object mail( ) throws IOException,MessagingException

    {
        JavaMailUtility javaMailUtility = new JavaMailUtility();
        javaMailUtility.sendmail("quizapphutech@gmail.com","tan");
        return null;


    }
//    @PostMapping("/Reset_password")




}

