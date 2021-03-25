package com.example.Quiz.Quick_Pojo_Class;


import java.util.Date;

 // tam thoi chua im ra class massage
public class Message {
    private final Date date_message = new Date();
    private String error ="";
    private String message= "";

    public Message(String message,String error){
        this.error=error;
        this.message=message;

    }
    public Message(String message) {
        this.error = message;
    }


     public Date getDate_message() {
         return date_message;
     }

     public String getError() {
         return error;
     }

     public void setError(String error) {
         this.error = error;
     }

     public String getMessage() {
         return message;
     }

     public void setMessage(String message) {
         this.message = message;
     }
 }
