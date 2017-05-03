package com.mitendero.tribuo.mitendero.Scanner;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Camilo Aguado on 4/27/2017.
 */

public class ScannerThread extends AsyncTask<String, Integer, String> {

    private static String URLhead = "http://192.168.0.12:8080/";
    //private static String URLhead = "http://tribuo-startup.herokuapp.com/";

    private static String URLtail = "productos/codigo/";


    @Override
    protected String doInBackground(String... params) {

        try {
            URL obj = new URL(URLhead + URLtail + params[0]);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            int rc = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
