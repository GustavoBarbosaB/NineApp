package com.example.gustavobarbosab.ninemessage.screens.chat.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.gustavobarbosab.ninemessage.R;
import com.example.gustavobarbosab.ninemessage.domain.*;
import com.example.gustavobarbosab.ninemessage.domain.events.ErrorEvent;
import com.example.gustavobarbosab.ninemessage.domain.events.MessageEvent;
import com.example.gustavobarbosab.ninemessage.screens.chat.recycler.Items.AlertItem;
import com.example.gustavobarbosab.ninemessage.screens.chat.recycler.Items.MyMessageItem;
import com.example.gustavobarbosab.ninemessage.screens.chat.recycler.Items.TheyMessageItem;
import com.example.gustavobarbosab.ninemessage.screens.login.LoginActivity;
import com.example.gustavobarbosab.ninemessage.util.rx.RxSchedulers;
import com.github.nkzawa.emitter.Emitter;
import     com.github.nkzawa.socketio.client.Socket;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.CompositeDisposable;

import static android.content.ContentValues.TAG;

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
    Socket mSocket;

    private Boolean isConnected = true;
    private String mUsername;
    private Integer numUsers;
    private Handler mTypingHandler = new Handler();
    private boolean mTyping = false;

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }


    public ChatPresenter(ChatView view, ChatModel model, CompositeDisposable compositeDisposable, EventBus eventBus, RxSchedulers rxSchedulers, Socket msocket) {
        this.view = view;
        this.model = model;
        this.eventBus = eventBus;
        this.compositeDisposable = compositeDisposable;
        this.rxSchedulers = rxSchedulers;
        this.mSocket=msocket;

        view.setmAdapter(model.messages);

        eventBus.register(this);

        initChat();
    }

    private void initChat() {
        mSocket.on(Socket.EVENT_CONNECT,onConnect);
        mSocket.on(Socket.EVENT_DISCONNECT,onDisconnect);
        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
        mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        mSocket.on("new message", onNewMessage);
        mSocket.on("user joined", onUserJoined);
        mSocket.on("user left", onUserLeft);
        mSocket.on("typing", onTyping);
        mSocket.on("stop typing", onStopTyping);
        mSocket.connect();

       // startSignIn();
    }

    public void onCreate(Bundle bundle) {
        Log.d("Chat presenter", "tudo ok");
        mUsername = (String) bundle.get("username");
        numUsers = (int) bundle.get("numUsers");

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
       // model.messages.add(messageEvent.getMessage());
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
        //model.messages.add(model.sendMessage(view.getMessageText()));
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

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            view.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(!isConnected) {
                        if(null!=mUsername)
                            mSocket.emit("add user", mUsername);
                        Toast.makeText(view.getActivity().getApplicationContext(),
                                R.string.connect, Toast.LENGTH_LONG).show();
                        isConnected = true;
                    }
                }
            });
        }
    };

    private Emitter.Listener onDisconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            view.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, "disconectado");
                    isConnected = false;
                    Toast.makeText(view.getActivity().getApplicationContext(),
                            R.string.disconnect, Toast.LENGTH_LONG).show();
                }
            });
        }
    };

    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            view.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.e(TAG, "Erro na conexão");
                    Toast.makeText(view.getActivity().getApplicationContext(),
                            R.string.error_connect, Toast.LENGTH_LONG).show();
                }
            });
        }
    };

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            view.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    String message;
                    try {
                        username = data.getString("username");
                        message = data.getString("message");
                    } catch (JSONException e) {
                        Log.e(TAG, e.getMessage());
                        return;
                    }

                   // removeTyping(username);
                    addTheyMessage(username, message);
                }
            });
        }
    };

    private Emitter.Listener onUserJoined = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            view.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    int numUsers;
                    try {
                        username = data.getString("username");
                        numUsers = data.getInt("numUsers");
                    } catch (JSONException e) {
                        Log.e(TAG, e.getMessage());
                        return;
                    }

                    addLog(view.getActivity().getString(R.string.message_user_joined, username));
                    addParticipantsLog(numUsers);
                }
            });
        }
    };

    private Emitter.Listener onUserLeft = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            view.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    int numUsers;
                    try {
                        username = data.getString("username");
                        numUsers = data.getInt("numUsers");
                    } catch (JSONException e) {
                        Log.e(TAG, e.getMessage());
                        return;
                    }

                    addLog(view.getActivity().getResources().getString(R.string.message_user_left, username));
                    addParticipantsLog(numUsers);
                  //  removeTyping(username);
                }
            });
        }
    };


    private Emitter.Listener onTyping = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            view.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    try {
                        username = data.getString("username");
                    } catch (JSONException e) {
                        Log.e(TAG, e.getMessage());
                        return;
                    }
                    //addTyping(username);
                }
            });
        }
    };

    private Emitter.Listener onStopTyping = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            view.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    try {
                        username = data.getString("username");
                    } catch (JSONException e) {
                        Log.e(TAG, e.getMessage());
                        return;
                    }
                   // removeTyping(username);
                }
            });
        }
    };


    private void addLog(String alert) {
        model.messages.add(new AlertItem(alert));
        view.notifyDataChanged();
    }
    private void addParticipantsLog(int numUsers) {
        addLog(view.getActivity()
                .getResources()
                .getQuantityString(R.plurals.message_participants, numUsers, numUsers));
    }

    private void addTheyMessage(String username, String message) {
        model.messages.add(new TheyMessageItem(message,username));
        view.notifyDataChanged();
    }

    private void addMyMessage(String message) {
        model.messages.add(new MyMessageItem(message,mUsername));
        view.notifyDataChanged();
    }


    public void attemptSend() {
        if (null == mUsername) return;
        if (!mSocket.connected()) return;

        mTyping = false;

        String message = view.getMessageText();
        if (TextUtils.isEmpty(message)) {
            view.requestTextFocus();
            return;
        }

        view.setText("");
        addMyMessage(message);

        // perform the sending message attempt.
        mSocket.emit("new message", message);
    }

   /* private void startSignIn() {
        mUsername = null;
        Intent intent = new Intent(view.getActivity(), LoginActivity.class);
        startActivityForResult(intent, REQUEST_LOGIN);
    }*/
/*

    private void leave() {
        mUsername = null;
        mSocket.disconnect();
        mSocket.connect();
        startSignIn();
    }
*/
/*private void addTyping(String username) {
        .add(new Message.Builder(Message.TYPE_ACTION)
                .username(username).build());
        mAdapter.notifyItemInserted(mMessages.size() - 1);
        scrollToBottom();
    }

    private void removeTyping(String username) {
        for (int i = mMessages.size() - 1; i >= 0; i--) {
            Message message = mMessages.get(i);
            if (message.getType() == Message.TYPE_ACTION && message.getUsername().equals(username)) {
                mMessages.remove(i);
                mAdapter.notifyItemRemoved(i);
            }
        }
    }

      private Runnable onTypingTimeout = new Runnable() {
        @Override
        public void run() {
            if (!mTyping) return;

            mTyping = false;
            mSocket.emit("stop typing");
        }
    };
*/
}

