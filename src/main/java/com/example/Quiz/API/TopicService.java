package com.example.Quiz.API;

import com.example.Quiz.Models.Topic;
import com.example.Quiz.Repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TopicService {

    @Autowired
    TopicRepository repository;

    public List<Topic> findAll(){
        return repository.findAll();
    }

    public Topic findByID(Long id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No such topic with id:" + id));
    }

    public Topic create(Topic topic){
        return repository.saveAndFlush(topic);
    }

    public Topic update(Topic topic){
        return repository.saveAndFlush(topic);
    }

    public void deleteById(Long id){
        repository.delete(findByID(id));
    }

}
