package com.example.Quiz.API;


import com.example.Quiz.JWT.JwtResponse;
import com.example.Quiz.Models.Account;
import com.example.Quiz.Models.User;
import com.example.Quiz.Quick_Pojo_Class.ErrorMessage;
import com.example.Quiz.Quick_Pojo_Class.Registerinfo;
import com.example.Quiz.Quick_Pojo_Class.changePassword;
import com.example.Quiz.Repository.AccountRepository;
import com.example.Quiz.Repository.UserRepository;
import com.example.Quiz.Ultility.JWTUtility;
import com.example.Quiz.Ultility.JavaMailUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;

import java.util.List;
import java.util.TimeZone;

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


    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findByID(Long id) {
        return accountRepository.getOne(id);
    }


    public Account findByUserName(String username )
    {
        return  accountRepository.findByUsername(username); }

    public Account create(Account user) {
        return accountRepository.saveAndFlush(user);
    }

    public Account update(Account user) {
        return accountRepository.saveAndFlush(user);
    }




    public ResponseEntity register(Registerinfo registerinfo) // dang ky tai khoan
    {

        String error = " existed";
            if (accountRepository.findByUsername(registerinfo.getUsername()) != null) {

                return new ResponseEntity(new ErrorMessage("403", "username"+ error), HttpStatus.NOT_FOUND);

            }
            if (accountRepository.GetAccountByEmail(registerinfo.getEmail()) != null) {

                return new ResponseEntity(new ErrorMessage("403", "email"+ error), HttpStatus.NOT_FOUND);

            }

            else {

                Account account = new Account();
                account.setUsername(registerinfo.getUsername());
                account.setPassword(bCryptPasswordEncoder.encode(registerinfo.getPassword()));
                account.setRole("USER");
                account.setBlocked(false);
                accountRepository.saveAndFlush(account);
                User user_DB = new User();
                user_DB.setEmail(registerinfo.getEmail());
                user_DB.setFullName(registerinfo.getFullName());
                user_DB.setPoint(0);
                user_DB.setLearningStreaks(1);
//                user_DB.setLevel(0);
                user_DB.setAccount(account);
                userRepository.save(user_DB);
                UserDetails user = userDetailsService.loadUserByUsername(account.getUsername());
                JwtResponse jwtResponse = new JwtResponse(jwtUtility.generateToken(user));
                return new ResponseEntity(jwtResponse, HttpStatus.OK); // RESPONE STATUS
            }
        }



    }


    public ResponseEntity changepassword(changePassword changePassword, long userId)
    {
        Account account = accountRepository.findByUserId(userId);
        if (bCryptPasswordEncoder.matches(changePassword.getOldpassword(), account.getPassword())) {
            account.setPassword(bCryptPasswordEncoder.encode(changePassword.getNewpassword())); //
            accountRepository.save(account);
            return new ResponseEntity("change password successed", HttpStatus.OK);
        } else {
            return new ResponseEntity(new ErrorMessage("404","password did'nt correct"), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity GenerateMail(String email) throws IOException, MessagingException// tao gmail va gui
    {
        Account account = accountRepository.GetAccountByEmail(email);
        if (account == null)
            return new ResponseEntity(new ErrorMessage("404","Email is not registered "), HttpStatus.NOT_FOUND);
        else
            javaMailUtility.sendmail(email, account.getUsername(), jwtUtility.generateToken10min(account.getUsername()));
        return new ResponseEntity("email was sended", HttpStatus.OK);


    }

    public ResponseEntity Updatepassword(String username, String entity) {
        Account account = accountRepository.findByUsername(username);
        account.setPassword(bCryptPasswordEncoder.encode(entity));
        accountRepository.saveAndFlush(account);
        return new ResponseEntity("Password updated", HttpStatus.OK);
    }
    //    protected ResponseEntity<String> Exceptionregister ()
//    {
//        return new ResponseEntity(HttpStatus.FORBIDDEN);
//    }
    public void delete(long id) {

        accountRepository.deleteById((long) id);
    }

    public void handleOnlineStreak(String username) {
        final Long MILISEC_OF_A_DAY =  86400000l;
        Account account = findByUserName(username);
        int streak = account.getUser().getLearningStreaks();
        System.out.println("current streak:" + streak);
        Date date = account.getLatestLogin();
        Date currentTime = new Date();
        Long currentTimeInMili = currentTime.getTime();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        calendar.setTime(date);
        int year  = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.set(year,month,day,23,59,59);
        Date END_OF_DAY = calendar.getTime();
        Long end_of_day_in_mili = END_OF_DAY.getTime();
        float calculate = (float) (currentTimeInMili-end_of_day_in_mili)/MILISEC_OF_A_DAY;
        if(calculate >1){
            System.out.println("the streak end");
           streak=1;
        }else if(calculate > 0){
            System.out.println("the streak continue");
            streak++;
        }else
            System.out.println("login in same day");
        System.out.println("current streak:" + streak);
        account.setLatestLogin(currentTime);
        account.getUser().setLearningStreaks(streak);
        accountRepository.saveAndFlush(account);
        userRepository.saveAndFlush(account.getUser());
    }

    public String login (String username ,String password)

    {

        Account account = accountRepository.findByUsername(username);

        if (account==null)
            return "username not found";
        if (account.isBlocked())
            return "account is blocked";
        else
        {
           if( bCryptPasswordEncoder.matches(password,account.getPassword()) )
               return "successed";
           return  "Wrong password";



        }


    }

}
