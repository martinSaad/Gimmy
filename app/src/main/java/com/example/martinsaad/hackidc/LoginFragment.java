package com.example.martinsaad.hackidc;



import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class LoginFragment extends Fragment {

    FragmentCommunicator fragmentCommunicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentCommunicator = (FragmentCommunicator) getActivity();
        fragmentCommunicator.passString("cancelDrawer");
        Button loginButton = (Button) getActivity().findViewById(R.id.button_Login);
        Button registerButton = (Button) getActivity().findViewById(R.id.button_registerLogin);
        final EditText username = (EditText) getActivity().findViewById(R.id.editText_usernameLogin);
        final EditText password = (EditText) getActivity().findViewById(R.id.editText_passwordLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                            if(output == null || output.equals("error")){
                                Toast.makeText(getActivity().getApplicationContext(), "wrong credentials", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                //save user id to file
                                writeToFile(output);
                                fragmentCommunicator.passString("exerciseDetailsFragment");
                            }
                        }
                    }).execute(r, null, null);
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentCommunicator.passString("registerFragment");
            }
        });
    }

    private void writeToFile(String userId) {
        String filename = "user_id.txt";
        FileOutputStream outputStream;

        try {
            outputStream = getActivity().getApplicationContext().openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(userId.getBytes());
            outputStream.close();
            Log.d("IDC", "success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
