package com.example.gustavobarbosab.ninemessage.screens.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.gustavobarbosab.ninemessage.R;
import com.example.gustavobarbosab.ninemessage.screens.chat.dagger.DaggerChatComponent;
import com.example.gustavobarbosab.ninemessage.screens.chat.mvp.ChatView;
import com.example.gustavobarbosab.ninemessage.screens.chat.dagger.ChatModule;
import com.example.gustavobarbosab.ninemessage.application.MainApplication;
import com.example.gustavobarbosab.ninemessage.screens.chat.mvp.ChatPresenter;

import javax.inject.Inject;

import dagger.android.AndroidInjection;


public class ChatActivity extends AppCompatActivity{

    @Inject
    ChatView view;

    @Inject
    ChatPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerChatComponent.builder()
                .appComponent(MainApplication.getComponent())
                .chatModule(new ChatModule(this))
                .build()
                .inject(this);

        setContentView(view.view());
        presenter.onCreate(getIntent().getExtras());

    }

    @Override
    protected void onSaveInstanceState(Bundle state){
        presenter.saveInstance(state);
        super.onSaveInstanceState(state);
    }

    @Override
    protected void onRestoreInstanceState(Bundle state){
        super.onRestoreInstanceState(state);
        presenter.restoreInstance(state);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.login:
                view.login();
                return true;
            case R.id.close:
                close();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void close(){
        this.finish();
        System.exit(0);
    }




    public ChatPresenter getPresenter() {
        return presenter;
    }


}
