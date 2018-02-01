package com.example.gustavobarbosab.ninemessage.service;

import android.database.Observable;

import com.example.gustavobarbosab.ninemessage.domain.Message;
import com.example.gustavobarbosab.ninemessage.domain.MessageImpl;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by gustavobarbosab on 26/01/18.
 */

public interface ChatService {
    @GET("5a6cf2ea2f00003322b6e537")
    io.reactivex.Observable<MessageImpl> receiveMessage();
}
