package com.example.gustavobarbosab.ninemessage.screens.chat.mvp;

import android.util.Log;

import com.example.gustavobarbosab.ninemessage.R;
import com.example.gustavobarbosab.ninemessage.domain.*;
import com.example.gustavobarbosab.ninemessage.domain.events.ErrorEvent;
import com.example.gustavobarbosab.ninemessage.domain.events.MessageEvent;
import com.example.gustavobarbosab.ninemessage.util.rx.RxSchedulers;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by gustavobarbosab on 26/01/18.
 */
public class ChatPresenter implements ChatContract.Presenter{

    /**
     * Presenter (Apresentador): classes que permitem a comunicação em Model e View. Classes que tendem a ser
     * criadas somente devido ao uso do MVP. Essas têm toda a lógica de formatação de dados dentro delas, lógica
     * antes presente na camada View;
     *
     * Camada responsável por responder as invocações da camada de visualização e invocações da camada de modelo,
     * além de também poder invocar ambas as camadas. O Presenter trabalha a formatação dos dados que entram em
     * ambas as camadas paralelas e também pode incluir parte da lógica de negócio que alguns programadores podem
     * pensar que deveria estar somente na camada de modelo;
     */

    EventBus eventBus;
    ChatView view;
    ChatModel model;
    CompositeDisposable compositeDisposable;
    RxSchedulers rxSchedulers;

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }


    public ChatPresenter(ChatView view, ChatModel model, CompositeDisposable compositeDisposable, EventBus eventBus, RxSchedulers rxSchedulers) {
        this.view = view;
        this.model = model;
        this.eventBus = eventBus;
        this.compositeDisposable = compositeDisposable;
        this.rxSchedulers = rxSchedulers;
        view.setmAdapter(model.messages);
        eventBus.register(this);
    }

    public void onCreate() {
        Log.d("entrando no presenter", "tudo ok");
    }

    public void onDestroy(){
        compositeDisposable.clear();
    }

    /**
     * Evento recebido pelo eventBus sempre que fizer uma requisição
     * ao chatservice.
     * @param messageEvent
     */
    @Subscribe
    public void onMessageEvent(MessageEvent messageEvent){
        model.messages.add(messageEvent.getMessage());
        view.notifyDataChanged();
    }

    /**
     * Evento recebido pelo eventBus sempre que houver um erro
     * na requisição do chatservice.
     * @param error
     */
    @Subscribe
    public void onError(ErrorEvent error){
        view.toastMessage(error.getError());
    }


    @Override
    public void sendMessage(){
        model.messages.add(model.sendMessage(view.getMessageText()));
        view.notifyDataChanged();
    }

    public void getMessage(){
        compositeDisposable.add(model.provideMessage()
                .observeOn(rxSchedulers.androidThread())
                .subscribeOn(rxSchedulers.io())
                .subscribe(this::handleMessageResponse,this::handleError));
    }

    private void handleMessageResponse(Message message) {
        eventBus.post(new MessageEvent(message));
    }

    private void handleError(Throwable error) {
        eventBus.post(new ErrorEvent(error.getLocalizedMessage()));
    }
}
