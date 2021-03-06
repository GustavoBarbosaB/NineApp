package com.example.gustavobarbosab.ninemessage.component;

import com.example.gustavobarbosab.ninemessage.activity.ChatPresenter;
import com.example.gustavobarbosab.ninemessage.activity.ChatActivity;
import com.example.gustavobarbosab.ninemessage.module.ChatModule;

import dagger.Component;

/**
 * Created by gustavobarbosab on 27/01/18.
 */

@Component(modules = ChatModule.class)
public interface ChatComponent {
    /**
     * Aqui nós informamos quais classes irão precisar da injeção de dependencias
     */
    void inject(ChatPresenter chatPresenter);
    void inject(ChatActivity chatActivity);
}
