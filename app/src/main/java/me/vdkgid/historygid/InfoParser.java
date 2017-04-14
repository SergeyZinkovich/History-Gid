package me.vdkgid.historygid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class InfoParser extends AsyncTask<Void, Void, String> {
    public int index;
    GoogleMap googleMap;
    int mode;
    CustomListAdapter simpleAdapter;
    ArrayList<String> Names = new ArrayList<String>();
    ArrayList<String> Icons = new ArrayList<String>();
    ImageView imageView;
    TextView nameTextView;
    TextView descriptionTextView;
    Context context;


    public InfoParser(GoogleMap googleMap, int index, int mode){
        this.googleMap = googleMap;
        this.index = index;
        this.mode = mode;
    }

    public InfoParser(int index, int mode, CustomListAdapter simpleAdapter, ArrayList<String> Names, ArrayList<String> Icons){
        this.index = index;
        this.mode = mode;
        this.simpleAdapter = simpleAdapter;
        this.Names = Names;
        this.Icons = Icons;
    }

    public InfoParser(Context context, int index, int mode, ImageView imageView, TextView nameTextView, TextView descriptionTextView){
        this.index = index;
        this.nameTextView = nameTextView;
        this.imageView = imageView;
        this.descriptionTextView = descriptionTextView;
        this.mode = mode;
        this.context = context;
    }

    @Override
    protected String doInBackground(Void ... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        try {
            URL url = new URL("http://62.109.7.158/api/places/" + index + "/");

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
            switch (mode) {
                case 1:
                    dataJsonObj = new JSONObject(strJson);
                    String name = dataJsonObj.getString("name");
                    JSONArray locations = dataJsonObj.getJSONArray("locations");
                    JSONObject jsonObject = locations.getJSONObject(0);
                    double latitude = jsonObject.getDouble("latitude");
                    double longitude = jsonObject.getDouble("longitude");

                    addMarker(latitude, longitude, index, name);
                    break;
                case 2:
                    dataJsonObj = new JSONObject(strJson);
                    String name1 = dataJsonObj.getString("name");
                    String icon = dataJsonObj.getString("main_full");
                    Names.add( name1);
                    Icons.add(icon);
                    simpleAdapter.notifyDataSetChanged();
                    break;
                case 3:
                    dataJsonObj = new JSONObject(strJson);
                    String name2 = dataJsonObj.getString("name");
                    String icon1 = dataJsonObj.getString("main_full");
                    String description = dataJsonObj.getString("description");
                    Names.add( name2);
                    Icons.add(icon1);
                    nameTextView.setText(name2);
                    Picasso.with(context)
                            .load(icon1)
                            .into(imageView);
                    descriptionTextView.setText(description);
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void addMarker(double latitude, double longitude, int id, String name){
        if(null != googleMap){
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(latitude, longitude))
                    .title(name)
                    .snippet(new String()+id)
                    .draggable(false)
            );
        }
    }
}
