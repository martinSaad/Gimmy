package com.example.martinsaad.hackidc;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class ExerciseInformationFragment extends Fragment {

    String machineName = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercise_information, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        WebView myWebView = (WebView)getActivity().findViewById( R.id.webView );
        myWebView.getSettings().setJavaScriptEnabled(true);
        WebSettings webSettings = myWebView.getSettings();
        String playVideo= "<html><body><iframe class=\"youtube-player\" type=\"text/html\" width=\"340.25\" height=\"300.25\" src=\"http://www.youtube.com/embed/bIPcobKMB94\" frameborder=\"0\"></body></html>";

        myWebView.loadData(playVideo, "text/html", "utf-8");
    }
}