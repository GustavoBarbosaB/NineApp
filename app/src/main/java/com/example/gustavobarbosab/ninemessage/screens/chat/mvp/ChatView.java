package com.example.gustavobarbosab.ninemessage.screens.chat.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gustavobarbosab.ninemessage.R;
import com.example.gustavobarbosab.ninemessage.domain.Message;
import com.example.gustavobarbosab.ninemessage.screens.chat.ChatActivity;
import com.example.gustavobarbosab.ninemessage.screens.chat.recycler.ChatAdapter;
import com.example.gustavobarbosab.ninemessage.screens.chat.recycler.Items.HolderItem;
import com.example.gustavobarbosab.ninemessage.screens.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by gustavobarbosab on 05/02/18.
 */

public class ChatView implements ChatContract.View{

    /**
     * View (Visualização): Activitys, Fragments, Dialogs, Widgets e Adapters;
     *como no MVC, responde a saída e entrada de dados, porém a saída vem do Presenter,
     *a entrada normalmente vem do usuário;
     */

    @BindView(R.id.my_toolbar)
    Toolbar toolbar;

    @BindView(R.id.sendButton)
    FloatingActionButton sendButton;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.textMessage)
    EditText editText;

    View view;

    private ChatAdapter mAdapter;

    private ChatActivity activity;

    public ChatView(ChatActivity context) {
        this.activity=context;
        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_main, parent, true);
        ButterKnife.bind(this,view);
        keyboardSendConfig();
        toolbar.setLogo(R.mipmap.ic_toolbar);
        toolbar.setTitle(R.string.nine);
        activity.setSupportActionBar(toolbar);
    }

    private void keyboardSendConfig() {
        editText.setOnEditorActionListener((v, actionId, event) -> {
            boolean handled = false;
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                sendMessage();
                handled = true;
            }
            return handled;
        });
    }

    public void setmAdapter(ArrayList<HolderItem> messages) {
        mAdapter = new ChatAdapter(messages);
        recyclerView.setAdapter(mAdapter);
        LinearLayoutManager mLayout= new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayout);
    }

    public View view() {
        return view;
    }


    public void notifyDataChanged() {
        recyclerView.smoothScrollToPosition(mAdapter.getItemCount()-1);
        mAdapter.notifyDataSetChanged();
        this.setText("");
    }

    public void toastMessage(String message) {
        Toast.makeText(view.getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.sendButton)
    @Override
    public void sendMessage(){
        activity.getPresenter().attemptSend();
    }

    @Override
    public void restoreInstance(Bundle state) {

    }

    @Override
    public void saveInstance(Bundle state) {

    }


    public String getMessageText() {
        return editText.getText().toString();
    }

    public ChatActivity getActivity() {
        return activity;
    }

    public void requestTextFocus() {
        editText.requestFocus();
    }

    public void setText(String text) {
        editText.setText(text);
    }

    public void updateAdapter(ArrayList<HolderItem> messages) {
        mAdapter.setMessages(messages);
    }

    public void login() {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
        activity.close();
    }
}
