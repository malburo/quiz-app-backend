package com.example.Quiz.API;

import com.example.Quiz.Models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class AccountAPI {
    @Autowired
    AccountService accountService;



}
