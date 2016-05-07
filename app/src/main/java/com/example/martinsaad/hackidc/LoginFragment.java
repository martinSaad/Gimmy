package com.example.martinsaad.hackidc;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
        EditText username = (EditText) getActivity().findViewById(R.id.editText_usernameLogin);
        EditText password = (EditText) getActivity().findViewById(R.id.editText_passwordLogin);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO make login request and save user is
                fragmentCommunicator.passString("main");
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentCommunicator.passString("register");
            }
        });
    }

}
