package com.example.Quiz.Config;


import com.example.Quiz.API.AccountService;
import com.example.Quiz.API.MyCustomUserDetailService;
import com.example.Quiz.API.UserService;
import com.example.Quiz.Models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@EnableScheduling
@Configuration
public class ScheduleConfig {
    @Autowired
    AccountService accountService;
    @Autowired
    MyCustomUserDetailService myCustomUserDetailService;

    @Autowired
    UserService userService;
    @Scheduled(cron = "0 59 23 * * ?")
    public void newDaySchedule(){
        Date current = new Date();
        for (Account account: accountService.findAll()) {
            System.out.println(account.getUsername());
           accountService.handleOnlineStreak(account.getUsername());
        }
    }
}
