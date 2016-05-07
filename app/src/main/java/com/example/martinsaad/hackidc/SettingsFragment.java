package com.example.martinsaad.hackidc;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        fragmentCommunicator = (FragmentCommunicator) getActivity();
        fragmentCommunicator.passString("enableDrawer");

        Button saveButton = (Button) getActivity().findViewById(R.id.button_Settings_buttonSaveChanges);

        EditText firstName = (EditText) getActivity().findViewById(R.id.editText_Settings_firstName);
        EditText lastName = (EditText) getActivity().findViewById(R.id.editText_Settings_lastName);
        EditText email = (EditText) getActivity().findViewById(R.id.editText_Settings_email);
        EditText pass = (EditText) getActivity().findViewById(R.id.editText_Settings_password);
        EditText repeatPass = (EditText) getActivity().findViewById(R.id.editText_Settings_repeatPassword);
        EditText weight = (EditText) getActivity().findViewById(R.id.editText_Settings_weight);
        EditText restBetweenSets = (EditText) getActivity().findViewById(R.id.editText_Settings_restTimeSets);
        EditText restBetweenMachines = (EditText) getActivity().findViewById(R.id.editText_Settings_restTimeExercise);





        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
