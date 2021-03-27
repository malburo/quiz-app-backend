package com.example.Quiz.API;

import com.example.Quiz.Models.Quiz;
import com.example.Quiz.Repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizRepository repository;

    public List<Quiz> findAll(){
        return repository.findAll();
    }

    public Quiz findByID(Long id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No such quiz with id:" + id));
    }

    public Quiz create(Quiz quiz){
        return repository.saveAndFlush(quiz);
    }

    public Quiz update(Quiz quiz){
        return repository.saveAndFlush(quiz);
    }

    public void deleteById(Long id){
        repository.delete(findByID(id));
    }

}
