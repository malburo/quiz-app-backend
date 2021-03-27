package com.example.Quiz.API;

import com.example.Quiz.Models.Quiz;
import com.example.Quiz.Models.Topic;
import com.example.Quiz.Models.User;
import com.example.Quiz.Quick_Pojo_Class.ErrorMessage;
import com.example.Quiz.Repository.QuizRepository;
import com.example.Quiz.Ultility.JWTUtility;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicApi {

    @Autowired
    private TopicService topicService;
    @Autowired
    private UserService userService;
    @Autowired
    private JWTUtility jwtUtility;
    @Autowired
    private AccountService accountService;
    @Autowired
    QuizService quizService;

    @GetMapping
    public ResponseEntity<List<Topic>> list() {
        return new ResponseEntity<>(topicService.findAll(),HttpStatus.OK);
    }



    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<Topic> get(@PathVariable Long id ) throws EntityNotFoundException {
        Topic topic = topicService.findByID(id);
        return new ResponseEntity<>(topic,HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Secured("ROLE_ADMIN")
    public Topic create(@RequestBody final Topic topic,@CurrentSecurityContext SecurityContext context) throws ValidationException {

        if (topic.getTopicName() == null || topic.getTopicDescription() == null )
            throw new ValidationException("Missing information for topic");
        UserDetails currentUserDetails = (UserDetails) context.getAuthentication().getPrincipal();
        User user = userService.findByID(accountService.findByUserName(currentUserDetails.getUsername()).getUser().getUserId());
        topic.setUser(user);
        return topicService.create(topic);
    }
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Long id){
            topicService.deleteById(id);
            return new ResponseEntity<>("Successfully deleted topic with id:"+id,HttpStatus.OK);
    }


    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Topic> update(@PathVariable Long id, @RequestBody Topic topic) throws ValidationException {
        Topic existTopic  = topicService.findByID(id);
        if (topic.getTopicName() == null || topic.getTopicDescription() == null )
            throw new ValidationException("Missing information for topic");
        BeanUtils.copyProperties(topic,existTopic,"topicId","user");
        return new ResponseEntity<>(topicService.update(existTopic),HttpStatus.OK);
    }
    @Secured("ROLE_ADMIN")
    @GetMapping ("/{topicId}/quizzes") // lay quizzes cua 1 topic
    public ResponseEntity GetallQuizz (@PathVariable("topicId") long topicId)
    {

        if(quizService.getQuizzesbytopicId(topicId).isEmpty())
        {
            return new ResponseEntity(new ErrorMessage("404","no quizzes with id: "+topicId),HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<Quiz>>(quizService.getQuizzesbytopicId(topicId),HttpStatus.OK);

    }
    @Secured("ROLE_ADMIN")
    @PostMapping ("/{topicId}/quizzes")
    public ResponseEntity CreateQuiz (@PathVariable("topicId") long topicId ,@RequestBody Quiz quiz)
    {
        try {

            quiz.setTopic(topicService.findByID(topicId));
            quizService.SaveaQuiz(quiz);
            return new ResponseEntity("created Quiz ",HttpStatus.OK);
        }
        catch (Exception ex)
        {
            throw new EntityNotFoundException("no topic with id: "+topicId);
        }
    }


}
