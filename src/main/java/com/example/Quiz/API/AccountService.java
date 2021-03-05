package com.example.Quiz.API;

import com.example.Quiz.Models.Account;
import com.example.Quiz.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountService {
    @Autowired
    AccountRepository repository;

    public List<Account> findAll(){
        return repository.findAll();
    }

    public Account findByID(Long id){
        return  repository.getOne(id);
    }

    public Account create(Account user){
        return repository.saveAndFlush(user);
    }

    public Account update(Account user){
        return repository.saveAndFlush(user);
    }
    public boolean register( String Username, String Password)
    {

        return  true;

    }

    public void delete(int id){
        repository.deleteById((long) id);
    }
}
