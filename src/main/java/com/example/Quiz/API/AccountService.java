package com.example.Quiz.API;

import com.example.Quiz.Quick_Pojo_Class.Message;
import com.example.Quiz.JWT.JwtResponse;
import com.example.Quiz.Models.Account;
import com.example.Quiz.Repository.AccountRepository;
import com.example.Quiz.Quick_Pojo_Class.changePassword;
import com.example.Quiz.Ultility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    JWTUtility jwtUtility;

    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    public Account findByID(Long id){
        return  accountRepository.getOne(id);
    }

    public Account findByUserName(String username ){
        return  accountRepository.findByUserName(username);
    }

    public Account create(Account user){
        return accountRepository.saveAndFlush(user);
    }

    public Account update(Account user){
        return accountRepository.saveAndFlush(user);
    }

    public ResponseEntity register(Account account) // dang ky tai khoan
    {
        if( accountRepository.findByUserName(account.getUserName()) !=null) {

            return new ResponseEntity( new Message("Account exist"),HttpStatus.FORBIDDEN); // RESPONE STATUS
        }
        else {

            String oldpassword = account.getPassword();
            String passwordencoded = bCryptPasswordEncoder.encode(oldpassword);
            account.setPassword(passwordencoded);
            account.setRole("User");
            account.setBlocked(false);
            accountRepository.saveAndFlush(account);
            UserDetails user = userDetailsService.loadUserByUsername(account.getUserName());

            JwtResponse jwtResponse = new JwtResponse(jwtUtility.generateToken(user));
            return new ResponseEntity(jwtResponse,HttpStatus.OK); // RESPONE STATUS
        }


    }
    public ResponseEntity  changepassword (changePassword changePassword, String Username)
    {

              Account account = accountRepository.findByUserName(Username);

              if( bCryptPasswordEncoder.matches(changePassword.getOldpassword(),account.getPassword()))

           {
               account.setPassword(bCryptPasswordEncoder.encode(changePassword.getNewpassword())); //
               accountRepository.save(account);
                return  new   ResponseEntity( new Message("change password successed"),HttpStatus.OK);

            }
           else {
           return  new ResponseEntity(new Message("password doesn't match"),HttpStatus.BAD_REQUEST); }






    }


    protected ResponseEntity<String> Exceptionregister ()
    {
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    public void delete(int id){
        accountRepository.deleteById((long) id);
    }
}
