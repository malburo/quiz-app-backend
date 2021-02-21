package com.example.Quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class QuizApplication {
 // main
	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}

}
