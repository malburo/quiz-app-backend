package com.example.Quiz.Repository;

import com.example.Quiz.Models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {
    @Query("select q from Quiz q where q.topic.topicId = ?1")
    public List<Quiz> GetAllQuizByTopicId (long id);

}
