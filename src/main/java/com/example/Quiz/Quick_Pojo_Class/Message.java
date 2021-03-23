package com.example.Quiz.Quick_Pojo_Class;


import java.util.Date;

 // tam thoi chua im ra class massage
public class Message {
    private final Date date_message = new Date();
    private String message;

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
