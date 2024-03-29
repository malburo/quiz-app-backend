package com.example.Quiz.API;

import com.example.Quiz.Models.Account;
import com.example.Quiz.Models.User;
import com.example.Quiz.Quick_Pojo_Class.ErrorMessage;
import com.example.Quiz.Repository.AccountRepository;
import com.example.Quiz.Repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository repository;
    @Autowired
    AccountRepository accountRepository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findByID(Long id) {


        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No such user with id:" + id));

    }

    public User create(User user) {
        return repository.saveAndFlush(user);
    }
// cho phep sdt trung
    public ResponseEntity update(User user,long userId, String username) { // cap nhap thong tin nguoi dung

        if (checkpermission(username,userId))
            return new ResponseEntity(new ErrorMessage("400", "user don't have permission to change info"),HttpStatus.BAD_REQUEST);

        User existuser = repository.findById(userId).orElseThrow(() -> new EntityNotFoundException("No such user with id:" + userId));
        if (accountRepository.GetAccountByEmail(user.getEmail())!=null)
                return new ResponseEntity(new ErrorMessage("400", "email exited"),HttpStatus.BAD_REQUEST);


            BeanUtils.copyProperties(user,existuser,"userId","account");
            repository.save(existuser);
            return new ResponseEntity(existuser, HttpStatus.OK);
    }
    public ResponseEntity Blockuser (long userId,boolean blocked)
    {
       Account account = accountRepository.findByUserId(userId);
       account.setBlocked(blocked);
       accountRepository.save(account);
        return new ResponseEntity("account was blocked", HttpStatus.OK);
    }
    public ResponseEntity changeurlImange(long userId,String urlImange,String username)
    {
        if (checkpermission(username,userId))
            return new ResponseEntity(new ErrorMessage("400", "user don't have permission to change info"),HttpStatus.BAD_REQUEST);
        User user = repository.getOne(userId);
        user.setImageUrl(urlImange);
        repository.save(user);
        return new ResponseEntity(user, HttpStatus.OK);


    }
    public boolean  checkpermission (String username,long userId)
    {
        if (!accountRepository.findByUserId(userId).getUsername().equals(username))
            return  true;
        return false;
    }


    public Optional<User> Getuser(String username) throws Throwable {
        Optional<User> user = Optional.ofNullable(repository.GetUserByUserName(username)
                .orElseThrow(() -> new EntityNotFoundException("No such user with username:" + username)));

        return user; // lay thong tin nguoi dung

    }

    public void delete(long id) {
        repository.deleteById((long) id);
        accountRepository.deleteById(accountRepository.GetAccountIdByUserId(id));
    }


}
