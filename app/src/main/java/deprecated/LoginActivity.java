/*
package deprecated;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.martinsaad.hackidc.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Intent mainIntent = new Intent(this, MainActivity.class);
        final Intent registerIntent = new Intent(this, RegisterActivity.class);


        Button loginButton = (Button) findViewById(R.id.button_Login);
        final EditText username = (EditText) findViewById(R.id.editText_usernameLogin);
        final EditText password = (EditText) findViewById(R.id.editText_passwordLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO make login request and save user is
                List<String> params = new ArrayList<>();
                params.add(Constants.USER_LOGIN);
                JSONObject json = new JSONObject();
                try {
                    json.put("username", username.getText().toString());
                    json.put("password", password.getText().toString());
                    Request r = new Request("POST", params, json.toString());

                    new HttpRequest(new AsyncResponse() {
                        @Override
                        public void processFinish(String output) {
                            if (output==null){
                                Toast.makeText(getApplicationContext(), "wrong credentials", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                //save user id to file
                                writeToFile(output);
                            }
                        }
                    }).execute(r, null, null);
                    startActivity(mainIntent);
                }catch (JSONException e) {
                    e.printStackTrace();
                }
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

    private void writeToFile(String userId) {
        String filename = "res/user_id.txt";
        String string = userId;
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/
