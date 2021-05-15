package com.example.Quiz.API;

import com.example.Quiz.Models.UserQuiz;
import com.example.Quiz.Repository.UserQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserQuizService {
    @Autowired
    UserQuizRepository repository;

    public List<UserQuiz> findAll(){
        return repository.findAll();
    }

    public UserQuiz findByID(Long id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No such quiz with id:" + id));
    }

    public UserQuiz create(UserQuiz UserQuiz){
        return repository.saveAndFlush(UserQuiz);
    }

    public UserQuiz update(UserQuiz UserQuiz){
        return repository.saveAndFlush(UserQuiz);
    }

    public void deleteById(Long id){
        repository.delete(findByID(id));
    }
}
