package com.example.Quiz.API;

import com.example.Quiz.Models.User;
import com.example.Quiz.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.security.Principal;

@RestController

@RequestMapping("/users")
public class UserAPI {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @RequestMapping(value="/user", method = RequestMethod.GET)
    public  Long UserAPI_controler_vippro (Principal principal , HttpServletRequest httpServletRequest)
    {
//      return userService.checker(principal.getName(),userId);
//        return userService.checker(principal.getName(),principal.)
        return  userRepository.checkerQuery(principal.getName());




    }


}
