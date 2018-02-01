package com.example.gustavobarbosab.ninemessage.service;

import android.util.EventLog;

import com.example.gustavobarbosab.ninemessage.domain.MessageImpl;
import com.example.gustavobarbosab.ninemessage.event.ErrorEvent;
import com.example.gustavobarbosab.ninemessage.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by gustavobarbosab on 31/01/18.
 */

public class MessageService {


    private ChatService chatService;
    private CompositeDisposable mCompositeDisposable;
    private EventBus eventBus;

    public ChatService getChatService() {
        return chatService;
    }

    public void setChatService(ChatService chatService) {
        this.chatService = chatService;
    }

    public MessageService(ChatService chatService, CompositeDisposable mCompositeDisposable, EventBus eventBus) {
        this.chatService = chatService;
        this.mCompositeDisposable = mCompositeDisposable;
        this.eventBus = eventBus;
    }

    public void setmCompositeDisposable(CompositeDisposable mCompositeDisposable) {
        this.mCompositeDisposable = mCompositeDisposable;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void getMessage(){
        mCompositeDisposable.add(chatService.receiveMessage()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));
    }

    private void handleResponse(MessageImpl message) {
        eventBus.post(new MessageEvent(message));
    }

    private void handleError(Throwable error) {
        eventBus.post(new ErrorEvent(error.getLocalizedMessage()));
    }
}
