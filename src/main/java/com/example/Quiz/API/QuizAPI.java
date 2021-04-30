package com.example.Quiz.API;


import com.example.Quiz.Models.Quiz;
import com.example.Quiz.Quick_Pojo_Class.ErrorMessage;
import com.example.Quiz.Repository.QuizRepository;
import com.example.Quiz.Repository.TopicRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.xml.bind.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@CrossOrigin(origins = "http://localhost:3000")
public class QuizAPI {

    @Autowired
    QuizService quizService;
    @Autowired
    TopicService topicService;
    @Autowired
    QuestionService questionService;

    @GetMapping("{quizId}")
    public Quiz getQuiz(@PathVariable("quizId") long quizId) {
        return quizService.findByID(quizId);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("{quizId}")
    public Object PutQuiz(@PathVariable("quizId") long quizId, @RequestBody Map<String,Object> mapList) throws  ValidationException {

            Quiz quiz = quizService.findByID(quizId);

// code ngu vai lon, ban co 100 thuoc tinh thi sao?
        // thi tinh cach khac ahihi do ngok
        //dit me may ngu  vl
        for(Map.Entry<String, Object> entry : mapList.entrySet())
        {

            if (entry.getKey().equals("quizName"))
            {
                quiz.setQuizName(entry.getValue().toString());

            }
            if (entry.getKey().equals("qizDescription"))
            {
                quiz.setQizDescription(entry.getValue().toString());

            }
            if(entry.getKey().equals("topicId"))
            {
                quiz.setTopic(topicService.findByID(Long.parseLong( entry.getValue().toString())));

            }
        }
        quizService.SaveaQuiz(quiz);
        return new ResponseEntity<>("quiz "+quizId+" updated",HttpStatus.OK);

    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("{quizId}")
    public ResponseEntity DeleteuserByuserId(@PathVariable("quizId") long quizId) {
        return quizService.deleteById(quizId);

    }

    // question arena
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
