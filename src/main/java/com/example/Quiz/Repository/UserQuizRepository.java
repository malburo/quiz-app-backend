package com.example.Quiz.Repository;

import com.example.Quiz.Models.Quiz;
import com.example.Quiz.Models.User;
import com.example.Quiz.Models.UserQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserQuizRepository extends JpaRepository<UserQuiz,Long> {

    @Query("Select uq from UserQuiz uq where uq.user.userId = :userId and uq.quiz.quizId = :quizId Order by uq.point DESC")
    List<UserQuiz> findByUserIdAndQuizId(@Param("userId") Long userId, @Param("quizId") Long quizId);

    List<UserQuiz> findByUserAndQuizOrderByPointDesc(User user, Quiz quiz);

}
