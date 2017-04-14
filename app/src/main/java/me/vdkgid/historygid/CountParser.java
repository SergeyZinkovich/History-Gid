package me.vdkgid.historygid;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.SimpleAdapter;

import com.google.android.gms.drive.Contents;
import com.google.android.gms.maps.GoogleMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class CountParser extends AsyncTask<Void, Void, String> {

    GoogleMap googleMap;
    int mode;
    CustomListAdapter simpleAdapter;
    ArrayList<String> Names = new ArrayList<String>();
    ArrayList<String> Icons = new ArrayList<String>();

    public CountParser(GoogleMap googleMap, int mode){
        this.mode = mode;
        this.googleMap = googleMap;
    }

    public CountParser(CustomListAdapter simpleAdapter, int mode, ArrayList<String> Names, ArrayList<String> Icons){
        this.mode = mode;
        this.simpleAdapter = simpleAdapter;
        this.Names = Names;
        this.Icons = Icons;
    }

    @Override
    protected String doInBackground(Void ... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        try {
            URL url = new URL("http://62.109.7.158/api/places/");

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            resultJson = buffer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultJson;
    }

    @Override
    protected void onPostExecute(String strJson) {
        super.onPostExecute(strJson);

        JSONObject dataJsonObj = null;

        try {
            dataJsonObj = new JSONObject(strJson);
            int count = dataJsonObj.getInt("count");

            for (int i = 1; i <= count+1; i++) {
                if(mode == 1) {
                    InfoParser infoParser = new InfoParser(googleMap, i, mode);
                    infoParser.execute();
                }
                if(mode == 2) {
                    InfoParser infoParser = new InfoParser(i, mode, simpleAdapter, Names, Icons);
                    infoParser.execute();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
