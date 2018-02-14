package com.example.gustavobarbosab.ninemessage.screens.chat.recycler.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gustavobarbosab.ninemessage.R;
import com.example.gustavobarbosab.ninemessage.screens.chat.recycler.Items.AlertItem;
import com.example.gustavobarbosab.ninemessage.screens.chat.recycler.Items.HolderItem;

import butterknife.ButterKnife;

/**
 * Created by gustavobarbosab on 10/02/18.
 */

public class AlertHolder extends ViewHolder {

    private TextView textView;

    public AlertHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textAlert);
    }

    @Override
    public void bindType(HolderItem item) {
        textView.setText(((AlertItem)item).getAlert());
    }
}
