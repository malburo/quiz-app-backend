package com.example.Quiz.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "quiz")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Quiz {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "quiz_id")
   private Long quizId;
   @Column(name = "quiz_name")
   private String quizName;
   @Column(name = "quiz_description")
   private String qizDescription;

   @OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL)
   private List<Question> questions;


   @ManyToOne
   @JoinColumn(name = "sub_topic_id",referencedColumnName = "sub_topic_id",foreignKey = @ForeignKey(name = "sub_topic_id"))
   private SubTopic subTopic;

   public Long getQuizId() {
      return quizId;
   }

   public void setQuizId(Long quizId) {
      this.quizId = quizId;
   }

   public String getQuizName() {
      return quizName;
   }

   public void setQuizName(String quizName) {
      this.quizName = quizName;
   }

   public String getQizDescription() {
      return qizDescription;
   }

   public void setQizDescription(String qizDescription) {
      this.qizDescription = qizDescription;
   }

   public List<Question> getQuestions() {
      return questions;
   }

   public void setQuestions(List<Question> questions) {
      this.questions = questions;
   }

   public SubTopic getSubTopic() {
      return subTopic;
   }

   public void setSubTopic(SubTopic subTopic) {
      this.subTopic = subTopic;
   }

}
