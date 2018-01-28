package com.example.gustavobarbosab.ninemessage.activity;

import android.os.Bundle;

import com.example.gustavobarbosab.ninemessage.event.MessageEvent;

/**
 * Created by gustavobarbosab on 27/01/18.
 */

public interface ChatView {

    void testBundle(Bundle savedInstanceState);
    void configureRecycler();
    //Aqui fiz o método inverso, na verdade estamos ouvindo a mensagem
    void sendMessage();
    void refreshAdapter();
    void checkPermission();
}
