package com.example.gustavobarbosab.ninemessage.chat;

import com.example.gustavobarbosab.ninemessage.chat.ChatActivity;
import com.example.gustavobarbosab.ninemessage.chat.ChatPresenter;

/**
 * Created by gustavobarbosab on 26/01/18.
 */

public class ChatPresenterImpl implements ChatPresenter {

    ChatActivity chatActivity;

    public ChatPresenterImpl(ChatActivity chatActivity) {
        this.chatActivity = chatActivity;
    }

    @Override
    public void onDestroy() {
        this.chatActivity = null;
    }

    @Override
    public void sendMessage() {

    }

    @Override
    public void receiveMessage() {

    }
}
