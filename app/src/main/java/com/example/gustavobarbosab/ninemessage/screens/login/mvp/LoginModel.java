package com.example.gustavobarbosab.ninemessage.screens.login.mvp;

import com.example.gustavobarbosab.ninemessage.screens.login.LoginActivity;

/**
 * Created by gustavobarbosab on 11/02/18.
 */

public class LoginModel {

    LoginPresenter loginPresenter;

    public LoginModel(LoginActivity loginActivity) {
        this.loginPresenter = loginActivity.getPresenter();
    }


}
