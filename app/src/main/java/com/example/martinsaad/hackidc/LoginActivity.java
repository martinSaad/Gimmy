package com.example.martinsaad.hackidc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Intent mainIntent = new Intent(this, MainActivity.class);
        final Intent registerIntent = new Intent(this, RegisterActivity.class);


        Button loginButton = (Button) findViewById(R.id.button_Login);
        EditText username = (EditText) findViewById(R.id.editText_usernameLogin);
        EditText password = (EditText) findViewById(R.id.editText_passwordLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO make login request and save user is
                startActivity(mainIntent);
            }
        });

        Button registerButton = (Button) findViewById(R.id.button_registerLogin);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(registerIntent);
            }
        });
    }
}
