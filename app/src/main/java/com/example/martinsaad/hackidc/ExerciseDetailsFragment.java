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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



public class ExerciseDetailsFragment extends Fragment {
    ImageButton startChrono;
    Chronometer chrono;
    long time = 0;
    FragmentCommunicator fragmentCommunicator;
    static int flag = 1;
    List<Exercises> data;

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
        final TextView exerciseName = (TextView) getActivity().findViewById(R.id.ExerciseName);
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
                        json.put("weight", newSet1.getText().toString() + "," + newSet2.getText().toString() + "," + newSet3.getText().toString() + "," + newSet4.getText().toString() + "," + newSet5.getText().toString());
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
                if (flag == 1) {
                    flag = 0;
                    List<String> params = new ArrayList<>();
                    params.add("exercises");
                    try{
                        params.add(readFromFile(getContext()));
                    } catch (IOException e){Log.d("TAG","Error reading file");}
                    params.add(Constants.readFromFile(getActivity().getApplicationContext()));
                    params.add("get_replacement");
                    JSONObject json = new JSONObject();

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

        exerciseName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentCommunicator.passData(new Object[]{"exerciseInformationFragment", data});
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
    }

    public String readFromFile(Context context) throws IOException {
        String userId;
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
