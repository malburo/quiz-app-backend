package com.example.Quiz.Models;


import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
//    @MockBean
//    private  UserRepository userRepository;
//
//    @Autowired
//    private UserService userService;
////    @Test
////    public void testFind() throws Exception {
////        User user = userRepository.getOne(1L);
////        assertNotNull(user);
////    }
//    @Test
//    public void testFindAll() throws  Exception {
//        List<User> users = userService.findAll();
//        assertTrue(users.size() >0);
//    }
//
////    @Test
////    public void testCreateFindDelete() throws  Exception {
////        User user = new User();
////        user.setFullName("test");
////        user.setEmail("abc@gmail.com");
////        user.setImageUrl(null);
////        user.setPhoneNumber("1234567890");
////        user.setLearningStreaks(0);
////        user.setPoint(0);
////
////        userRepository.saveAndFlush(user);
////
////        User testUser = userRepository.getOne(2L);
////        assertEquals("test",user.getFullName());
////
////        userRepository.deleteById((long)testUser.getUserId());
////    }
}
