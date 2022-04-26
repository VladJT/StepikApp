package jt.projects.stepikapp.loginpasswordapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
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
        TextView tLoginInfo = findViewById(R.id.textViewLoginInfo);

        Intent intent = getIntent();
        String login = intent.getStringExtra("login");
        String password = intent.getStringExtra("password");

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/jokerman.ttf");
        tLoginPassword.setTypeface(tf);
        tLoginPassword.setText("Login: " + login + "\npassword: " + password);

        tLoginInfo.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/colonna.ttf"));
        String[] versions = getResources().getStringArray(R.array.version_names);
        StringBuilder sb = new StringBuilder();
        for(String st:versions) sb.append(st+"\n");
        tLoginInfo.setText(sb.toString());
    }
}
