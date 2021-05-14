package com.example.Quiz.Repository;

import com.example.Quiz.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query ( "select u from User u where u.account.username = ?1")
    Optional<User> GetUserByUserName (String userName); // kiem tra co ton tai account thoa man request ko



}
