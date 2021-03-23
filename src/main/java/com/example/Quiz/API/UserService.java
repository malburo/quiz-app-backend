package com.example.Quiz.API;

import com.example.Quiz.Models.User;
import com.example.Quiz.Quick_Pojo_Class.Message;
import com.example.Quiz.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findByID(Long id){
        return repository.getOne(id);
    }

    public User create(User user){
        return repository.saveAndFlush(user);
    }

    public User update(User user){
        return repository.saveAndFlush(user);
    }
//    public User Getuser (String userName)
//    { User user= repository.findByUsername(userName);
//        return user ;
//    }

    public void delete(int id){
        repository.deleteById((long) id);
    }
    public  User Getuser (String userName)
    {
    return  repository.GetUserByUserName(userName);

    }
    public ResponseEntity PostUser ( User user)
    {
        try {
            repository.saveAndFlush(user);
            return new ResponseEntity(new Message("Update completed"), HttpStatus.OK);
        }
        catch (Exception ex)
        {
            return new ResponseEntity(new Message("Update Error"), HttpStatus.OK);
        }


    }



}
