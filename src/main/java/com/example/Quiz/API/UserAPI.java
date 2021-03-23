package com.example.Quiz.API;

import com.example.Quiz.Models.User;
import com.example.Quiz.Quick_Pojo_Class.changePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
@RestController
@RequestMapping("/users")
public class UserAPI {
    @Autowired
    UserService userService;
    @Autowired
    AccountService accountService;
    @RequestMapping(value="/user", method = {RequestMethod.GET})
    public Object UserAPI_controler_GET (Principal principal )
    {
        return  userService.Getuser(principal.getName());
    }
    @RequestMapping(value="/user", method = {RequestMethod.POST})
    public Object UserAPI_controler_POST ( @RequestBody User user)
    {
        return  userService.update(user);

    }
    @RequestMapping(value="/user", method = {RequestMethod.DELETE})
    public Object UserAPI_controler_DELETE (Principal principal )
    {

        return  null;
    }
    @PostMapping("/user/change_password")
    public ResponseEntity changepassword(@RequestBody changePassword change, Principal principal ) {
        return accountService.changepassword(change,principal.getName());

    }




}
