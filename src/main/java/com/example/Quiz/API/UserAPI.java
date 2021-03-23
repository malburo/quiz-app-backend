package com.example.Quiz.API;

import com.example.Quiz.Models.User;
import com.example.Quiz.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.security.Principal;
import java.util.Map;

@RestController

@RequestMapping("/users")
public class UserAPI {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
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



}
