package com.example.gustavobarbosab.ninemessage.callback;

import com.example.gustavobarbosab.ninemessage.activity.ChatPresenter;
import com.example.gustavobarbosab.ninemessage.domain.Message;
import com.example.gustavobarbosab.ninemessage.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gustavobarbosab on 27/01/18.
 */

public class ReceiveMessage implements Callback<List<Message>> {

    private ChatPresenter chatPresenter;
    private EventBus eventBus;

    public ReceiveMessage(ChatPresenter chatPresenter, EventBus eventBus) {
        this.chatPresenter = chatPresenter;
        this.eventBus = eventBus;
    }

    @Override
    public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
        if(response.isSuccessful()){
            List<Message> messages = response.body();
            eventBus.post(new MessageEvent(messages));

        }

    }

    @Override
    public void onFailure(Call<List<Message>> call, Throwable t) {

    }

}
