package com.example.Quiz.Models;

import com.example.Quiz.API.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void testFind() throws Exception {
        User user = userService.findByID(1L);
        assertNotNull(user);
    }
    @Test
    public void testFindAll() throws  Exception {
        List<User> users = userService.findAll();
        assertTrue(users.size() >0);
    }

    @Test
    public void testCreateFindDelete() throws  Exception {
        User user = new User();
        user.setFullName("test");
        user.setEmail("abc@gmail.com");
        user.setImageUrl(null);
        user.setPhoneNumber("1234567890");
        user.setLearningStreaks(0);
        user.setPoint(0);

        userService.create(user);

        User testUser = userService.findByID((long)user.getUserId());
        assertEquals("test",user.getFullName());

        userService.delete((int) testUser.getUserId());
    }
}
