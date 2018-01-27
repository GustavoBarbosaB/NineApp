package com.example.gustavobarbosab.ninemessage.activity;

import com.example.gustavobarbosab.ninemessage.application.ChatApplication;
import com.example.gustavobarbosab.ninemessage.callback.ReceiveMessage;
import com.example.gustavobarbosab.ninemessage.component.ChatComponent;
import com.example.gustavobarbosab.ninemessage.domain.Message;
import com.example.gustavobarbosab.ninemessage.event.MessageEvent;
import com.example.gustavobarbosab.ninemessage.service.ChatService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import retrofit2.Call;

/**
 * Created by gustavobarbosab on 26/01/18.
 */

public class ChatPresenterImpl implements ChatPresenter {

    private ChatActivity chatActivity;

    private List<Message> messages;

    private ChatService chatService;

    private EventBus eventBus;


    private ChatComponent component;


    public ChatPresenterImpl(ChatActivity chatActivity) {
        this.chatActivity = chatActivity;
        injectChatApplication();
        eventBus = chatActivity.eventBus;
        chatService = chatActivity.chatService;
        eventBus.register(this);
        ButterKnife.bind(chatActivity);
        if(this.messages == null)
            messages = new ArrayList<>();
    }

    @Override
    public void onDestroy() {
        this.chatActivity = null;
    }

    @Override
    public void onStop(){
        eventBus.unregister(this);
    }



    @Override
    public void sendMessage() {
        //aqui enviaremos as mensagens
    }

    @Subscribe
    @Override
    public void receiveMessage() {
        Call<List<Message>> call = chatService.receiveMessage();
        call.enqueue(new ReceiveMessage(this,eventBus));
    }

    @Subscribe
    public void messageResponse(List<Message> messages){
        this.messages = messages;
        chatActivity.refreshAdapter();
    }

    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public void injectChatApplication() {
        ChatApplication app = (ChatApplication) chatActivity.getApplication();
        component = app.getComponent();
        component.inject(chatActivity);
    }

    @Override
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
