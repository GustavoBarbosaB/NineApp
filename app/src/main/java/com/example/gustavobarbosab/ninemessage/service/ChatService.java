package com.example.gustavobarbosab.ninemessage.service;

import com.example.gustavobarbosab.ninemessage.domain.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by gustavobarbosab on 26/01/18.
 */

public interface ChatService {
    @GET("5a6cbfa52f0000e91bb6e514")
    Call<List<Message>> receiveMessage();
}
