package com.example.Quiz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.Quiz.Repository")
public class QuizApplication {
 	static final Logger log = LoggerFactory.getLogger(QuizApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}

}
