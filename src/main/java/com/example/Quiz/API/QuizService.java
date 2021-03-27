package com.example.Quiz.API;

import com.example.Quiz.Models.Quiz;
import com.example.Quiz.Quick_Pojo_Class.ErrorMessage;
import com.example.Quiz.Repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class QuizService {
    @Autowired
    QuizRepository quizRepository;
    public void SaveaQuiz (Quiz quiz)
    {
        quizRepository.save(quiz);
    }
    public List<Quiz> getQuizzesbytopicId (long topicId)
    {
        return  quizRepository.GetAllQuizByTopicId(topicId);
    }
    public List<Quiz> Getallquiz ()
    {
        return  quizRepository.findAll();
    }
    public  Quiz Getaquiz (long quizId)
    {
        return  quizRepository.findById(quizId).orElseThrow(() -> new EntityNotFoundException("no quiz with id: "+quizId));
    }
    public ResponseEntity updateaQuiz (Quiz quiz)
    {
        try {

            quizRepository.save(quiz);
            return new ResponseEntity("update completed", HttpStatus.OK);
        }
        catch (Exception ex)
        {
            return new ResponseEntity( new ErrorMessage("409","update fail for some reason"), HttpStatus.CONFLICT);
        }
    }
    public ResponseEntity delete(long quizId) {
        quizRepository.deleteById(quizId);
        return new ResponseEntity( "deleted quizz with id: "+quizId , HttpStatus.OK);


    }
    
}
