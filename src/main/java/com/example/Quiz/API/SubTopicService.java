package com.example.Quiz.API;


import com.example.Quiz.Models.SubTopic;
import com.example.Quiz.Repository.SubTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubTopicService {

    @Autowired
    SubTopicRepository repository;

    public List<SubTopic> findAll(){
        return repository.findAll();
    }

    public SubTopic findByID(Long id){
        return  repository.getOne(id);
    }

    public SubTopic create(SubTopic user){
        return repository.saveAndFlush(user);
    }

    public SubTopic update(SubTopic user){
        return repository.saveAndFlush(user);
    }

    public void delete(int id){
        repository.deleteById((long) id);
    }
}
