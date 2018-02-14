package com.example.gustavobarbosab.ninemessage.screens.login.mvp;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.example.gustavobarbosab.ninemessage.R;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

/**
 * Created by gustavobarbosab on 11/02/18.
 */

public class LoginPresenter {

    LoginView loginView;
    LoginModel loginModel;
    Socket mSocket;
    String mUsername;

    public LoginPresenter(LoginView loginView, LoginModel loginModel,Socket mSocket) {
        this.loginView = loginView;
        this.loginModel = loginModel;
        this.mSocket = mSocket;

    }
    public void onCreate() {
        Log.d("Login presenter", "tudo ok");
        mSocket.on("login", onLogin);
    }

    public void attemptLogin() {

        loginView.editText.setError(null);

        String username = loginView.getText();

        // Check for a valid username.
        if (TextUtils.isEmpty(username)) {
            loginView.focusText();
            loginView.toastMessage("Digite um nome de usuário!");
            return;
        }

        mUsername=username;

        // perform the user login attempt.
        mSocket.emit("add user", mUsername);
    }

    private Emitter.Listener onLogin = args -> {
        JSONObject data = (JSONObject) args[0];

        int numUsers;
        try {
            numUsers = data.getInt("numUsers");
        } catch (JSONException e) {
            return;
        }

        loginView.chatIntent(mUsername,numUsers);
    };


    public void onDestroy() {
        mSocket.off("login", onLogin);
    }
}

