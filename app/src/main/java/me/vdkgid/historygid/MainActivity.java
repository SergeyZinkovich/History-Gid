package me.vdkgid.historygid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback  {

    GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.mapstyle));
        //googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        CountParser countParser = new CountParser(googleMap, 1);
        countParser.execute();

        googleMap.setMyLocationEnabled(true);
        if((googleMap.getCameraPosition().target.latitude == 0) || (googleMap.getCameraPosition().target.longitude == 0)) {
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(43.169106, 131.965553))
                    .zoom(10)
                    .build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
            googleMap.animateCamera(cameraUpdate);

            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    Intent intent = new Intent(MainActivity.this, ActivityDescription.class);
                    int id = Integer.parseInt(marker.getSnippet());
                    intent.putExtra("Id", id);
                    startActivity(intent);
                    return true;
                }
            });
        }
    }

    public void onListButtonClick(View view) {
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        startActivity(intent);

    }


}
