package com.example.gustavobarbosab.ninemessage.application.builder;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjectionModule;

/**
 * Created by gustavobarbosab on 05/02/18.
 */

@Module
public class AppModule {

    private final Context context;

    public AppModule(Context aContext) {
        this.context = aContext;
    }

    @PerApplication
    @Provides
    Context provideAppContext() {
        return context;
    }

}
