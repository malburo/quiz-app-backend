package com.example.Quiz.Models;

import com.example.Quiz.API.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountTest {

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
        account.setRole("admin");
        accountService.create(account);

        Account account1 = accountService.findByID((long)account.getAccountId());
        assertEquals("abc",account.getUserName());

        accountService.delete((int) account1.getAccountId());
    }
}
