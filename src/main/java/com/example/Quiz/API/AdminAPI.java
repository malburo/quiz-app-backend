package com.example.Quiz.API;

import com.example.Quiz.Models.User;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminAPI {
    @Autowired
    TopicService topicService;
    UserService userService;



    

}
