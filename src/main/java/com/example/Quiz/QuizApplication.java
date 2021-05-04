package com.example.Quiz;

import com.example.Quiz.Models.Account;
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
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

	@Autowired
	UserRepository userRepository;

	@Autowired
	AccountRepository repository;

	@Autowired
	TopicRepository topicRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Bean
	public CommandLineRunner deme() {
		return (args) -> {

			if(repository.findByUsername("username1")==null){
				Account account = new Account();
				account.setUsername("username1");
				account.setPassword(bCryptPasswordEncoder.encode("password"));
				account.setRole("ADMIN");
				account.setBlocked(false);
				User user = new User();
				user.setFullName("abc");
				user.setAccount(account);
//				user.setLevel(1);
				user.setPoint(100);
				repository.saveAndFlush(account);


			}


		};
	}

}
