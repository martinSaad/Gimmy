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
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class ExerciseDetailsFragment extends Fragment {
    ImageButton startChrono;
    Chronometer chrono;
    long time = 0;
    static int flag = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercise_details, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       // final ImageButton btn = (ImageButton) getActivity().findViewById(R.id.button_swap_exercise);
        final ImageButton nextBtn = (ImageButton) getActivity().findViewById(R.id.next);
        final ImageButton changeExercise = (ImageButton) getActivity().findViewById(R.id.button_swap_exercise);
        final TextView exersiceName = (TextView)getActivity().findViewById(R.id.ExerciseName);
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

        changeExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == 1){
                flag = 0;
                Log.d("adi", "onClick:aaaaa");
                List<String> params = new ArrayList<>();
                params.add("exercises");
                params.add("1");
                params.add("get_replacement");
                JSONObject json = new JSONObject();

                    //json.put("username", username.getText().toString());
                    //json.put("password", password.getText().toString());
                    Request r = new Request("GET", params, null);

                    new HttpRequest(new AsyncResponse() {
                        @Override
                        public void processFinish(String output) {
                            if (output==null){
                                //Toast.makeText(getApplicationContext(), "wrong credentials", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                try {
                                    JSONArray jsonArray = new JSONArray(output);
                                    JSONObject Jobject = jsonArray.getJSONObject(0);
                                    Gson gson = new Gson();
                                    Exercies ex = gson.fromJson(Jobject.toString(), Exercies.class);

                                    System.out.println(Jobject.toString());
                                } catch (JSONException e) {
                                    System.out.println("2222222");
                                    e.printStackTrace();
                                }

                                System.out.println("**********");
                                exersiceName.setText("aaaa");
                                //System.out.println(output);
                            }
                        }
                    }).execute(r, null, null);
                    //startActivity(mainIntent);


            }
            else{

            }
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
