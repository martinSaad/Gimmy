package com.example.martinsaad.hackidc;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by martinsaad on 06/05/2016.
 */
public class Constants {
    public final static String DB_BASE_URL = "http://calm-anchorage-58622.herokuapp.com/api/v1/gymmy";
    public final static String TRAINEES = "trainees";
    public final static String USER_LOGIN = "user-log-in";
    public final static String GET_CURRENT_TRAINING_PLAN_EXERCIESES = "get_current_training_plan_exercises";


    public static String readFromFile(Context cnotext) {

        String ret = "";

        try {
            InputStream inputStream = cnotext.openFileInput("user_id.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
