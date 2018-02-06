package com.example.gustavobarbosab.ninemessage.application.builder;

import android.util.EventLog;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gustavobarbosab on 06/02/18.
 */

@Module
public class EventBusModule {

    @Provides
    EventBus provideEventBus(){
        return EventBus.builder().build();
    }
}
