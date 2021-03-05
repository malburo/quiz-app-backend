package com.example.Quiz;

import com.example.Quiz.Models.Account;
import com.example.Quiz.Models.Topic;
import com.example.Quiz.Models.User;
import com.example.Quiz.Repository.AccountRepository;
import com.example.Quiz.Repository.TopicRepository;
import com.example.Quiz.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.Quiz.Repository")
public class QuizApplication {
 	static final Logger log = LoggerFactory.getLogger(QuizApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}
	@Autowired
	UserRepository userRepository;

	@Autowired
	AccountRepository repository;

	@Autowired
	TopicRepository topicRepository;

//	@Bean
//	public CommandLineRunner deme(){
//		return (args) -> {
//			User user =new User();
//			Topic topic = new Topic();
//			Topic topic1 = new Topic();
//			Account account = new Account();
//			account.setUserName("username");
//			account.setPassword("password");
//			user.setFullName("Test user");
//			account.setUser(user);
//			account.setRole("user");
//			topic.setTopicName("topictest");
//			topic.setUser(user);
//			topic.setTopicDescription("desc of topic ");
//			topic1.setTopicName("topictest1");
//			topic1.setUser(user);
//			topic1.setTopicDescription("desc of topic1");
//
//
//			userRepository.saveAndFlush(user);
//			repository.saveAndFlush(account);
//			topicRepository.saveAndFlush(topic);
//			topicRepository.saveAndFlush(topic1);
//
//
//		};
//
//	}

}
