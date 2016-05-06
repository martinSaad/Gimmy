package com.example.martinsaad.hackidc;


import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;


public class ExerciseDetailsFragment extends Fragment {
    ImageButton startChrono;
    Chronometer chrono;
    long time = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ImageButton btn = (ImageButton) getActivity().findViewById(R.id.button_swap_excerise);
        ImageButton nextBtn = (ImageButton) getActivity().findViewById(R.id.next);
        startChrono = (ImageButton)getActivity().findViewById(R.id.timer);
        chrono = (Chronometer)getActivity().findViewById(R.id.chronometer);


        final EditText set1,set2,set3,set4,set5;
        set1 = (EditText) getActivity().findViewById(R.id.newSet1);
        set2 = (EditText) getActivity().findViewById(R.id.newSet2);
        set3 = (EditText) getActivity().findViewById(R.id.newSet3);
        set4 = (EditText) getActivity().findViewById(R.id.newSet4);
        set5 = (EditText) getActivity().findViewById(R.id.newSet5);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("IDC",set1.getText().toString()+","+set2.getText().toString()+","+set3.getText().toString()+","+set4.getText().toString()+","+set5.getText().toString());
            }
        });

        startChrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.timer:
                        chrono.setBase(SystemClock.elapsedRealtime()+time);
                        chrono.start();
                        break;
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

}
