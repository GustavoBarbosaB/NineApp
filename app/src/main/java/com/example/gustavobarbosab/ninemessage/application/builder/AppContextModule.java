package com.example.gustavobarbosab.ninemessage.application.builder;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gustavobarbosab on 05/02/18.
 */

@Module
public class AppContextModule {

    private final Context context;

    public AppContextModule(Context aContext) {
        this.context = aContext;
    }

    @AppScope
    @Provides
    Context provideAppContext() {
        return context;
    }

}
