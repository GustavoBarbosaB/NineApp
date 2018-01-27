package com.example.gustavobarbosab.ninemessage.application;

import android.app.Application;

import com.example.gustavobarbosab.ninemessage.component.ChatComponent;
import com.example.gustavobarbosab.ninemessage.component.DaggerChatComponent;
import com.example.gustavobarbosab.ninemessage.module.ChatModule;

import dagger.internal.DaggerCollections;

/**
 * Created by gustavobarbosab on 27/01/18.
 */

public class ChatApplication extends Application {

    private ChatComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerChatComponent.builder()
                                        .chatModule(new ChatModule(this))
                                        .build();
    }

    public ChatComponent getComponent(){
        return component;
    }
}
