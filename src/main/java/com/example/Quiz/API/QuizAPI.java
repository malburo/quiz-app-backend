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

@RestController
@RequestMapping("/quizzes")
public class QuizAPI {
    @Autowired
    QuizService quizService;
    @Autowired
    TopicService topicService;


    @GetMapping("{quizId}")
    public Quiz getQuiz(@PathVariable("quizId") long quizId) {
        return quizService.Getaquiz(quizId);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("{quizId}")
    public Object PutQuiz(@PathVariable("quizId") long quizId, @RequestBody Map<String,Object> mapList) throws  ValidationException {

            Quiz quiz = quizService.Getaquiz(quizId);

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
        return quizService.delete(quizId);

    }
}
