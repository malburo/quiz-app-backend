package com.example.Quiz.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminAPI {
    @Autowired
    TopicService topicService;

    UserService userService;



    

}
