package com.example.gustavobarbosab.ninemessage.activity;

import android.Manifest;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.widget.Toast;

import com.example.gustavobarbosab.ninemessage.application.ChatApplication;
import com.example.gustavobarbosab.ninemessage.callback.ReceiveMessage;
import com.example.gustavobarbosab.ninemessage.component.ChatComponent;
import com.example.gustavobarbosab.ninemessage.domain.Message;
import com.example.gustavobarbosab.ninemessage.domain.MessageImpl;
import com.example.gustavobarbosab.ninemessage.event.MessageEvent;
import com.example.gustavobarbosab.ninemessage.service.ChatService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import retrofit2.Call;

/**
 * Created by gustavobarbosab on 26/01/18.
 */

public class ChatPresenterImpl implements ChatPresenter, Serializable {

    @Override
    public ChatActivity getChatActivity() {
        return chatActivity;
    }

    private ChatActivity chatActivity;

    private List<Message> messages = new ArrayList<>();

    @Inject
    ChatService chatService;

    @Inject
    EventBus eventBus;

    private ChatComponent component;


    public ChatPresenterImpl(ChatActivity chatActivity) {
        this.chatActivity = chatActivity;
        injectChatApplication();
        eventBus = chatActivity.eventBus;
        chatService = chatActivity.chatService;
        ButterKnife.bind(chatActivity);
        eventBus.register(this);


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


    @Override
    public void receiveMessage() {

        Call<MessageImpl> call = chatService.receiveMessage();
        call.enqueue(new ReceiveMessage(this,eventBus));
    }

    @Subscribe
    public void onEvent(MessageEvent messageEvent){
        this.messages.add(messageEvent.getMessage());
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
        component.inject(this);
    }

    @Override
    public void checkPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(chatActivity,
                Manifest.permission.INTERNET);
        if(PermissionChecker.PERMISSION_GRANTED == permissionCheck)
            Toast.makeText(chatActivity,"Permissão internet garantida!",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(chatActivity,"ERRO NA PERMISSÃO!",Toast.LENGTH_SHORT).show();
    }

}
