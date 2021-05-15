package com.example.Quiz.API;


import com.example.Quiz.MarkResolver.MarkResolverRequest;
import com.example.Quiz.MarkResolver.MarkResolverResponse;
import com.example.Quiz.Models.Question;
import com.example.Quiz.Models.Quiz;
import com.example.Quiz.Models.User;
import com.example.Quiz.Models.UserQuiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.io.InvalidClassException;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/quizzes")
public class QuizAPI {
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    QuizService quizService;
    @Autowired
    TopicService topicService;
    @Autowired
    QuestionService questionService;

    @Autowired
    UserQuizService userQuizService;



    @GetMapping("{quizId}")
    public Quiz getQuiz(@PathVariable("quizId") long quizId) {
        return quizService.findByID(quizId);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("{quizId}")
    public Object PutQuiz(@PathVariable("quizId") long quizId, @RequestBody Map<String,Object> mapList) throws  ValidationException {

            Quiz quiz = quizService.findByID(quizId);

// code ngu vai lon, ban co 100 thuoc tinh thi sao?
        // thi tinh cach khac ahihi do ngok
        //dit me may ngu  vl
        for(Map.Entry<String, Object> entry : mapList.entrySet())
        {

            if (entry.getKey().equals("quizName"))
            {
                quiz.setQuizName(entry.getValue().toString());

            }
            if (entry.getKey().equals("qizDescription"))
            {
                quiz.setQizDescription(entry.getValue().toString());

            }
            if(entry.getKey().equals("topicId"))
            {
                quiz.setTopic(topicService.findByID(Long.parseLong( entry.getValue().toString())));

            }
        }
        quizService.SaveaQuiz(quiz);
        return new ResponseEntity<>(quiz,HttpStatus.OK);


    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("{quizId}")
    public ResponseEntity DeleteuserByuserId(@PathVariable("quizId") long quizId) {
        return quizService.deleteById(quizId);

    }

    // question arena
    @GetMapping
    @RequestMapping("/{id}/questions")
    public ResponseEntity<List<Question>> getAllQuestionFromQuiz(@PathVariable Long id){
        Quiz quiz = quizService.findByID(id);
        List<Question> questionList = quiz.getQuestions();
        return new ResponseEntity<>(questionList, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Secured("ROLE_ADMIN")
    @PostMapping("/{id}/questions")
    public Question create(@PathVariable long id,@RequestBody Question question){
        Quiz quiz = quizService.findByID(id);
        question.setQuiz(quiz);
        questionService.create(question);
        //quiz.getQuestions().add(question);
        return question;
    }


    @PostMapping("/{id}/submit")
    public ResponseEntity<MarkResolverResponse> handleMarkCal(@PathVariable long id, @RequestBody MarkResolverRequest resolverRequest,
                                                              @CurrentSecurityContext SecurityContext context) throws InvalidClassException, InvalidPropertiesFormatException {
        if(resolverRequest.getUserSubmission().isEmpty()){
            throw new InvalidPropertiesFormatException("User submission is empty, Make sure you answer as least 1 question");
        }
        Quiz currentQuiz  = quizService.findByID(id);

        int numCorrect = 0;
        int numFalse = 0;
        int numUndone = currentQuiz.getQuestions().size();
        int totalPoint=0;
        HashMap<Long,String> userSubmission = resolverRequest.getUserSubmission();
        System.out.println(userSubmission.isEmpty());
        for (Long questionID:  userSubmission.keySet()) {
            System.out.println(questionID);
            String userAnswer = userSubmission.get(questionID).toString();
            String correctAns = questionService.findByID(questionID).getQuestionCorrectAnswer();
            System.out.println("User attempt:"+ userAnswer );
            System.out.println("Correct ans:" + correctAns);
            if(userAnswer.isBlank()){
                throw new InvalidPropertiesFormatException("User submission for questionID: " +questionID + " is blank" );
            }
            if (correctAns.equalsIgnoreCase(userAnswer)){
                numCorrect ++;
                totalPoint+= questionService.findByID(questionID).getQuestionPoint();
            }
            else
                numFalse++;
            numUndone--;
        }
       UserDetails currentUserDetails = (UserDetails)context.getAuthentication().getPrincipal();
       User user = userService.findByID(accountService.findByUserName(currentUserDetails.getUsername()).getUser().getUserId());
       UserQuiz userQuiz = new UserQuiz(currentQuiz,user,totalPoint);
           user.setPoint(user.getPoint()+totalPoint);
           userService.update(user);
           System.out.println("Update point : " + totalPoint);

       userQuizService.create(userQuiz);
        return new ResponseEntity<>(new MarkResolverResponse(numCorrect,numFalse,numUndone,totalPoint),HttpStatus.OK);

    }

}
