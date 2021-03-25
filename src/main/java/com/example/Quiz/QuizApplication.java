package com.example.Quiz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(
		prePostEnabled = true,
		securedEnabled = true,
		jsr250Enabled = true)
@EnableJpaRepositories("com.example.Quiz.Repository")
public class QuizApplication {
 	static final Logger log = LoggerFactory.getLogger(QuizApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}
	/*
	@Autowired
	UserRepository userRepository;

	@Autowired
	AccountRepository repository;

	@Autowired
	TopicRepository topicRepository;

	@Bean
	public CommandLineRunner deme(){
		return (args) -> {

			Topic topic = new Topic();
			Topic topic1 = new Topic();
			Account account = new Account();
			account.setUserName("username");
			account.setPassword("password");
			//user.setFullName("Test user");
			//account.setUser();
			account.setRole("user");
			account.setBlocked(false);

			//topic.setTopicName("topictest");
			//topic.setUser(user);
			//topic.setTopicDescription("desc of topic ");
			//topic1.setTopicName("topictest1");
			//topic1.setUser(user);
			//topic1.setTopicDescription("desc of topic1");


			//userRepository.saveAndFlush(user);
			repository.saveAndFlush(account);
			//topicRepository.saveAndFlush(topic);
			//topicRepository.saveAndFlush(topic1);
			// tam thoi an may cai kia de test register


		};

	}
	*/

}
