package com.example.martinsaad.hackidc;


import android.content.Context;
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
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class ExerciseDetailsFragment extends Fragment {
    ImageButton startChrono;
    Chronometer chrono;
    long time = 0;
    FragmentCommunicator fragmentCommunicator;
    static int flag = 1;
    List<Exercises> data = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercise_details, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentCommunicator = (FragmentCommunicator) getActivity();
        fragmentCommunicator.passString("enableDrawer");
        final ImageButton nextBtn = (ImageButton) getActivity().findViewById(R.id.next);
        final ImageButton changeExercise = (ImageButton) getActivity().findViewById(R.id.button_swap_exercise);
        TextView exerciseName = (TextView) getActivity().findViewById(R.id.ExerciseName);
        startChrono = (ImageButton) getActivity().findViewById(R.id.timer);
        chrono = (Chronometer) getActivity().findViewById(R.id.chronometer);

        final EditText newSet1, newSet2, newSet3, newSet4, newSet5;
        newSet1 = (EditText) getActivity().findViewById(R.id.newSet1);
        newSet2 = (EditText) getActivity().findViewById(R.id.newSet2);
        newSet3 = (EditText) getActivity().findViewById(R.id.newSet3);
        newSet4 = (EditText) getActivity().findViewById(R.id.newSet4);
        newSet5 = (EditText) getActivity().findViewById(R.id.newSet5);

        TextView set1, set2, set3, set4, set5;
        set1 = (TextView) getActivity().findViewById(R.id.Set1);
        set2 = (TextView) getActivity().findViewById(R.id.Set2);
        set3 = (TextView) getActivity().findViewById(R.id.Set3);
        set4 = (TextView) getActivity().findViewById(R.id.Set4);
        set5 = (TextView) getActivity().findViewById(R.id.Set5);

        final Exercises ex = data.get(0);

        //set values
        exerciseName.setText(ex.getExerciseInstance().getName());
        String[] arr = ex.getLatestExercieseProgress().getWeight().split(",");
        set1.setText(arr[0]);
        set2.setText(arr[1]);
        set3.setText(arr[2]);
        set4.setText(arr[3]);
        set5.setText(arr[4]);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 1) {
                    List<String> params = new ArrayList<>();
                    params.add(Constants.TRAINING_PLAN_EXERCISE_PROGRESSES);
                    JSONObject json = new JSONObject();
                    try {
                        json.put("training_plan_exercise_details", ex.getId());
                        json.put("sets", 5);
                        json.put("weight", newSet1.getText().toString()+","+newSet2.getText().toString()+","+newSet3.getText().toString()+","+newSet4.getText().toString()+","+newSet5.getText().toString());
                        json.put("breaks_between_sets", ex.getBreaksBetweenExercieses());

                        Request r = new Request("POST", params, json.toString());

                        new HttpRequest(new AsyncResponse() {
                            @Override
                            public void processFinish(String output) {

                            }
                        }).execute(r, null, null);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("IDC", newSet1.getText().toString() + "," + newSet2.getText().toString() + "," + newSet3.getText().toString() + "," + newSet4.getText().toString() + "," + newSet5.getText().toString());
            }
        });

        changeExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == 1){
                    flag = 0;
                    Log.d("adi", "onClick:aaaaa");
                    List<String> params = new ArrayList<>();
                    params.add("exercises");
                    params.add("1");
                    params.add(Constants.readFromFile(getActivity().getApplicationContext()));
                    params.add("get_replacement");
                    JSONObject json = new JSONObject();

                    //json.put("username", username.getText().toString());
                    //json.put("password", password.getText().toString());
                    Request r = new Request("GET", params, null);

                    new HttpRequest(new AsyncResponse() {
                        @Override
                        public void processFinish(String output) {
                            try {
                                JSONArray jsonArray = new JSONArray(output);
                                JSONObject Jobject = jsonArray.getJSONObject(0);
                                Gson gson = new Gson();
                                Exercises e = gson.fromJson(Jobject.toString(), Exercises.class);
                                //exerciseName.setText(e.getExerciseInstance().getName());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }).execute(r, null, null);
                } else {

                }
            }
        });

    startChrono.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.timer:
                    chrono.setBase(SystemClock.elapsedRealtime() + time);
                    chrono.start();
                    break;
            }
        }
    });
     /*   btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });*/
}

    public static void readFromFile() throws IOException {
        String fileName="res/user_id.txt";
        FileReader inputFile = new FileReader(fileName);
        BufferedReader bufferReader = new BufferedReader(inputFile);
        String line;

        while ((line = bufferReader.readLine()) != null)   {
            System.out.println(line);
        }
        bufferReader.close();
    }
}
