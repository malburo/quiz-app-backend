package com.example.Quiz.API;

import com.example.Quiz.Models.Account;
import com.example.Quiz.Models.User;
import com.example.Quiz.Quick_Pojo_Class.Message;
import com.example.Quiz.Quick_Pojo_Class.changePassword;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.ArrayList;
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
    @GetMapping("/users/{userId}")
    public User GetuserByuserId (@PathVariable("userId") long userId)
    {
        return  userService.findByID(userId);
    }
    @DeleteMapping("/users/{userId}")
    public ResponseEntity DeleteuserByuserId (@PathVariable("userId") long userId)

    {
        userService.delete(userId);
        accountService.delete(userId);
        return  new ResponseEntity(new Message("Delete completed"), HttpStatus.OK);

    }
 @PutMapping("/users/{userId}")
 public HttpEntity PutuserByuserId (@PathVariable("userId") long userId, User user)
 {
     if (userId==user.getUserId())
    return userService.update(user);
     return new ResponseEntity(new Message("Ids did'nt match",""), HttpStatus.BAD_REQUEST);

 }

    @PostMapping  ("/{userId}/change_password") // doi mat khau
    public HttpEntity changepassword(@PathVariable("userId") long userId ,@RequestBody changePassword password) {
       return accountService.changepassword(password,userId);

    }
}
