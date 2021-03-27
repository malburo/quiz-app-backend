package com.example.Quiz.API;


import com.example.Quiz.Models.Question;
import com.example.Quiz.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository repository;

    public List<Question> findAll(){
        return repository.findAll();
    }

    public Question findByID(Long id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No such quiz with id:" + id));
    }

    public Question create(Question question){
        return repository.saveAndFlush(question);
    }

    public Question update(Question question){
        return repository.saveAndFlush(question);
    }

    public void deleteById(Long id){
        repository.delete(findByID(id));
    }
}
