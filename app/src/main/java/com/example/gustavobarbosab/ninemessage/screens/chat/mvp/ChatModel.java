package com.example.gustavobarbosab.ninemessage.screens.chat.mvp;

import com.example.gustavobarbosab.ninemessage.api.ChatService;
import com.example.gustavobarbosab.ninemessage.domain.Message;
import com.example.gustavobarbosab.ninemessage.screens.chat.ChatActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by gustavobarbosab on 05/02/18.
 */

public class ChatModel implements ChatContract.Model {

    /**
     * Model (Modelo): classes de obtenção de dados, em uma base convencional local ou pela
     * comunicação remota, Network. Classes de domínio e lógica de negócio, de preferência
     * as que não tenham lógica de formatação de dados, essas tendem a estar na camada Presenter.
     *
     * Camada fornecedora de dados além de conter a lógica de negócio do domínio do problema.
     */

    ChatPresenter presenter;
    List<Message> messages = new ArrayList<>();
    ChatService chatService;

    public ChatModel(ChatActivity chatActivity, ChatService api) {
        this.presenter = chatActivity.getPresenter();
        this.chatService = api;
    }

    public Observable<Message> provideMessage() {
        return chatService.receiveMessage();
    }

    @Override
    public Message sendMessage(String text){
        return new Message(text,"Gustavo","Brother");
    }
}
