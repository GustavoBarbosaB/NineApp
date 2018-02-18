package com.example.gustavobarbosab.ninemessage.screens.chat.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gustavobarbosab.ninemessage.R;
import com.example.gustavobarbosab.ninemessage.screens.chat.recycler.Items.HolderItem;
import com.example.gustavobarbosab.ninemessage.screens.chat.recycler.holder.AlertHolder;
import com.example.gustavobarbosab.ninemessage.screens.chat.recycler.holder.MyMessageHolder;
import com.example.gustavobarbosab.ninemessage.screens.chat.recycler.holder.TheyMessageHolder;
import com.example.gustavobarbosab.ninemessage.screens.chat.recycler.holder.ViewHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavobarbosab on 27/01/18.
 */

public class ChatAdapter extends RecyclerView.Adapter<ViewHolder> implements Serializable {

    private ArrayList<HolderItem> messages;

    public ChatAdapter(ArrayList<HolderItem> messages) {
        this.messages = messages;
    }

    @Override
    public int getItemViewType(int position) {
        return messages.get(position).getListItem();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View view = null;

        switch (type) {
            case HolderItem.MY_MESSAGE:
                view = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.my_recycle_message, parent, false);
                return new MyMessageHolder(view);
            case HolderItem.THEY_MESSAGE:
                view = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.they_recycle_message, parent, false);
                return new TheyMessageHolder(view);
            case HolderItem.ALERT:
                view = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.recycle_alert, parent, false);
                return new AlertHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HolderItem item = messages.get(position);
        holder.bindType(item);
    }

    public void setMessages(ArrayList<HolderItem> messages){
        this.messages.clear();
        this.messages.addAll(messages);
    }

    @Override
    public int getItemCount() {
        return this.messages.size();
    }




}
