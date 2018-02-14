package com.example.gustavobarbosab.ninemessage.screens.chat.recycler.Items;

/**
 * Created by gustavobarbosab on 10/02/18.
 */

public class AlertItem implements HolderItem{
    public AlertItem(String alert) {
        this.alert = alert;
    }

    private String alert;

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    @Override
    public int getListItem() {
        return HolderItem.ALERT;
    }
}
