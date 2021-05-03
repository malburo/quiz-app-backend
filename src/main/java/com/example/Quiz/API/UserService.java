package com.example.Quiz.API;

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

    public ResponseEntity update(User user,long userId) { // cap nhap thong tin nguoi dung
        User existuser = repository.getOne(userId);
        if (existuser==null)
            return new ResponseEntity(new ErrorMessage("400", "no user with id: "+ userId), HttpStatus.CONFLICT);
        try {

            BeanUtils.copyProperties(user,existuser,"userId","account");
            repository.save(existuser);
            return new ResponseEntity("update completed", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(new ErrorMessage("409", "update fail for some reason"), HttpStatus.CONFLICT);
        }
//        return repository.saveAndFlush(user);
    }

    public User Getuser(String userName) {
        return repository.GetUserByUserName(userName); // lay thong tin nguoi dung

    }

    public void delete(long id) {
        repository.deleteById((long) id);
        accountRepository.deleteById(accountRepository.GetAccountIdByUserId(id));
    }


}
