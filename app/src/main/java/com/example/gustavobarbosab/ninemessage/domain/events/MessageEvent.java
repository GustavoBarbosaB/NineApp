package com.example.gustavobarbosab.ninemessage.domain.events;

import com.example.gustavobarbosab.ninemessage.domain.Message;

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

    public MessageEvent(Message message) {

        this.message = message;
    }

    public Message getMessage() {
        return message;
    }
}
