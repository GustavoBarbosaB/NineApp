package com.example.gustavobarbosab.ninemessage.callback;

import android.widget.Toast;

import com.example.gustavobarbosab.ninemessage.activity.ChatPresenter;
import com.example.gustavobarbosab.ninemessage.domain.Message;
import com.example.gustavobarbosab.ninemessage.domain.MessageImpl;
import com.example.gustavobarbosab.ninemessage.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gustavobarbosab on 27/01/18.
 */

public class ReceiveMessage implements Callback<MessageImpl> {

    private ChatPresenter chatPresenter;
    private EventBus eventBus;

    public ReceiveMessage(ChatPresenter chatPresenter, EventBus eventBus) {
        this.chatPresenter = chatPresenter;
        this.eventBus = eventBus;
    }

    @Override
    public void onResponse(Call<MessageImpl> call, Response<MessageImpl> response) {
        if(response.isSuccessful()){
            MessageImpl message = response.body();
            eventBus.post(new MessageEvent(message));
        }

    }

    @Override
    public void onFailure(Call<MessageImpl> call, Throwable t) {
        Toast.makeText(chatPresenter.getChatActivity(),"Requisição falhou!",Toast.LENGTH_SHORT).show();
    }

}
