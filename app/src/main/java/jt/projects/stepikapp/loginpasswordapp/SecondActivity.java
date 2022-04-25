package jt.projects.stepikapp.loginpasswordapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import jt.projects.stepikapp.R;

public class SecondActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpwd_2);
        TextView tLoginPassword = findViewById(R.id.textViewLoginPassword);
        Intent intent = getIntent();
        String login = intent.getStringExtra("login");
        String password = intent.getStringExtra("password");
        tLoginPassword.setText("Login: " + login + "\npassword: " + password);
    }
}
