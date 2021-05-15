package com.example.Quiz.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity()
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "account")
public class Account {

    @Id

    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @JsonIgnore
    @Column(name = "latest_login")
    private Date latestLogin;

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

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY) // khong hien password khi respone

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

    public Date getLatestLogin() {
        return latestLogin;
    }

    public void setLatestLogin(Date latestLogin) {
        this.latestLogin = latestLogin;
    }
}
