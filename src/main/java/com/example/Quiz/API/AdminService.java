package com.example.Quiz.API;

import com.example.Quiz.Repository.AccountRepository;
import com.example.Quiz.Repository.SubTopicRepository;
import com.example.Quiz.Repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    SubTopicRepository subTopicRepository;
    TopicRepository topicRepository;
    AccountRepository accountRepository;

}
