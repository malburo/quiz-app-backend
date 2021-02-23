package com.example.Quiz.Models;

import javax.persistence.*;

@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private  String topicId;

    @Column(name = "topic_name")
    private  String topicName;
    @Column(name = "topic_description")
    private  String topicDescription;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id",foreignKey = @ForeignKey(name = "user_id_fk"))
    private User user;
    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
