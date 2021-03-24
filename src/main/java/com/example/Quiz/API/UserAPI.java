package com.example.Quiz.API;

import com.example.Quiz.Models.User;
import com.example.Quiz.Quick_Pojo_Class.changePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users") // endpoint nay de get all
public class UserAPI {
    @Autowired
    UserService userService;
    @Autowired
    AccountService accountService;
// admin arena
    @GetMapping
    public List getallusers ()
    {
        return  userService.findAll();
    }

 // user get user info by using jwt
    @RequestMapping(value="/getme", method = {RequestMethod.GET}) // lay thong tin nguoi dung theo jwt
    public Object UserinfoByJwt_GET (Principal principal )
    {
        return  userService.Getuser(principal.getName());
    }


    @PutMapping("/getme")
    public Object UserinfoByJwt_POST ( @RequestBody User user) // doi thong tin nguoi dung
    {
        return  userService.update(user);
    }

    @PostMapping("/Getme/change_password") // doi mat khau
    public ResponseEntity changepassword(@RequestBody changePassword change, Principal principal ) {
        return accountService.changepassword(change,principal.getName());

    }




}
