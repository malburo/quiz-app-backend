package com.example.Quiz.API;

import com.example.Quiz.Models.User;
import com.example.Quiz.Config.viewdataconfig;
import com.example.Quiz.Quick_Pojo_Class.ErrorMessage;
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
import java.util.Map;

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
 public ResponseEntity PutuserByuserId (@PathVariable("userId") long userId,@RequestBody User user,
                                        HttpServletRequest request,Principal principal)
 {



    return userService.update(user,userId,principal.getName());


 }

    //******************************************************************//
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{userId}/blocked")
    public  ResponseEntity blockuser (@PathVariable("userId") long userId,@RequestBody Map<String, Boolean> Jsonrequest)
    {
        String key = Jsonrequest.keySet().toString();
        key = key.substring(1, key.length() - 1);
        if (key.equals("blocked"))
            return userService.Blockuser(userId,Jsonrequest.get(key));
        return new ResponseEntity(new ErrorMessage("400", "key : blocked"),HttpStatus.BAD_REQUEST);


    }

    //******************************************************************//
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping("/{userId}/changeAvatar")
    public ResponseEntity updateurlimage (@PathVariable("userId") long userId, @RequestBody Map<String, String> Jsonrequest, Principal principal)
    {
        String key = Jsonrequest.keySet().toString();
        key = key.substring(1, key.length() - 1);
        if (key.equals("imgUrl"))
    return  userService.changeurlImange(userId,Jsonrequest.get(key),principal.getName());
        return new ResponseEntity(new ErrorMessage("400", "key : imgUrl"),HttpStatus.BAD_REQUEST);
    }
    //******************************************************************//
    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping  ("/{userId}/change_password") // doi mat khau
    public HttpEntity changepassword(@PathVariable("userId") long userId ,@RequestBody changePassword password) {
       return accountService.changepassword(password,userId);

    }
}
