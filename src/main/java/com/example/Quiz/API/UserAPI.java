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

    @GetMapping
    public List getallusers ()
    {
        return  userService.findAll();
    }


    @RequestMapping(value="/user", method = {RequestMethod.GET}) // lay thong tin nguoi dung theo jwt
    public Object UserAPI_controler_GET (Principal principal )
    {
        return  userService.Getuser(principal.getName());
    }


    @PutMapping("/user")
    public Object UserAPI_controler_POST ( @RequestBody User user) // doi thong tin nguoi dung
    {
        return  userService.update(user);
    }


    @RequestMapping(value="/user", method = {RequestMethod.DELETE}) // tam thoi de method phong ho`
    public Object UserAPI_controler_DELETE (Principal principal )
    {
        return  null;
    }


    @PostMapping("/user/change_password") // doi mat khau
    public ResponseEntity changepassword(@RequestBody changePassword change, Principal principal ) {
        return accountService.changepassword(change,principal.getName());

    }




}
