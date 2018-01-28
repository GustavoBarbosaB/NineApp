package com.example.gustavobarbosab.ninemessage.activity;

import android.Manifest;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.gustavobarbosab.ninemessage.R;
import com.example.gustavobarbosab.ninemessage.adapter.RecycleAdapter;
import com.example.gustavobarbosab.ninemessage.event.MessageEvent;
import com.example.gustavobarbosab.ninemessage.service.ChatService;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity implements ChatView {

    @BindView(R.id.sendButton)
    FloatingActionButton sendButton;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    ChatService chatService;

    @Inject
    EventBus eventBus;

    private RecycleAdapter mAdapter;

    private ChatPresenter chatPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testBundle(savedInstanceState);
        configureRecycler();
        checkPermission();

        //Aqui chamaria ainda o método de ouvir as mensagens
    }


    @Override
    public void onStop() {
        super.onStop();
        chatPresenter.onStop();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("chatPresenter", (ChatPresenterImpl) chatPresenter);
    }

    @Override
    public void testBundle(Bundle savedInstanceState) {
        if(savedInstanceState!=null)
            chatPresenter = (ChatPresenter) savedInstanceState.getSerializable("chatPresenter");
        else
            chatPresenter = new ChatPresenterImpl(this);
    }

    @Override
    public void configureRecycler() {
        RecyclerView.LayoutManager mLayout;
        recyclerView.setHasFixedSize(true);
        mLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayout);
        mAdapter = new RecycleAdapter(chatPresenter.getMessages());
        recyclerView.setAdapter(mAdapter);
    }

    @OnClick(R.id.sendButton)
    @Override
    public void sendMessage() {
        chatPresenter.receiveMessage();
        Toast.makeText(getApplicationContext(),"Botão pressionado!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshAdapter() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void checkPermission() {
        chatPresenter.checkPermission();
    }


}
