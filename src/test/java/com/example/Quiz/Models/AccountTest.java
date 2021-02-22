package com.example.Quiz.Models;

import com.example.Quiz.API.AccountService;
import com.example.Quiz.Repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountTest {
    @MockBean
    private AccountRepository repository;

    @Autowired
    private AccountService accountService;


    @Test
    public void testFind() throws Exception {
        Account account = accountService.findByID(1L);
        assertNotNull(account);
    }
    @Test
    public void testFindAll() throws  Exception {
        List<Account> accounts = accountService.findAll();
        assertTrue(accounts.size() >0);
    }

    @Test
    public void testCreateFindDelete() throws  Exception {
        Account account = new Account();
        account.setUserName("abc");
        account.setPassword("abc");

        accountService.create(account);


    }
}
