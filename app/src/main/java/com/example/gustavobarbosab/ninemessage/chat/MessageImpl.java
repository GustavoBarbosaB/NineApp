package com.example.gustavobarbosab.ninemessage.chat;

/**
 * Created by gustavobarbosab on 26/01/18.
 */

public class MessageImpl implements com.example.gustavobarbosab.ninemessage.chat.Message {

    private String message;
    private String sender;
    private String receiver;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getSender() {
        return sender;
    }

    @Override
    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public String getReceiver() {
        return receiver;
    }

    @Override
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
