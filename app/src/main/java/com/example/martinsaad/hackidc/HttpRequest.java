package com.example.martinsaad.hackidc;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by martinsaad on 06/05/2016.
 */
public abstract class HttpRequest {
    private String doGet(List<String> parameters) throws IOException {

        HttpURLConnection con = null;
        BufferedReader in = null;
        String url = Constants.DB_BASE_URL;

        try {
            //add parameters
            for (String parameter : parameters)
                url+="/" + parameter;

            URL obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }



            //print result
            System.out.println("Response: " + response.toString());

            return response.toString();

        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException();
        } finally {
            con.disconnect();
            in.close();
        }

    }

    private void doPost(String body, List<String> parameters) throws IOException {
        HttpURLConnection con = null;
        BufferedReader in = null;
        String url = Constants.DB_BASE_URL;

        try {
            //add parameters
            for (String parameter : parameters)
                url+="/" + parameter;

            URL obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();

            con.setRequestProperty("Content-Type", "application/json");


            con.setRequestMethod("POST");
            con.setDoOutput(true);

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(body);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post body : " + body);
            System.out.println("Response Code : " + responseCode);

            in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            //print result
            System.out.println("Response: " + response.toString());

        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException();
        } finally {
            con.disconnect();
            in.close();
        }
    }
}
