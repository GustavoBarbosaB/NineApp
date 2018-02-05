package com.example.gustavobarbosab.ninemessage.api;

import com.example.gustavobarbosab.ninemessage.screens.chat.core.ChatPresenter;
import com.example.gustavobarbosab.ninemessage.models.Message;
import com.example.gustavobarbosab.ninemessage.models.events.ErrorEvent;
import com.example.gustavobarbosab.ninemessage.models.events.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by gustavobarbosab on 31/01/18.
 */

public class MessageService {
/*
    private ChatService chatService;
    private ChatPresenter chatPresenter;
    private EventBus eventBus;

    public ChatService getChatService() {
        return chatService;
    }

    public void setChatService(ChatService chatService) {
        this.chatService = chatService;
    }

    public MessageService(ChatService chatService, ChatPresenter chatPresenter, EventBus eventBus) {
        this.chatService = chatService;
        this.chatPresenter = chatPresenter;
        this.eventBus = eventBus;
    }


    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void getMessage(){
        chatPresenter.addDisposable(chatService.receiveMessage()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));
    }

    private void handleResponse(Message message) {
        eventBus.post(new MessageEvent(message));
    }

    private void handleError(Throwable error) {
        eventBus.post(new ErrorEvent(error.getLocalizedMessage()));
    }*/
}
