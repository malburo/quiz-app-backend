package com.example.Quiz.Repository;

import com.example.Quiz.Models.UserQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserQuizRepository extends JpaRepository<UserQuiz,Long> {

}
