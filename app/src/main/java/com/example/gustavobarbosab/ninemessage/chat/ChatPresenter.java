package com.example.gustavobarbosab.ninemessage.chat;

/**
 * Created by gustavobarbosab on 26/01/18.
 */

public interface ChatPresenter {
    void onDestroy();
    void sendMessage();
    void receiveMessage();
}
