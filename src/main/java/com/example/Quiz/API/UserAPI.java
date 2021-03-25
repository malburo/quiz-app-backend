package com.example.Quiz.API;

import com.example.Quiz.Models.Account;
import com.example.Quiz.Models.User;
import com.example.Quiz.Quick_Pojo_Class.Message;
import com.example.Quiz.Quick_Pojo_Class.changePassword;
import com.example.Quiz.Repository.AccountRepository;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public List<User> getallusers ()
    {
        return  userService.findAll();
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{userId}")
    public User GetuserByuserId (@PathVariable("userId") int userId)
    {
        return  userService.findByID((long)userId);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{userId}")

    public ResponseEntity DeleteuserByuserId (@PathVariable("userId") int userId)

    {

        // tao account thi ben kia cung phai co user
        userService.delete(userId);
        return  new ResponseEntity(new Message("","Delete completed"), HttpStatus.OK);

    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping("/{userId}")
 public ResponseEntity PutuserByuserId (@PathVariable("userId") long userId,@RequestBody User user)
 {
     if (userId==user.getUserId())
    return userService.update(user);
     return new ResponseEntity(new Message("Ids did'nt match",""), HttpStatus.BAD_REQUEST);


 }
    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping  ("/{userId}/change_password") // doi mat khau
    public HttpEntity changepassword(@PathVariable("userId") long userId ,@RequestBody changePassword password) {
       return accountService.changepassword(password,userId);

    }
}
