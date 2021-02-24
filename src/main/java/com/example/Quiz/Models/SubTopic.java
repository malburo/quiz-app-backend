package com.example.Quiz.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sub_topic")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SubTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_topic_id")
    private  Long subTopicId;

    @Column(name = "sub_topic_name")
    private  String getSubTopic;
    @Column(name = "sub_topic_description")
    private  String subTopicDescription;

    @ManyToOne
    @JoinColumn(name = "topic_id",referencedColumnName = "topic_id",foreignKey = @ForeignKey(name = "topic_id_fk"))
    private Topic topic;

    @OneToMany(mappedBy = "subTopic", cascade = CascadeType.ALL)
    private List<Quiz>  quizzes ;

    public Long getSubTopicId() {
        return subTopicId;
    }

    public void setSubTopicId(Long subTopicId) {
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
