package com.example.Quiz.Repository;

import com.example.Quiz.Models.Account;
import com.example.Quiz.Models.User;
import com.example.Quiz.Quick_Pojo_Class.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query (value = "select user_id from user_info u where u.user_name = ?1", nativeQuery = true)
    Userinfo checkerQuery (String userName); // kiem tra co ton tai account thoa man request ko
}
