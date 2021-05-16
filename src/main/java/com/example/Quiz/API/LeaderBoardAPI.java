package com.example.Quiz.API;

import com.example.Quiz.Models.User;
import com.example.Quiz.Repository.AccountRepository;
import com.example.Quiz.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/leaderboard")
@CrossOrigin
public class LeaderBoardAPI {
    @Autowired
    UserRepository userRepository;
    @GetMapping
    public List<User> leaderboard ()
    {

      return    userRepository.findTop10ByOrderByPointDesc();


    }
}
