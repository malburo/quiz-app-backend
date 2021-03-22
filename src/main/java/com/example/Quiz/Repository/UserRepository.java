package com.example.Quiz.Repository;

import com.example.Quiz.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query (value = "select * from user_info u where u.user_name = ?1", nativeQuery = true)
    User GetUserByUserName (String userName); // kiem tra co ton tai account thoa man request ko
}
