package com.example.Quiz.API;

import com.example.Quiz.Models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;


@RestController
@RequestMapping("/")
public class AccountAPI {
    @Autowired
    AccountService accountService;
    @PostMapping("/test")
    public HashMap Register ( @RequestBody Account account )
    {
         return accountService.register(account);
         // regiser
    }
    @GetMapping ("/test2")
    public String test ()
    {

        return "helo";
    }


}
