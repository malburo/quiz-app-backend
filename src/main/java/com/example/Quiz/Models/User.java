package com.example.Quiz.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "user_info")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;
    @Column(name = "user_full_name")// cho phep null
    private String fullName;
    @Column(name = "user_email")// cho phep null
    private String email;
    @Column(name = "phone_number") // cho phep null
    private String phoneNumber;
    @Column(name = "user_level", nullable = false)
     //finnal level
    @JsonProperty (access = JsonProperty.Access.READ_ONLY)
    private int level;
    // hello chinh json tu point sang totalPoint
    @Column(name = "user_point", nullable = false)
    @JsonProperty (access = JsonProperty.Access.READ_ONLY,value = "totalPoint")
    private double point;

    @Column(name = "user_image_url",nullable =true)
    @JsonProperty (access = JsonProperty.Access.READ_ONLY)

    private String imageUrl;
    @Column(name = "learning_streaks")
    @JsonIgnore
    private int learningStreaks;



    // Swaped , user  hold account
    @OneToOne
    @JoinColumn(name = "account_id",referencedColumnName = "account_id",foreignKey = @ForeignKey(name = "account_id_fk"))
    @JsonIgnoreProperties({"user"}) // oh my lord tranh infinite loop
    @JsonIgnore
    private Account account ;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Topic> topics;



    @OneToMany(mappedBy = "user",cascade =CascadeType.ALL)
    @JsonIgnore
    private List<UserQuiz> quizzesFinished;

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public List<UserQuiz> getQuizzesFinished() {
        return quizzesFinished;
    }

    public void setQuizzesFinished(List<UserQuiz> quizzesFinished) {
        this.quizzesFinished = quizzesFinished;
    }

}
