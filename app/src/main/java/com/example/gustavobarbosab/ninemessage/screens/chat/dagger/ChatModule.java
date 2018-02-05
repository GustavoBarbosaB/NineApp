package com.example.gustavobarbosab.ninemessage.screens.chat.dagger;

import com.example.gustavobarbosab.ninemessage.api.ChatService;
import com.example.gustavobarbosab.ninemessage.screens.chat.ChatActivity;
import com.example.gustavobarbosab.ninemessage.screens.chat.core.ChatModel;
import com.example.gustavobarbosab.ninemessage.screens.chat.core.ChatPresenter;
import com.example.gustavobarbosab.ninemessage.screens.chat.core.ChatView;

import org.greenrobot.eventbus.EventBus;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by gustavobarbosab on 05/02/18.
 */
@Module
public class ChatModule {
    ChatActivity chatActivity;

    public ChatModule(ChatActivity context){
        this.chatActivity=context;
    }

    @ChatScope
    @Provides
    ChatView provideView(){
        return new ChatView(chatActivity);
    }

    @ChatScope
    @Provides
    ChatPresenter providePresenter(ChatView chatView,ChatModel chatModel,EventBus eventBus){
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        return new ChatPresenter(chatView,chatModel,compositeDisposable,eventBus);
    }

    @ChatScope
    @Provides
    ChatActivity provideContext(){
        return chatActivity;
    }

    @ChatScope
    @Provides
    public EventBus getEventBus() {
        return EventBus.builder().build();
    }

    @ChatScope
    @Provides
    ChatModel provideModel(ChatService api){
        return new ChatModel(chatActivity,api);
    }
}
