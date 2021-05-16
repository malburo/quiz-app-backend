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
    UserRepository userRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void newDaySchedule(){
        for (Account account: accountService.findAll()) {
            System.out.println(account.getUsername());
           float cal= accountService.doCalculate(account.getLatestLogin(),new Date());
           if(cal > 1){
              User user =  account.getUser();
              user.setLearningStreaks(0);
              userRepository.saveAndFlush(user);
           }
        }
    }
}
