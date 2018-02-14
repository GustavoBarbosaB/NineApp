package com.example.gustavobarbosab.ninemessage.application.builder;

import com.github.nkzawa.socketio.client.IO;

import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gustavobarbosab on 12/02/18.
 */
@Module
public class SocketModule {

    private String CHAT_SERVER_URL = "https://tranquil-hamlet-65820.herokuapp.com/";

    @Provides
    Socket provideSocket(){
        try {
            return IO.socket(CHAT_SERVER_URL).connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

}
