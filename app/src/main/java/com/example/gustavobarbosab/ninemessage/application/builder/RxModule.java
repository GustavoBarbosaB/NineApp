package com.example.gustavobarbosab.ninemessage.application.builder;

import com.example.gustavobarbosab.ninemessage.util.rx.AppRxSchedulers;
import com.example.gustavobarbosab.ninemessage.util.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gustavobarbosab on 05/02/18.
 */

@Module
public class RxModule {
    @Provides
    RxSchedulers provideRxSchedulers() {
        return new AppRxSchedulers();
    }
}
