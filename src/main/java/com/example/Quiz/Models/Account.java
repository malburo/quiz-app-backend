package com.example.Quiz.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity()
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private long accountId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "Account",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    //@JoinColumn(name = "user_id", referencedColumnName = "user_id", foreignKey = @ForeignKey(name = "user_id_fk"))


    private User user;

    @Column(name = "role")
    private String role;
    @Column(name = "blocked")
    private boolean blocked;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

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
