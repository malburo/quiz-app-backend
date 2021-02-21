package com.example.Quiz;

import com.example.Quiz.API.UserService;
import com.example.Quiz.Models.User;
import com.example.Quiz.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest()
class QuizApplicationTests {

	@MockBean
	private  UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Test
	public void testFindAll() throws  Exception {
		List<User> users = userService.findAll();
		assertTrue(users.size() >0);
	}
	@Test
	void contextLoads() {
	}

}
