package com.example.gustavobarbosab.ninemessage.event;

import com.example.gustavobarbosab.ninemessage.domain.Message;
import com.example.gustavobarbosab.ninemessage.domain.MessageImpl;

import java.util.List;

/**
 * Created by gustavobarbosab on 27/01/18.
 */

public class MessageEvent {

    /**
     * Aqui criamos a classe usada pelo eventbus para gerar o LocalBroadcast
     */

    private Message message;

    public MessageEvent() {
    }

    public MessageEvent(MessageImpl message) {

        this.message = message;
    }

    public Message getMessage() {
        return message;
    }
}
