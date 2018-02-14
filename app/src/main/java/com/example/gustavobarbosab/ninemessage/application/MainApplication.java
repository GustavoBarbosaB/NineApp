package com.example.gustavobarbosab.ninemessage.application;

import android.app.Activity;
import android.app.Application;

import com.example.gustavobarbosab.ninemessage.BuildConfig;
import com.example.gustavobarbosab.ninemessage.application.builder.AppComponent;
import com.example.gustavobarbosab.ninemessage.application.builder.AppModule;
import com.example.gustavobarbosab.ninemessage.application.builder.DaggerAppComponent;


import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;

/**
 * Created by gustavobarbosab on 27/01/18.
 */

public class MainApplication extends Application{

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        initialiseLogger();
        initAppComponent();
    }

    private void initAppComponent() {
        component = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
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
