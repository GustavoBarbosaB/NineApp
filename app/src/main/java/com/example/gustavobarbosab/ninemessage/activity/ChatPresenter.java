package com.example.gustavobarbosab.ninemessage.activity;

import com.example.gustavobarbosab.ninemessage.domain.Message;
import com.example.gustavobarbosab.ninemessage.event.MessageEvent;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gustavobarbosab on 26/01/18.
 */

public interface ChatPresenter extends Serializable {
    void onDestroy();

    void onStop();

    void sendMessage();
    void receiveMessage();
    void setMessages(List<Message> messages);
    List<Message> getMessages();
    void injectChatApplication();
}
