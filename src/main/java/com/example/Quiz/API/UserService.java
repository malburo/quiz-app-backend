package com.example.Quiz.API;

import com.example.Quiz.Models.Account;
import com.example.Quiz.Models.User;
import com.example.Quiz.Quick_Pojo_Class.Message;
import com.example.Quiz.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findByID(Long id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No User with id:" + id));
    }

    public User create(User user){
        return repository.saveAndFlush(user);
    }

    public HttpEntity update(User user)  { // cap nhap thong tin nguoi dung
        try {

            repository.saveAndFlush(user);
            return new ResponseEntity(new Message("Update completed",""), HttpStatus.OK);
        }
        catch (Exception ex)
        {
            return new ResponseEntity(new Message("Update Error"), HttpStatus.OK);
        }
//        return repository.saveAndFlush(user);
    }
    public  User Getuser (String userName)
    {
        return  repository.GetUserByUserName(userName); // lay thong tin nguoi dung

    }
    public void delete(long id) {
        repository.deleteById((long) id) ;
    }





}
