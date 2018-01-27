package com.example.gustavobarbosab.ninemessage.chat;

/**
 * Created by gustavobarbosab on 26/01/18.
 */

public interface Message {

    //------ Getters and Setters
    String getMessage();
    String getSender();
    String getReceiver();
    void setMessage(String message);
    void setSender(String sender);
    void setReceiver(String receiver);
}
