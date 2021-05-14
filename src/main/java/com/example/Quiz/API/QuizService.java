package com.example.Quiz.API;

import com.example.Quiz.Models.Quiz;
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

    public void SaveaQuiz(Quiz quiz) {
        quizRepository.save(quiz);
    }

    public List<Quiz> getQuizzesbytopicId(long topicId) {
        return quizRepository.GetAllQuizByTopicId(topicId);
    }


    public ResponseEntity deleteById(long quizId) {
        quizRepository.deleteById(quizId);
        return new ResponseEntity("deleted quizz with id: " + quizId, HttpStatus.OK);

    }
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    public Quiz findByID(Long id) {
        return quizRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No such quiz with id:" + id));
    }

    public Quiz create(Quiz quiz) {
        return quizRepository.saveAndFlush(quiz);
    }

    public Quiz update(Quiz quiz) {
        return quizRepository.saveAndFlush(quiz);
    }

//    public void deleteById(Long id){
//        quizRepository.delete(findByID(id));
//    }


}
