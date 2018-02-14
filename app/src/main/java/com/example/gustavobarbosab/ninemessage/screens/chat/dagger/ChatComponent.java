package com.example.gustavobarbosab.ninemessage.screens.chat.dagger;

import com.example.gustavobarbosab.ninemessage.application.builder.AppComponent;
import com.example.gustavobarbosab.ninemessage.screens.chat.ChatActivity;

import dagger.Component;

/**
 * Created by gustavobarbosab on 05/02/18.
 */
@ChatScope
@Component(dependencies = {AppComponent.class}, modules = {ChatModule.class})
public interface ChatComponent {
    void inject(ChatActivity chatActivity);

}
