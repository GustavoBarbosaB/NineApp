package com.example.gustavobarbosab.ninemessage.application;

import android.app.Application;

import com.example.gustavobarbosab.ninemessage.BuildConfig;
import com.example.gustavobarbosab.ninemessage.application.builder.AppComponent;
import com.example.gustavobarbosab.ninemessage.application.builder.AppContextModule;
import com.example.gustavobarbosab.ninemessage.application.builder.DaggerAppComponent;


import timber.log.Timber;

/**
 * Created by gustavobarbosab on 27/01/18.
 */

public class AppController extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        initialiseLogger();
        initAppComponent();
    }

    private void initAppComponent() {
        component = DaggerAppComponent.builder().appContextModule(new AppContextModule(this)).build();
    }


    private void initialiseLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new Timber.Tree() {
                @Override
                protected void log(int priority, String tag, String message, Throwable t) {
                    //TODO  decide what to log in release version
                }
            });
        }
    }


    public static AppComponent getComponent(){
        return component;
    }
}
