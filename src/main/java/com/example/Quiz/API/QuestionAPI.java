package com.example.Quiz.API;


import com.example.Quiz.Models.Question;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/questions")
public class QuestionAPI {
    @Autowired
    QuestionService questionService;

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Long id){
        questionService.deleteById(id);
        return new ResponseEntity<>("Successfully deleted question with id:"+id, HttpStatus.OK);
    }
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Question> update(@PathVariable Long id, @RequestBody Question question) throws ValidationException {
        Question existQuestion  = questionService.findByID(id);
        if (question.getQuestionName() == null || question.getQuestionDescription() == null )
            throw new ValidationException("Missing information for question");
        BeanUtils.copyProperties(question,existQuestion,"questionId","quiz");
        return new ResponseEntity<>(questionService.update(existQuestion),HttpStatus.OK);
    }
    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<Question> get(@PathVariable Long id ) throws EntityNotFoundException {
        Question question = questionService.findByID(id);
        return new ResponseEntity<>(question,HttpStatus.OK);
    }
}
