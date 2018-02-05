package com.example.gustavobarbosab.ninemessage.screens.chat.core;

import com.example.gustavobarbosab.ninemessage.api.ChatService;
import com.example.gustavobarbosab.ninemessage.models.Message;
import com.example.gustavobarbosab.ninemessage.screens.chat.ChatActivity;

import io.reactivex.Observable;

/**
 * Created by gustavobarbosab on 05/02/18.
 */

public class ChatModel {

    ChatActivity context;
    ChatService chatService;

    public ChatModel(ChatActivity chatActivity, ChatService api) {
        this.context = chatActivity;
        this.chatService = api;
    }

    Observable<Message> provideMessage() {
        return chatService.receiveMessage();
    }
}
