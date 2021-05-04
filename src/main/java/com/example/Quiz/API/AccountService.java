package com.example.Quiz.API;


import com.example.Quiz.Models.User;
import com.example.Quiz.Quick_Pojo_Class.ErrorMessage;
import com.example.Quiz.JWT.JwtResponse;
import com.example.Quiz.Models.Account;
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
import java.util.ArrayList;
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
        boolean HasU_E = false;
        if (registerinfo.getPassword() ==null || registerinfo.getFullName()== null || registerinfo.getUsername() ==null
                || registerinfo.getEmail() ==null
        )
        {
            error="wrong format, valid format : username, password, email, fullName";
            return new ResponseEntity(new ErrorMessage("400",error), HttpStatus.NOT_FOUND);
        }
        else {

            if (accountRepository.findByUsername(registerinfo.getUsername()) != null) {

                error = "username " + error;
                HasU_E = true;
            }
            if (accountRepository.GetAccountByEmail(registerinfo.getEmail()) != null) {

                error = "email " + error;
                HasU_E = true;
            }

            if (HasU_E == true)
                return new ResponseEntity(new ErrorMessage("403", error), HttpStatus.NOT_FOUND);
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
    public String login (String username ,String password)

    {
        String error = "error :";
        Account account = accountRepository.findByUsername(username);
        if (account.isBlocked())
            return error+"account is blocked";
        if (account==null)
            return error+"username not found";
        else
        {
           if( bCryptPasswordEncoder.matches(password,account.getPassword()) )
               return "successed";
           return error+ "password invalid";



        }


    }

}
