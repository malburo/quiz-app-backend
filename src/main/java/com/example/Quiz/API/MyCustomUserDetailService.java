package com.example.Quiz.API;

import com.example.Quiz.Models.Account;
import com.example.Quiz.Models.MyCustomUserDetail;
import com.example.Quiz.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyCustomUserDetailService implements UserDetailsService {

    @Autowired
    AccountRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Account account = repository.findByUserName(s);
        if(account != null)
            return new MyCustomUserDetail(account);
        else throw new UsernameNotFoundException("User not found with username: " + account.getUserName());

    }
}
