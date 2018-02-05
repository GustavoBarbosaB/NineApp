package com.example.gustavobarbosab.ninemessage.screens.chat.core;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.gustavobarbosab.ninemessage.R;
import com.example.gustavobarbosab.ninemessage.models.Message;
import com.example.gustavobarbosab.ninemessage.screens.chat.ChatActivity;
import com.example.gustavobarbosab.ninemessage.screens.chat.recycler.ChatAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gustavobarbosab on 05/02/18.
 */

public class ChatView {

    @BindView(R.id.sendButton)
    FloatingActionButton sendButton;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    View view;

    private ChatAdapter mAdapter;

    public ChatView(ChatActivity context) {

        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_main, parent, true);
        ButterKnife.bind(this,view);

        mAdapter = new ChatAdapter();
        recyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager mLayout=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayout);

    }

    public View view() {
        return view;
    }

    public void swapAdapter(List<Message> messages)
    {
        mAdapter.swapAdapter(messages);
    }

    public void notifyDataChanged() {
        mAdapter.notifyDataSetChanged();
    }

    public void toastMessage(String message) {
        Toast.makeText(view.getContext(),message,Toast.LENGTH_SHORT).show();
    }
}
