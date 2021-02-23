package com.example.Quiz.Repository;

import com.example.Quiz.Models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Long> {

}


