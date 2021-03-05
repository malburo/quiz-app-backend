package com.example.Quiz.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AccountAPI {
    @Autowired
    AccountService accountService;

}
