package com.example.gustavobarbosab.ninemessage.module;

import android.app.Application;

import com.example.gustavobarbosab.ninemessage.service.ChatService;

import org.greenrobot.eventbus.EventBus;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by gustavobarbosab on 27/01/18.
 */
@Module
public class ChatModule {
    /**
     * Aqui são instanciados os objetos pelo dagger
     */

    private Application app;

    public ChatModule(Application app) {
        this.app = app;
    }

    @Provides
    public ChatService getChatService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.mocky.io/v2/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ChatService.class);
    }

    @Provides
    public EventBus getEventBus() {
        return EventBus.builder().build();
    }

}
