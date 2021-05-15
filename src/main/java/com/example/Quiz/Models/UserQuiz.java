package com.example.Quiz.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "user_quiz")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_quiz_id")
    private Long userQuizId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id",referencedColumnName = "quiz_id",foreignKey = @ForeignKey(name = "quiz_id_fk"))
    private Quiz quiz;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "user_id",foreignKey = @ForeignKey(name = "user_id_fk"))
    private User user;

    @Column(name = "rating")
    private int rating;

    @Column(name = "point")
    private int point;
    public Long getUserQuizId() {
        return userQuizId;
    }

    public void setUserQuizId(Long userQuizId) {
        this.userQuizId = userQuizId;
    }

    public UserQuiz() {
    }

    public UserQuiz(Quiz quiz, User user,int point) {
        this.quiz = quiz;
        this.user = user;
        this.point = point;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
