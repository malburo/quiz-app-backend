package com.example.Quiz.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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

   @OneToMany(mappedBy = "quiz",cascade =CascadeType.ALL)
   private List<UserQuiz> participantQuizzes;

   @ManyToOne
   @JoinColumn(name = "topic_id",referencedColumnName = "topic_id",foreignKey = @ForeignKey(name = "topic_id"))
   private Topic topic;

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
   @JsonProperty (access = JsonProperty.Access.WRITE_ONLY) // hide luon question
   public List<Question> getQuestions() {
      return questions;
   }

   public void setQuestions(List<Question> questions) {
      this.questions = questions;
   }

   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // hide quiz user da hoan thanh
   public List<UserQuiz> getParticipantQuizzes() {
      return participantQuizzes;
   }

   public void setParticipantQuizzes(List<UserQuiz> participantQuizzes) {
      this.participantQuizzes = participantQuizzes;
   }
   @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
   public Topic getTopic() {
      return topic;
   }
   public long gettopicId()
   {
      return topic.getTopicId();
   }


   public void setTopic(Topic topic) {
      this.topic = topic;
   }
}
