package com.example.Quiz.Models;

import javax.persistence.*;

@Entity
@Table(name = "sub_topic")
public class SubTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_topic_id")
    private  String subTopicId;

    @Column(name = "sub_topic_name")
    private  String getSubTopic;
    @Column(name = "sub_topic_description")
    private  String subTopicDescription;

    @ManyToOne
    @JoinColumn(name = "topic_id",referencedColumnName = "topic_id",foreignKey = @ForeignKey(name = "topic_id_fk"))
    private Topic topic;


    public String getSubTopicId() {
        return subTopicId;
    }

    public void setSubTopicId(String subTopicId) {
        this.subTopicId = subTopicId;
    }

    public String getGetSubTopic() {
        return getSubTopic;
    }

    public void setGetSubTopic(String getSubTopic) {
        this.getSubTopic = getSubTopic;
    }

    public String getSubTopicDescription() {
        return subTopicDescription;
    }

    public void setSubTopicDescription(String subTopicDescription) {
        this.subTopicDescription = subTopicDescription;
    }
}
