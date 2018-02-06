package com.example.gustavobarbosab.ninemessage.api;

import com.example.gustavobarbosab.ninemessage.domain.Message;

import retrofit2.http.GET;

/**
 * Created by gustavobarbosab on 26/01/18.
 */

public interface ChatService {

    @GET("5a6cf2ea2f00003322b6e537")
    io.reactivex.Observable<Message> receiveMessage();

}
