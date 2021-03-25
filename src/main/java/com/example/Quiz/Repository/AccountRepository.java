package com.example.Quiz.Repository;

import com.example.Quiz.Models.Account;
import com.example.Quiz.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
        Account findByUserName(String Username);
        @Query ("select a from Account a where a.user = ?1")
        Account GetAccountByUser (User user); // kiem tra co ton tai account thoa man request ko
        @Query("select a from Account a where a.user.email = ?1")
        Account  GetAccountByEmail(String email);
        @Query ( "select A from Account A where A.user.userId = ?1")
        Account findByUserId (long id);
        @Query ( "select accountId from Account a where a.user.userId = ?1")
        long GetAccountIdByUserId (long userId);




}
