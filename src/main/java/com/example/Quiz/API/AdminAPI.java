package com.example.Quiz.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AdminAPI {
    @Autowired
    TopicService topicService;
    SubTopicService subTopicService;

    

}
