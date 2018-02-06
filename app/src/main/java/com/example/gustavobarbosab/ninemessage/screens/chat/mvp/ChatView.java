package com.example.gustavobarbosab.ninemessage.screens.chat.mvp;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gustavobarbosab.ninemessage.R;
import com.example.gustavobarbosab.ninemessage.domain.Message;
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

    @BindView(R.id.textMessage)
    TextView textView;

    View view;

    private ChatAdapter mAdapter;

    public ChatView(ChatActivity context) {

        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_main, parent, true);
        ButterKnife.bind(this,view);
    }

    public void setmAdapter(List<Message> messages) {
        mAdapter = new ChatAdapter(messages);
        recyclerView.setAdapter(mAdapter);
        LinearLayoutManager mLayout= new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayout);
    }

    public View view() {
        return view;
    }

    /*public void swapAdapter(List<Message> messages)
    {
        mAdapter.swapAdapter(messages);
    }*/

    public void notifyDataChanged() {
        recyclerView.smoothScrollToPosition(mAdapter.getItemCount()-1);
        mAdapter.notifyDataSetChanged();
        this.clearText(textView);
    }

    public void toastMessage(String message) {
        Toast.makeText(view.getContext(),message,Toast.LENGTH_SHORT).show();
    }



    public void clearText(TextView text){
        text.setText("");
    }

    public String getMessageText() {
        return textView.getText().toString();
    }
}
