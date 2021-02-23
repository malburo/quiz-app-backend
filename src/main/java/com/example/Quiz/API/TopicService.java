package com.example.Quiz.API;

import com.example.Quiz.Models.Topic;
import com.example.Quiz.Repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TopicService {

    @Autowired
    TopicRepository repository;

    public List<Topic> findAll(){
        return repository.findAll();
    }

    public Topic findByID(Long id){
        return  repository.getOne(id);
    }

    public Topic create(Topic user){
        return repository.saveAndFlush(user);
    }

    public Topic update(Topic user){
        return repository.saveAndFlush(user);
    }

    public void delete(int id){
        repository.deleteById((long) id);
    }

}
