package com.example.gustavobarbosab.ninemessage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gustavobarbosab.ninemessage.R;
import com.example.gustavobarbosab.ninemessage.domain.Message;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by gustavobarbosab on 27/01/18.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private List<Message> messages;

    public RecycleAdapter(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.recycle_layout,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecycleAdapter.ViewHolder holder, int position) {
        holder.textView.setText(messages.get(position).getMessage());
        Context context = holder.imageView.getContext();
        Picasso.with(context)
                .load("https://api.adorable.io/avatars/285/:gustavo.png")
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return this.messages.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.textMessage);
            imageView = v.findViewById(R.id.image_user);
        }
    }
}
