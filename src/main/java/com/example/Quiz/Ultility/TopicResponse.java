package com.example.Quiz.Ultility;

import com.example.Quiz.Models.Topic;

public class TopicResponse {

    private Topic topic;
    private int totalQuiz;

    public TopicResponse(Topic topic) {
        this.topic = topic;
        this.totalQuiz = topic.getQuizzes().size();
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public int getTotalQuiz() {
        return totalQuiz;
    }

}
