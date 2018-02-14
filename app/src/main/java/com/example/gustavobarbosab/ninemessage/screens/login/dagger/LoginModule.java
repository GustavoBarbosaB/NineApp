package com.example.gustavobarbosab.ninemessage.screens.login.dagger;

import com.example.gustavobarbosab.ninemessage.screens.login.LoginActivity;
import com.example.gustavobarbosab.ninemessage.screens.login.mvp.LoginModel;
import com.example.gustavobarbosab.ninemessage.screens.login.mvp.LoginPresenter;
import com.example.gustavobarbosab.ninemessage.screens.login.mvp.LoginView;
import com.github.nkzawa.socketio.client.Socket;
import dagger.Module;
import dagger.Provides;

/**
 * Created by gustavobarbosab on 11/02/18.
 */

@Module
public class LoginModule {

    LoginActivity loginActivity;

    public LoginModule(LoginActivity context){
        this.loginActivity=context;
    }

    @LoginScope
    @Provides
    LoginView provideView(){ return new LoginView(loginActivity);}

    @LoginScope
    @Provides
    LoginPresenter providePresenter(LoginView loginView, LoginModel loginModel, Socket socket){
        return new LoginPresenter(loginView,loginModel,socket);
    }

    @LoginScope
    @Provides
    LoginActivity provideContext(){return loginActivity;}

    @LoginScope
    @Provides
    LoginModel provideModel(){
        return new LoginModel(loginActivity);
    }
}
