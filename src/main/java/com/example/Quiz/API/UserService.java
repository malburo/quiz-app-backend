package com.example.Quiz.API;

import com.example.Quiz.Models.User;
import com.example.Quiz.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
//    public  boolean checker (String userName,Long userId)
//    {
//    User user = repository.checkerQuery(userId,userName);
//    if (user != null)
//    return true;
//    return  false;
//    }



}
