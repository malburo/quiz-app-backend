package com.example.Quiz.Models;

import javax.persistence.*;

@Entity
@Table(name = "user_info")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    @Column(name = "user_full_name")
    private String fullName;
    @Column(name = "user_email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "user_level")
    private int level;
    @Column(name = "user_point")
    private double point;
    @Column(name = "user_image_url")
    private String imageUrl;
    @Column(name = "learning_streaks")
    private int learningStreaks;


    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Account account ;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public long getUserId() {
        return user_id;
    }

    public void setUserId(long userId) {
        this.user_id = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getLearningStreaks() {
        return learningStreaks;
    }

    public void setLearningStreaks(int learningStreaks) {
        this.learningStreaks = learningStreaks;
    }


}
