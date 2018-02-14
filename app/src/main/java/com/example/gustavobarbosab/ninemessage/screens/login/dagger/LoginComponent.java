package com.example.gustavobarbosab.ninemessage.screens.login.dagger;

import com.example.gustavobarbosab.ninemessage.application.builder.AppComponent;
import com.example.gustavobarbosab.ninemessage.screens.login.LoginActivity;

import dagger.Component;

/**
 * Created by gustavobarbosab on 11/02/18.
 */
@LoginScope
@Component(dependencies = {AppComponent.class}, modules = {LoginModule.class})
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
