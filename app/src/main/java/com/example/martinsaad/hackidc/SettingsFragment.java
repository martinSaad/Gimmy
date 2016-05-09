package com.example.martinsaad.hackidc;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.widget.Button;
import android.widget.EditText;


public class SettingsFragment extends Fragment {

    FragmentCommunicator fragmentCommunicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final EditText name, lname, email, breaks, weight;

        name = (EditText) getActivity().findViewById(R.id.editText_Settings_firstName);
        lname = (EditText) getActivity().findViewById(R.id.editText_Settings_lastName);
        email = (EditText) getActivity().findViewById(R.id.editText_Settings_email);
        breaks = (EditText) getActivity().findViewById(R.id.editText_Settings_restTimeExercise);
        weight = (EditText) getActivity().findViewById(R.id.editText_Settings_weight);

        List<String> params = new ArrayList<>();
        params.add("trainees");
        String id = userId(getActivity().getApplicationContext());
        params.add(id);
        //params.add(readFromFile(getActivity().getApplicationContext()));
        params.add("get_personal_details");
        final JSONObject json = new JSONObject();
        //json.put("username", username.getText().toString());
        //json.put("password", password.getText().toString());
        Request r = new Request("GET", params, null);
        new HttpRequest(new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                try {
                    JSONObject Jobject = new JSONObject(output);
                    String first_name = Jobject.get("first_name").toString();
                    String last_name = Jobject.get("last_name").toString();
                    JSONObject Jobject_intern = Jobject.getJSONObject("user");

                    String user_email = Jobject_intern.get("email").toString();
                    String user_breaks = Jobject.get("breaks_between_exercises").toString();
                    String user_weight = Jobject.get("weight").toString();
                    name.setText(first_name);
                    lname.setText(last_name);
                    email.setText(user_email);
                    breaks.setText(user_breaks);
                    weight.setText(user_weight);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).execute(r, null, null);
    }

    public static String userId(Context context) {
        final String userId;
        try {
            InputStream inputStream = context.openFileInput("user_id.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                userId = stringBuilder.toString();
                return userId;
            } else
                return null;
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return null;
    }
}

