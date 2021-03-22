package com.example.Quiz.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity()
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "account")
public class Account {



    //@Column(name = "account_id")
    //private long accountId; // bo id vi no ngu vai lon du ma
    @Id
    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "account",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private User user;
    //@JoinColumn(name = "user_id", referencedColumnName = "user_id", foreignKey = @ForeignKey(name = "user_id_fk"))


    @Column(name = "role",nullable =false)
    private String role;
    @Column(name = "blocked",nullable = false)
    private boolean blocked;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//    public long getAccountId() {
//        return accountId;
//    }

//    public void setAccountId(long accountId) {
//        this.accountId = accountId;
//    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

}
