package com.example.gustavobarbosab.ninemessage.screens.chat.recycler.Items;

import com.example.gustavobarbosab.ninemessage.domain.Message;

/**
 * Created by gustavobarbosab on 10/02/18.
 */

public class TheyMessageItem extends Message implements HolderItem {


    public TheyMessageItem(String message, String username) {
        super(message, username);
    }

    @Override
    public int getListItem() {
        return HolderItem.THEY_MESSAGE;
    }
}
