package com.example.Quiz.API;

import com.example.Quiz.Models.Topic;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Topic create(@RequestBody final Topic topic) throws ValidationException {
        if (topic.getTopicName() == null || topic.getTopicDescription() == null || topic.getUser() == null)
            throw new ValidationException("Missing information for topic");
        userService.findByID(topic.getUser().getUserId());
        return topicService.create(topic);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Long id){
            topicService.deleteById(id);
            return new ResponseEntity<>("Successfully deleted topic with id:"+id,HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Topic> update(@PathVariable Long id, @RequestBody Topic topic) throws ValidationException {
        Topic existTopic  = topicService.findByID(id);
        if (topic.getTopicName() == null || topic.getTopicDescription() == null || topic.getUser() == null)
            throw new ValidationException("Missing information for topic");
        userService.findByID(topic.getUser().getUserId());
        BeanUtils.copyProperties(topic,existTopic,"topicId");
        return new ResponseEntity<>(topicService.update(existTopic),HttpStatus.OK);
    }
}
