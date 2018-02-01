package com.example.gustavobarbosab.ninemessage.activity;

import android.Manifest;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.widget.Toast;

import com.example.gustavobarbosab.ninemessage.application.ChatApplication;
import com.example.gustavobarbosab.ninemessage.component.ChatComponent;
import com.example.gustavobarbosab.ninemessage.domain.Message;
import com.example.gustavobarbosab.ninemessage.event.ErrorEvent;
import com.example.gustavobarbosab.ninemessage.event.MessageEvent;
import com.example.gustavobarbosab.ninemessage.service.ChatService;
import com.example.gustavobarbosab.ninemessage.service.MessageService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

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
    EventBus eventBus;

    @Inject
    ChatService chatService;

    private ChatComponent component;

    private CompositeDisposable compositeDisposable;

    private MessageService messageService;


    public ChatPresenterImpl(ChatActivity chatActivity) {
        this.chatActivity = chatActivity;
        injectChatApplication();
        compositeDisposable = new CompositeDisposable();
        messageService = new MessageService(chatService,compositeDisposable,eventBus);
        eventBus.register(this);
    }


    @Override
    public void onDestroy() {
        this.chatActivity = null;
    }

    @Override
    public void onStop(){
        eventBus.unregister(this);
        compositeDisposable.clear();
    }

    @Override
    public void sendMessage() {
        //aqui enviaremos as mensagens
    }


    @Override
    public void receiveMessage() {
        messageService.getMessage();
    }

    @Override
    @Subscribe
    public void onEvent(MessageEvent messageEvent){
        this.messages.add(messageEvent.getMessage());
        chatActivity.refreshAdapter();
    }

    @Override
    @Subscribe
    public void onError(ErrorEvent error){
        Toast.makeText(chatActivity,error.getError(),Toast.LENGTH_SHORT).show();
    }

    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public void injectChatApplication() {
        ChatApplication app = (ChatApplication) chatActivity.getApplication();
        component = app.getComponent();
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
