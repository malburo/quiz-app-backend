package com.example.Quiz.Repository;

import com.example.Quiz.Models.Account;
import com.example.Quiz.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
        Account findByUserName(String Username);

}
