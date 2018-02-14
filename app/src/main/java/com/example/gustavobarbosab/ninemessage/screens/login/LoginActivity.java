package com.example.gustavobarbosab.ninemessage.screens.login;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gustavobarbosab.ninemessage.R;
import com.example.gustavobarbosab.ninemessage.application.MainApplication;
import com.example.gustavobarbosab.ninemessage.screens.chat.dagger.ChatModule;
import com.example.gustavobarbosab.ninemessage.screens.chat.dagger.DaggerChatComponent;
import com.example.gustavobarbosab.ninemessage.screens.login.dagger.DaggerLoginComponent;
import com.example.gustavobarbosab.ninemessage.screens.login.dagger.LoginModule;
import com.example.gustavobarbosab.ninemessage.screens.login.mvp.LoginPresenter;
import com.example.gustavobarbosab.ninemessage.screens.login.mvp.LoginView;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.HasActivityInjector;

public class LoginActivity extends AppCompatActivity {

    @Inject
    LoginPresenter loginPresenter;

    @Inject
    LoginView loginView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerLoginComponent.builder()
                .appComponent(MainApplication.getComponent())
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);

        setContentView(loginView.view());
        loginPresenter.onCreate();

    }

    public LoginPresenter getPresenter() {
        return loginPresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.onDestroy();
    }



}
