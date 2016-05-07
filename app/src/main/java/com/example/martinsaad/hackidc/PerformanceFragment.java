package com.example.martinsaad.hackidc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PerformanceFragment extends Fragment {
    FragmentCommunicator fragmentCommunicator;
    private static final Random RANDOM = new Random();
    private int lastX = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_performance, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentCommunicator = (FragmentCommunicator) getActivity();
        fragmentCommunicator.passString("enableDrawer");

        List<String> params = new ArrayList<>();
        params.add("trainees");
        params.add("5");
        params.add("get_average_of_exercise_weight_by_date");

        Request r = new Request("GET", params, null);
        new HttpRequest(new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                if (output==null){
                }
                else {
                    try {
                        JSONArray jsonArray = new JSONArray(output);
                        GraphView graph = (GraphView) getActivity().findViewById(R.id.graph);
                        DataPoint[] dataPoints = new DataPoint[jsonArray.length()];
                        // data
                        for(int i = 0 ; i < jsonArray.length() ; i++) {
                            JSONObject jsonObj = jsonArray.getJSONObject(i);
                            int avg = (int) jsonObj.get("average");
                            dataPoints[i] = new DataPoint(i, avg);
                        }
                        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
                        graph.addSeries(series);
                        Viewport viewport = graph.getViewport();
                        viewport.setYAxisBoundsManual(true);
                        /*viewport.setMinY(0);
                        viewport.setMaxY(10);*/
                        viewport.setScrollable(true);

                    } catch (JSONException e){

                    }
                }
            }
        }).execute(r, null, null);
    }
}

