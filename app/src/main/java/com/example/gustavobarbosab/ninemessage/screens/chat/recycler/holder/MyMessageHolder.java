package com.example.gustavobarbosab.ninemessage.screens.chat.recycler.holder;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gustavobarbosab.ninemessage.R;
import com.example.gustavobarbosab.ninemessage.screens.chat.recycler.Items.HolderItem;
import com.example.gustavobarbosab.ninemessage.screens.chat.recycler.Items.MyMessageItem;
import com.example.gustavobarbosab.ninemessage.util.CircleTransform;
import com.squareup.picasso.Picasso;

/**
 * Created by gustavobarbosab on 12/02/18.
 */

public class MyMessageHolder extends ViewHolder {
    private TextView textView;

    public TextView getTextView() {
        return textView;
    }

    public MyMessageHolder(View v) {
        super(v);
        textView = v.findViewById(R.id.myText);

    }

    @Override
    public void bindType(HolderItem item) {
        textView.setText(((MyMessageItem)item).getMessage());
    }
}
