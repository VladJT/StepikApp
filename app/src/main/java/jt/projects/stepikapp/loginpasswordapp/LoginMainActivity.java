package jt.projects.stepikapp.loginpasswordapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import jt.projects.stepikapp.R;

public class LoginMainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etLogin;
    private EditText etPassword;
    private Button bSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpwd);
        initViewComponents();
    }

    private void initViewComponents() {
        etLogin = findViewById(R.id.editTextLogin);
        etPassword = findViewById(R.id.editTextPassword);
        bSubmit = findViewById(R.id.buttonSubmitPassword);
        bSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("login", etLogin.getText().toString());
        intent.putExtra("password", etPassword.getText().toString());
        startActivity(intent);
    }
}
