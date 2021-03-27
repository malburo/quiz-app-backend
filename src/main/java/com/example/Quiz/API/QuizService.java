package com.example.Quiz.API;

import com.example.Quiz.Models.Quiz;
import com.example.Quiz.Repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    @Autowired
    QuizRepository quizRepository;
    public List<Quiz> Getallquiz ()
    {
        return  quizRepository.findAll();
    }
    
}
