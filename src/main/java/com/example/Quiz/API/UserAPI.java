package com.example.Quiz.API;

import com.example.Quiz.Models.User;
import com.example.Quiz.Config.viewdataconfig;
import com.example.Quiz.Quick_Pojo_Class.changePassword;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;
import java.security.Principal;

@RestController
@RequestMapping("/users") // endpoint nay de get all
@CrossOrigin
public class UserAPI {
    @Autowired
    UserService userService;
    @Autowired
    AccountService accountService;

    //******************************************************************//
// admin arena
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public Object getallusers (Principal principal) throws  ValidationException
    {
            return  userService.findAll();

    }

    //******************************************************************//
    @JsonView(viewdataconfig.Public.class)
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{userId}")
    public User GetuserByuserId  (@PathVariable("userId") int userId) throws  Exception
    { return  userService.findByID((long)userId); }

    //******************************************************************//
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity DeleteuserByuserId (@PathVariable("userId") int userId)
    {
        // tao account thi ben kia cung phai co user
        userService.delete(userId);
        return  new ResponseEntity("delete succcessed" +userId, HttpStatus.OK);
    }

    //******************************************************************//
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping("/{userId}")
 public ResponseEntity PutuserByuserId (@PathVariable("userId") long userId,@RequestParam(required = false) boolean blocked,@RequestBody User user,
                                        HttpServletRequest request,Principal principal)
 {

     if (blocked && request.isUserInRole("ROLE_ADMIN") )
         return userService.Blockuser(userId);

    return userService.update(user,userId,principal.getName());


 }

    //******************************************************************//



    //******************************************************************//
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping("/{userId}/changeAvatar ")
    public ResponseEntity updateurlimage (@PathVariable("userId") long userId,@RequestParam String urlImage, Principal principal)
    {
    return  userService.changeurlImange(userId,urlImage,principal.getName());
    }
    //******************************************************************//
    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping  ("/{userId}/change_password") // doi mat khau
    public HttpEntity changepassword(@PathVariable("userId") long userId ,@RequestBody changePassword password) {
       return accountService.changepassword(password,userId);

    }
}
