package com.example.gustavobarbosab.ninemessage.application.builder;

import com.example.gustavobarbosab.ninemessage.api.ChatService;
import com.example.gustavobarbosab.ninemessage.util.rx.RxSchedulers;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by gustavobarbosab on 05/02/18.
 */
@AppScope
@Component(modules = {
        NetworkModule.class,
        AppContextModule.class,
        RxModule.class,
        ChatApiServiceModule.class,
        EventBusModule.class
    }
)
public interface AppComponent {
    EventBus eventBus();
    RxSchedulers rxSchedulers();
    ChatService apiService();
}
