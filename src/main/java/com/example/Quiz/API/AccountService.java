package com.example.Quiz.API;

import com.example.Quiz.Models.User;
import com.example.Quiz.Quick_Pojo_Class.Accountregister;
import com.example.Quiz.Quick_Pojo_Class.Message;
import com.example.Quiz.JWT.JwtResponse;
import com.example.Quiz.Models.Account;
import com.example.Quiz.Repository.AccountRepository;
import com.example.Quiz.Quick_Pojo_Class.changePassword;
import com.example.Quiz.Repository.UserRepository;
import com.example.Quiz.Ultility.JWTUtility;
import com.example.Quiz.Ultility.JavaMailUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
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
    @Autowired
    UserRepository userRepository;
    @Autowired
    JavaMailUtility javaMailUtility;


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


    public ResponseEntity register(Accountregister accountregister) // dang ky tai khoan
    {
        if( accountRepository.findByUserName(accountregister.getUsername()) !=null) {

            return new ResponseEntity( new Message("Account exist"),HttpStatus.FORBIDDEN); // RESPONE STATUS
        }
        else {
            String Password_temp = accountregister.getPassword();
            String passwordencoded = bCryptPasswordEncoder.encode(Password_temp);
            Account account = new Account();
            account.setUserName(accountregister.getUsername());
            account.setPassword(passwordencoded);
            account.setRole("USER");
            account.setBlocked(false);
            accountRepository.saveAndFlush(account);
            User user_DB = new User();
            user_DB.setPoint(0);
            user_DB.setLevel(0);
            user_DB.setAccount(account);
            userRepository.save(user_DB);
            UserDetails user = userDetailsService.loadUserByUsername(accountregister.getUsername());
            JwtResponse jwtResponse = new JwtResponse(jwtUtility.generateToken(user));
            return new ResponseEntity(jwtResponse,HttpStatus.OK); // RESPONE STATUS
        }
    }


    public ResponseEntity  changepassword (changePassword changePassword, long userId)
    {
              Account account = accountRepository.findByUserId(userId);
              if( bCryptPasswordEncoder.matches(changePassword.getOldpassword(),account.getPassword()))
           {
               account.setPassword(bCryptPasswordEncoder.encode(changePassword.getNewpassword())); //
               accountRepository.save(account);
                return  new   ResponseEntity( new Message("change password successed",""),HttpStatus.OK);
            }
           else {
           return  new ResponseEntity(new Message("password doesn't match"),HttpStatus.BAD_REQUEST); }
    }


    public ResponseEntity GenerateMail (String email) throws IOException, MessagingException
    // tao gmail va gui
    {
        Account account = accountRepository.GetAccountByEmail(email);
        if (account==null)
            return new ResponseEntity( new Message("Email chưa được đăng ký","Email error"),HttpStatus.FORBIDDEN);
        else
            javaMailUtility.sendmail(email,account.getUserName(),jwtUtility.generateToken10min(account.getUserName()));
        return new ResponseEntity( new Message("","Mail sended"),HttpStatus.OK);


    }
    public ResponseEntity Updatepassword (String username,String entity)
    {
        Account account = accountRepository.findByUserName(username);
        account.setPassword(bCryptPasswordEncoder.encode(entity));
        accountRepository.saveAndFlush(account);
        return new ResponseEntity( new Message("","Password updated"),HttpStatus.OK);
    }

//    protected ResponseEntity<String> Exceptionregister ()
//    {
//        return new ResponseEntity(HttpStatus.FORBIDDEN);
//    }
    public void delete(long id){
        accountRepository.deleteById((long) id);
    }
}
