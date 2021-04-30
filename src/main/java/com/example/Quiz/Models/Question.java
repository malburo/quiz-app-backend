package com.example.Quiz.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;
    @JsonProperty("questionTitle")
    @Column(name = "question_name")
    private String questionName;
    @JsonProperty("questionCode")
    @Column(name = "question_description")
    private String questionDescription;
    @JsonProperty("questionProgramingLanguage")
    @Column(name ="programingLanguage")
    private String programingLanguage;
    @Column(name = "question_point")
    private int  questionPoint;
    @Column(name = "question_Answer_A")
    private String questionAnswerA;
    @Column(name = "question_Answer_B")
    private String questionAnswerB;
    @Column(name = "question_Answer_C")
    private String questionAnswerC;
    @Column(name = "questionAnswer_D")
    private String questionAnswerD;

    @Column(name = "questionCorrectAnswer")
    private String questionCorrectAnswer;

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

    public int getQuestionPoint() {
        return questionPoint;
    }

    public void setQuestionPoint(int questionPoint) {
        this.questionPoint = questionPoint;
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

    public String getQuestionCorrectAnswer() {
        return questionCorrectAnswer;
    }

    public void setQuestionCorrectAnswer(String questionCorrectAnswer) {
        this.questionCorrectAnswer = questionCorrectAnswer;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getProgramingLanguage() {
        return programingLanguage;
    }

    public void setProgramingLanguage(String programingLanguage) {
        this.programingLanguage = programingLanguage;
    }


}
