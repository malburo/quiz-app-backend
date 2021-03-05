package com.example.Quiz.API;

import com.example.Quiz.Models.Account;
import com.example.Quiz.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    public Account findByID(Long id){
        return  accountRepository.getOne(id);
    }

    public Account create(Account user){
        return accountRepository.saveAndFlush(user);
    }

    public Account update(Account user){
        return accountRepository.saveAndFlush(user);
    }

    public HashMap register(Account account)
    {
        HashMap<String,String> status = new HashMap<>();

        if( accountRepository.findByUserName(account.getUserName()) !=null) {
            status.put("Status", "Failed");
            return status;
        }
        else {
        String oldpassword = account.getPassword();
        String passwordencoded =bCryptPasswordEncoder.encode(oldpassword);
        account.setPassword(passwordencoded);
        account.setRole("User");
        account.setBlocked(false);
        accountRepository.save(account);
        status.put("Status", "Successed");
        return status;
            // tam thoi return cai nay, dang tim cach return
           //  http errors
        }
    }

    public void delete(int id){
        accountRepository.deleteById((long) id);
    }
}
