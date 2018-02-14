package com.example.gustavobarbosab.ninemessage.application.builder;

import android.app.Application;

import com.example.gustavobarbosab.ninemessage.api.ChatService;
import com.example.gustavobarbosab.ninemessage.util.rx.RxSchedulers;
import com.github.nkzawa.socketio.client.Socket;

import org.greenrobot.eventbus.EventBus;

import dagger.Component;

/**
 * Created by gustavobarbosab on 05/02/18.
 */
@PerApplication
@Component(modules = {
        NetworkModule.class,
        SocketModule.class,
        AppModule.class,
        RxModule.class,
        ChatApiServiceModule.class,
        EventBusModule.class
    }
)
public interface AppComponent {
    EventBus eventBus();
    RxSchedulers rxSchedulers();
    ChatService apiService();
    Socket socket();
}
