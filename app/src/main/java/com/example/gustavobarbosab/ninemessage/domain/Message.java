package com.example.gustavobarbosab.ninemessage.domain;


import com.example.gustavobarbosab.ninemessage.screens.chat.recycler.Items.HolderItem;

/**
 * Created by gustavobarbosab on 26/01/18.
 */
public class Message {

    private String message;
    private String username;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Message(String message, String username) {

        this.message = message;
        this.username = username;
    }
}
