package com.example.gustavobarbosab.ninemessage.screens.chat.recycler;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gustavobarbosab.ninemessage.R;
import com.example.gustavobarbosab.ninemessage.models.Message;
import com.example.gustavobarbosab.ninemessage.util.CircleTransform;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.VISIBLE;

/**
 * Created by gustavobarbosab on 27/01/18.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> implements Serializable {

    private List<Message> messages;

    public ChatAdapter(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.recycle_layout,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ChatAdapter.ViewHolder holder, int position) {
        Context context;
        /*if(position%2==0){*/
            holder.textView.setVisibility(VISIBLE);
            holder.imageView.setVisibility(VISIBLE);
            holder.cardView.setVisibility(VISIBLE);
            holder.textView.setText(messages.get(position).getMessage());
            context = holder.imageView.getContext();
            Picasso.with(context)
                    .load("https://api.adorable.io/avatars/285/:gustavo"+position+".png")
                    .transform(new CircleTransform())
                    .into(holder.imageView);
        /*}else{
            holder.textSender.setVisibility(VISIBLE);
            holder.imageSender.setVisibility(VISIBLE);
            holder.carSender.setVisibility(VISIBLE);
            holder.textSender.setText(messages.get(position).getMessage());
            context = holder.imageSender.getContext();
            Picasso.with(context)
                    .load("https://api.adorable.io/avatars/285/:gustavo"+position+".png")
                    .transform(new CircleTransform())
                    .into(holder.imageSender);
        }*/
    }

    @Override
    public int getItemCount() {
        return this.messages.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public ImageView imageView;
        public CardView cardView;

        public TextView textSender;
        public ImageView imageSender;
        public CardView carSender;

        public ViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.textMessage);
            imageView = v.findViewById(R.id.image_user);
            imageSender= v.findViewById(R.id.image_sender);
            textSender = v.findViewById(R.id.textSender);
            cardView = v.findViewById(R.id.card_receiver);
            carSender = v.findViewById(R.id.card_sender);
        }
    }
}
