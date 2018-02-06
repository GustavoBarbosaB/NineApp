package com.example.gustavobarbosab.ninemessage.screens.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gustavobarbosab.ninemessage.screens.chat.mvp.ChatContract;
import com.example.gustavobarbosab.ninemessage.screens.chat.mvp.ChatView;
import com.example.gustavobarbosab.ninemessage.screens.chat.dagger.ChatModule;
import com.example.gustavobarbosab.ninemessage.application.AppController;
import com.example.gustavobarbosab.ninemessage.screens.chat.mvp.ChatPresenter;
import com.example.gustavobarbosab.ninemessage.screens.chat.dagger.DaggerChatComponent;

import javax.inject.Inject;


public class ChatActivity extends AppCompatActivity{

    @Inject
    ChatView view;

    @Inject
    ChatPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerChatComponent.builder()
                .appComponent(AppController.getComponent())
                .chatModule(new ChatModule(this))
                .build()
                .inject(this);

        setContentView(view.view());
        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    public ChatPresenter getPresenter() {
        return presenter;
    }


}
