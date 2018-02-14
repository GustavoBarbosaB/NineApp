package com.example.gustavobarbosab.ninemessage.screens.chat.recycler.holder;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gustavobarbosab.ninemessage.R;
import com.example.gustavobarbosab.ninemessage.screens.chat.recycler.Items.HolderItem;
import com.example.gustavobarbosab.ninemessage.screens.chat.recycler.Items.MyMessageItem;
import com.example.gustavobarbosab.ninemessage.screens.chat.recycler.Items.TheyMessageItem;
import com.example.gustavobarbosab.ninemessage.util.CircleTransform;
import com.squareup.picasso.Picasso;

/**
 * Created by gustavobarbosab on 10/02/18.
 */

public class TheyMessageHolder extends ViewHolder {
    private TextView textView;
    private ImageView imageView;
    private TextView username;
    private CardView cardView;

    public TextView getTextView() {
        return textView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public CardView getCardView() {
        return cardView;
    }

    public TheyMessageHolder(View v) {
        super(v);
        textView = v.findViewById(R.id.textMessage);
        imageView = v.findViewById(R.id.image_user);
        cardView = v.findViewById(R.id.card_receiver);
        username = v.findViewById(R.id.textUser);
    }

    @Override
    public void bindType(HolderItem item) {
        textView.setText(((TheyMessageItem)item).getMessage());
        username.setText(((TheyMessageItem)item).getUsername());
        Context context = imageView.getContext();
        Picasso.with(context)
                .load("https://api.adorable.io/avatars/285/"+((TheyMessageItem)item).getUsername())
                .transform(new CircleTransform())
                .into(imageView);
    }
}
