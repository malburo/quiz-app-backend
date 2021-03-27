package com.example.Quiz.API;


import com.example.Quiz.Models.Question;
import com.example.Quiz.Models.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quizzes")
public class QuizAPI {

    @Autowired
    QuizService quizService;
    @Autowired
    QuestionService questionService;

    @GetMapping
    @RequestMapping("/{id}/questions")
    public ResponseEntity<List<Question>> getAllQuestionFromQuiz(@PathVariable Long id){
        Quiz quiz = quizService.findByID(id);
        List<Question> questionList = quiz.getQuestions();
        return new ResponseEntity<>(questionList, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Secured("ROLE_ADMIN")
    @PostMapping("/{id}/questions")
    public Question create(@PathVariable long id,@RequestBody Question question){
        Quiz quiz = quizService.findByID(id);
        question.setQuiz(quiz);
        questionService.create(question);
        //quiz.getQuestions().add(question);
        return question;
    }

}
