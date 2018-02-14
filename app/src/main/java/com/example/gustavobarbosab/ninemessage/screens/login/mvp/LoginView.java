package com.example.gustavobarbosab.ninemessage.screens.login.mvp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gustavobarbosab.ninemessage.R;
import com.example.gustavobarbosab.ninemessage.screens.chat.ChatActivity;
import com.example.gustavobarbosab.ninemessage.screens.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by gustavobarbosab on 11/02/18.
 */

public class LoginView {

    @BindView(R.id.usernameText)
    EditText editText;

    @BindView(R.id.loading)
    ProgressBar progressBar;

    @BindView(R.id.enterButton)
    Button button;

    LoginActivity loginActivity;

    private View view;

    public LoginView(LoginActivity loginActivity) {
        this.loginActivity=loginActivity;

        FrameLayout parent = new FrameLayout(loginActivity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(loginActivity).inflate(R.layout.activity_login, parent, true);
        ButterKnife.bind(this,view);

    }

    public View view() {
        return view;
    }

    @OnClick(R.id.enterButton)
    public void enterChat(){
        button.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        loginActivity.getPresenter()
                .attemptLogin();
    }


    public void chatIntent(String mUsername, int numUsers) {
        Intent intent = new Intent(loginActivity, ChatActivity.class);
        intent.putExtra("username", mUsername);
        intent.putExtra("numUsers", numUsers);
        loginActivity.setResult(RESULT_OK, intent);
        loginActivity.startActivity(intent);
        loginActivity.finish();
    }

    public void toastMessage(String message) {
        Toast.makeText(view.getContext(),message,Toast.LENGTH_SHORT).show();
    }

    public String getText() {
        return editText.getText().toString().trim();
    }

    public void focusText() {
        editText.requestFocus();
    }
}
