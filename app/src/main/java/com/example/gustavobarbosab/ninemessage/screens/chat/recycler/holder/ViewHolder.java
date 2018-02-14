package com.example.gustavobarbosab.ninemessage.screens.chat.recycler.holder;

import android.app.LauncherActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.gustavobarbosab.ninemessage.screens.chat.recycler.Items.HolderItem;

/**
 * Created by gustavobarbosab on 10/02/18.
 */

public abstract class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindType(HolderItem item);
}