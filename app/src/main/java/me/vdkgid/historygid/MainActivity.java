package me.vdkgid.historygid;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    FragmentManager fragmentManager = getSupportFragmentManager();
    Artic articlFragment;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.mapstyle));
        //googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        addMarker(43.169106, 131.965553);
        googleMap.setMyLocationEnabled(true);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(43.169106, 131.965553))
                .zoom(10)
                .build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        googleMap.animateCamera(cameraUpdate);
    }

    private void addMarker(double X, double Y){
        if(null != googleMap){
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(X, Y))
                    .title("marker")
                    .snippet("safdsgdfsgdhdffgfkjhjfshgkdjfhgkdfj")
                    .draggable(false)
            );
            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    //Toast.makeText(MainActivity.this, marker.getTitle(),1000).show();// display toast
                    if (articlFragment == null){
                    articlFragment = new Artic();
                        Bundle bundle = new Bundle();
                        bundle.putString("Name", marker.getTitle());
                        articlFragment.setArguments(bundle);
                        FragmentTransaction fragmentTransaction = fragmentManager
                                .beginTransaction();
                        fragmentTransaction
                                .add(R.id.container, articlFragment, "Tag")
                                .commit();
                    }
                    return true;
                }
            });
            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    if (articlFragment != null) {
                        Artic fragment = (Artic) fragmentManager
                                .findFragmentByTag("Tag");
                        FragmentTransaction fragmentTransaction = fragmentManager
                                .beginTransaction();
                        fragmentTransaction.remove(fragment);
                        fragmentTransaction.commit();
                        articlFragment = null;
                    }
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);
    }


    public void onListButtonClick(View view) {
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        //intent.addFlags(Intent.EXTRA_DOCK_STATE_LE_DESK)
        startActivity(intent);
    }
}
