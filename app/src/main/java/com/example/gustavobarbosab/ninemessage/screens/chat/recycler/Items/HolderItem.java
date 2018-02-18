package com.example.gustavobarbosab.ninemessage.screens.chat.recycler.Items;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.Serializable;

/**
 * Created by gustavobarbosab on 10/02/18.
 */

public interface HolderItem extends Serializable {
    int MY_MESSAGE = 1;
    int THEY_MESSAGE = 2;
    int ALERT = 3;

    int  getListItem();
}
