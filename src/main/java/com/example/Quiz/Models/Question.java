package com.example.Quiz.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;
    @Column(name = "question_name")
    private String questionName;
    @Column(name = "question_description")
    private String questionDescription;
    @Column(name = "question_point")
    private String questionPoint;
    @Column(name = "question_image_url")
    private String questionImageUrl;
    @Column(name = "question_Answer_A")
    private String questionAnswerA;
    @Column(name = "question_Answer_B")
    private String questionAnswerB;
    @Column(name = "question_Answer_C")
    private String questionAnswerC;
    @Column(name = "questionAnswerD")
    private String questionAnswerD;
    @Column(name = "question_Answer_Correct")
    private String questionAnswerCorrect;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "quiz_id",referencedColumnName = "quiz_id",foreignKey = @ForeignKey(name ="quiz_id_fk"))
    public Quiz quiz;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public String getQuestionPoint() {
        return questionPoint;
    }

    public void setQuestionPoint(String questionPoint) {
        this.questionPoint = questionPoint;
    }

    public String getQuestionImageUrl() {
        return questionImageUrl;
    }

    public void setQuestionImageUrl(String questionImageUrl) {
        this.questionImageUrl = questionImageUrl;
    }

    public String getQuestionAnswerA() {
        return questionAnswerA;
    }

    public void setQuestionAnswerA(String questionAnswerA) {
        this.questionAnswerA = questionAnswerA;
    }

    public String getQuestionAnswerB() {
        return questionAnswerB;
    }

    public void setQuestionAnswerB(String questionAnswerB) {
        this.questionAnswerB = questionAnswerB;
    }

    public String getQuestionAnswerC() {
        return questionAnswerC;
    }

    public void setQuestionAnswerC(String questionAnswerC) {
        this.questionAnswerC = questionAnswerC;
    }

    public String getQuestionAnswerD() {
        return questionAnswerD;
    }

    public void setQuestionAnswerD(String questionAnswerD) {
        this.questionAnswerD = questionAnswerD;
    }

    public String getQuestionAnswerCorrect() {
        return questionAnswerCorrect;
    }

    public void setQuestionAnswerCorrect(String questionAnswerCorrect) {
        this.questionAnswerCorrect = questionAnswerCorrect;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }



}
