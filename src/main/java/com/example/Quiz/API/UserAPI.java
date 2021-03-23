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
    @RequestMapping(value="/user", method = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST,RequestMethod.PUT})
    public Object UserAPI_controler_vippro (Principal principal , HttpServletRequest httpServletRequest, @RequestBody User user)
    {

        String method = httpServletRequest.getMethod();
        if (method.equals("GET"))
        {
            return  userService.Getuser(principal.getName());
        }
        if (method.equals("POST"))
        {
                return userService.PostUser(user);
        }
        if (method.equals("DELETE"))
        {

        }
        if (method.equals("PUT"))
        {

        }
        return  null;




    }


}
