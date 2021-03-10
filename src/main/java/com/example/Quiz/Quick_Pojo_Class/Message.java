package com.example.Quiz.Quick_Pojo_Class;


import java.util.Date;


public class Message {
    private String message;
    private final Date date_message = new Date();
    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate_message() {
        return date_message;
    }
}
