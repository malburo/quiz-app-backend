package com.example.Quiz;

import com.example.Quiz.API.UserService;
import com.example.Quiz.Models.Account;
import com.example.Quiz.Models.User;
import com.example.Quiz.Repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	@Bean
	public CommandLineRunner demo(UserService service, AccountRepository accountRepository){
		return (args) -> {
			Account account = new Account();
			account.setUserName("usernamee");
			account.setPassword("passwordd");
			account.setRole("admin");
			User user = service.findByID(2L);
			account.setUser(user);
			//log.info(user.getFullName() + " " + user.getUserId()) ;
			accountRepository.saveAndFlush(account);

		};
	}
}
