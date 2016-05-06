package com.example.martinsaad.hackidc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button registerButton = (Button) findViewById(R.id.button_Register_buttonRegister);
        final EditText firstName = (EditText) findViewById(R.id.editText_Register_firstName);
        final EditText lastName = (EditText) findViewById(R.id.editText_Register_lastName);
        DatePicker birthDate = (DatePicker) findViewById(R.id.datePicker_Register_birthDay);
        Switch gender = (Switch) findViewById(R.id.switch_Register_gender);
        EditText email = (EditText) findViewById(R.id.editText_Register_email);
        final EditText password = (EditText) findViewById(R.id.editText_Register_password);
        final EditText repeatPassword = (EditText) findViewById(R.id.editText_Register_repeatPassword);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = password.getText().toString();
                String repeatPass = repeatPassword.getText().toString();
                //User newUser = new User(firstName.getText().toString(), lastName.getText().toString());
            }
        });
    }
}
