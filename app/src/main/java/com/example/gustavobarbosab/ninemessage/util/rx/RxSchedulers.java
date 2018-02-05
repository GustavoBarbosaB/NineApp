package com.example.gustavobarbosab.ninemessage.util.rx;

import io.reactivex.Scheduler;

/**
 * Created by gustavobarbosab on 05/02/18.
 */

public interface RxSchedulers {
    Scheduler runOnBackground();

    Scheduler io();

    Scheduler compute();

    Scheduler androidThread();

    Scheduler internet();
}
