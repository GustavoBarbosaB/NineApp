package com.example.gustavobarbosab.ninemessage.application.builder;

import com.example.gustavobarbosab.ninemessage.api.ChatService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gustavobarbosab on 05/02/18.
 */

@Module
public class ChatApiServiceModule {

    private String BASEURL = "http://www.mocky.io/v2/";

    @Provides
    ChatService getChatService(RxJava2CallAdapterFactory rx, GsonConverterFactory gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addCallAdapterFactory(rx)
                .addConverterFactory(gson)
                .build();

        return retrofit.create(ChatService.class);
    }
}
