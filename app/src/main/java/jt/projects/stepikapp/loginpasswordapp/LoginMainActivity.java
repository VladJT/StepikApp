package jt.projects.stepikapp.loginpasswordapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
    private Button bCalc;


    private String login="";
    private String password="";
    private final static String TEXT = "THEME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpwd);
        loadSettings();
        initViewComponents();
    }

    private void initViewComponents() {
        etLogin = findViewById(R.id.editTextLogin);
        etPassword = findViewById(R.id.editTextPassword);
        bSubmit = findViewById(R.id.buttonSubmitPassword);
        bCalc= findViewById(R.id.buttonCalc);
        bCalc.setOnClickListener(this);
        bSubmit.setOnClickListener(this);
        etLogin.setText(login);
        etPassword.setText(password);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.buttonSubmitPassword) {
            saveSettings();
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("login", etLogin.getText().toString());
            intent.putExtra("password", etPassword.getText().toString());
            startActivity(intent);
        }

        if (v.getId()==R.id.buttonCalc) {
           try{
               Uri uri = Uri.parse("example://intent");
               Intent runEchoIntent = new Intent(Intent.ACTION_VIEW, uri);
               runEchoIntent.putExtra(TEXT, "0");
               startActivity(runEchoIntent);

           }
           catch (Exception e) {
               etLogin.setText(e.getMessage());
           }
        }
    }

    private void loadSettings() {
        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        login = sharedPref.getString("login", "???");
        password = sharedPref.getString("password", "");
    }

    private void saveSettings() {
        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("login", etLogin.getText().toString());
        editor.putString("password", etPassword.getText().toString());
        editor.apply();
    }
}
