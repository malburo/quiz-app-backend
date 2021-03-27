package com.example.Quiz.Quick_Pojo_Class;

import java.util.Date;

public class ErrorMessage {
    private final Date date_message = new Date();
    private String status;
    private String message;


    public ErrorMessage(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
