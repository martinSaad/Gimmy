package com.example.martinsaad.hackidc;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class RegisterFragment extends Fragment {

    FragmentCommunicator fragmentCommunicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentCommunicator = (FragmentCommunicator) getActivity();
        fragmentCommunicator.passString("cancelDrawer");
        Button registerButton = (Button) getActivity().findViewById(R.id.button_Register_buttonRegister);
        final EditText firstName = (EditText) getActivity().findViewById(R.id.editText_Register_firstName);
        final EditText lastName = (EditText) getActivity().findViewById(R.id.editText_Register_lastName);
        final DatePicker birthDate = (DatePicker) getActivity().findViewById(R.id.datePicker_Register_birthDay);
        final Switch gender = (Switch) getActivity().findViewById(R.id.switch_Register_gender);
        final EditText email = (EditText) getActivity().findViewById(R.id.editText_Register_email);
        final EditText password = (EditText) getActivity().findViewById(R.id.editText_Register_password);
        final EditText repeatPassword = (EditText) getActivity().findViewById(R.id.editText_Register_repeatPassword);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = password.getText().toString();
                String repeatPass = repeatPassword.getText().toString();

                if (firstName.getText().toString().isEmpty() == false && lastName.getText().toString().isEmpty() == false && email.getText().toString().isEmpty() == false && password.getText().toString().isEmpty() == false && repeatPassword.getText().toString().isEmpty() == false && birthDate.getText().toString().isEmpty() == false){
                    if (pass.equals(repeatPass)){
                        String gend = gender.isChecked() ? "Male" : "Female";
                        User user = new User(firstName.getText().toString(), lastName.getText().toString(), email.getText().toString(), pass, gend, birthDate.getText().toString());

                        Gson gson = new Gson();
                        String json = gson.toJson(user);
                        List<String> parameters = new ArrayList<String>();
                        parameters.add(Constants.TRAINEES);
                        Request r = new Request("POST", parameters, json);
                        try {
                            new HttpRequest(new AsyncResponse() {
                                @Override
                                public void processFinish(String output) {

                                }
                            }).execute(r, null, null);
                        }catch (Exception e){
                            Log.d("error", e.toString());
                        }
                    }
                    else{
                        Toast.makeText(getActivity(), "passwords are not equal", Toast.LENGTH_SHORT).show();
                    }
                }

                else {
                    Toast.makeText(getActivity(), "not all fields are full", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
