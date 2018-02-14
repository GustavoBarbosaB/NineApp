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
import com.example.gustavobarbosab.ninemessage.screens.chat.recycler.Items.HolderItem;

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

    @BindView(R.id.sendButton)
    FloatingActionButton sendButton;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.textMessage)
    TextView textView;

    View view;

    private ChatAdapter mAdapter;

    private ChatActivity activity;

    public ChatView(ChatActivity context) {
        this.activity=context;
        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_main, parent, true);
        ButterKnife.bind(this,view);
    }

    public void setmAdapter(List<HolderItem> messages) {
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

    public String getMessageText() {
        return textView.getText().toString();
    }

    public ChatActivity getActivity() {
        return activity;
    }

    public void requestTextFocus() {
        textView.requestFocus();
    }

    public void setText(String text) {
        textView.setText(text);
    }
}
