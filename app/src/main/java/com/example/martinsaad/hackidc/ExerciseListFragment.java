package com.example.martinsaad.hackidc;

import android.content.Context;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;
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


public class ExerciseListFragment extends ListFragment implements OnItemClickListener {
    FragmentCommunicator fragmentCommunicator;
    List<Exercises> data = null;
    MyAdapter adapter = null;
    static String userId = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercise_list, container, false);
    }

    private void handleJson(String response){
        try {
            Gson gson = new Gson();
            JSONArray arr = new JSONArray(response);
            for (int i=0; i<arr.length(); i++){
                JSONObject obj = arr.getJSONObject(i);
                Exercises ex = gson.fromJson(obj.toString(), Exercises.class);
                data.add(ex);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentCommunicator = (FragmentCommunicator) getActivity();
        if (isUserLoggedIn(getActivity().getApplicationContext()) == true) {
            boolean flag = isUserLoggedIn(getActivity().getApplicationContext());
            data = new ArrayList<>();
            getListView().setOnItemClickListener(this);
            fragmentCommunicator.passString("enableDrawer");

            FloatingActionButton floationButton = (FloatingActionButton) getActivity().findViewById(R.id.button_exercise_list);

            List<String> parameters = new ArrayList<>();
            parameters.add(Constants.TRAINEES);
            parameters.add(userId);
            parameters.add(Constants.GET_CURRENT_TRAINING_PLAN_EXERCIESES);
            Request r = new Request("GET", parameters, null);

            try {
                new HttpRequest(new AsyncResponse() {
                    @Override
                    public void processFinish(String output) {
                        handleJson(output);
                        adapter = new MyAdapter();
                        setListAdapter(adapter);
                    }
                }).execute(r, null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }

            floationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragmentCommunicator.passData(new Object[]{"exerciseDetailsFragment", data});
                }
            });
        }
        else {
            fragmentCommunicator.passString("loginFragment");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        //Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                LayoutInflater inflater =  getActivity().getLayoutInflater();
                convertView = inflater.inflate(R.layout.tableview_row_exercise_list,null);
                Log.d("TAG", "create view:" + position);
            }

            else {
                Log.d("TAG", "use convert view:" + position);
            }

            TextView name = (TextView) convertView.findViewById(R.id.textView_Row_exercise_name);
            name.setText(data.get(position).getExerciseInstance().getName());
            return convertView;
        }
    }
    public static boolean isUserLoggedIn(Context context){

        try {
            InputStream inputStream = context.openFileInput("user_id.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                userId = stringBuilder.toString();
                return true;
            }
            else
                return false;
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return false;
    }
}
