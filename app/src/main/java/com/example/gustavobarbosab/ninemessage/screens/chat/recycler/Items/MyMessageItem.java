package com.example.gustavobarbosab.ninemessage.screens.chat.recycler.Items;

import com.example.gustavobarbosab.ninemessage.domain.Message;

/**
 * Created by gustavobarbosab on 10/02/18.
 */

public class MyMessageItem extends Message implements HolderItem {
    public MyMessageItem(String message, String username) {
        super(message, username);
    }

    @Override
    public int getListItem() {
        return HolderItem.MY_MESSAGE;
    }
}
