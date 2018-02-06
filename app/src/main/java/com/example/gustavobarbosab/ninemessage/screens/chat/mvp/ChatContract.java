package com.example.gustavobarbosab.ninemessage.screens.chat.mvp;

import com.example.gustavobarbosab.ninemessage.domain.Message;

/**
 * Created by gustavobarbosab on 06/02/18.
 */

public interface ChatContract {
    interface View{
        void sendMessage();
    }

    interface Model{
        Message sendMessage(String text);

    }

    interface Presenter{
        void sendMessage();

    }

}
