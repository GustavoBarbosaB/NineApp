package com.example.gustavobarbosab.ninemessage.activity;

import com.example.gustavobarbosab.ninemessage.domain.Message;
import com.example.gustavobarbosab.ninemessage.event.ErrorEvent;
import com.example.gustavobarbosab.ninemessage.event.MessageEvent;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gustavobarbosab on 26/01/18.
 */

public interface ChatPresenter  {
    void onDestroy();
    void onStop();
    ChatActivity getChatActivity();
    void sendMessage();
    void receiveMessage();
    List<Message> getMessages();
    void injectChatApplication();
    void checkPermission();
    void onError(ErrorEvent error);
    void onEvent(MessageEvent messageEvent);
}
