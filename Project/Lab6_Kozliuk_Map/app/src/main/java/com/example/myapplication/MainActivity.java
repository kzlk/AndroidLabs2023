package com.example.myapplication;

import static java.lang.String.format;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback  {

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient fusedLocationProviderClient;

    private static Map<String, mPoint> locations = new HashMap<>();
    private GoogleMap mMap;
    protected LocationManager locationManager;
    PolylineOptions polylineOptions = new PolylineOptions();

    Spinner spinner;

    int position_spinner;

    LatLng myPos;

    class mPoint
    {
        double first;
        double second;

        mPoint(double first, double second)
        {
            this.first = first;
            this.second = second;
        }
    }

    {
        locations.put("Головна будівля", new mPoint(49.835664417899494, 24.01443679983333));
        locations.put("Перший корпус", new mPoint(49.83534091276235, 24.010644727103653));
        locations.put("Другий корпус", new mPoint(49.83611705969751, 24.012392109423697));
        locations.put("Третій корпус", new mPoint(49.83653599231934, 24.01360437589593));
        locations.put("Четвертий корпус", new mPoint(49.836423507581806, 24.011372301879856));
        locations.put("П'ятий корпус", new mPoint(49.83499578639943, 24.00810262479517));
        locations.put("Шостий корпус", new mPoint(49.83522227115248, 24.006496308753526));
        locations.put("Сьомий корпус", new mPoint(49.83466293290917, 24.009693295262277));
        locations.put("Восьмий корпус", new mPoint(49.837843234646435, 24.01253141246118));
        locations.put("Дев'ятий корпус", new mPoint(49.836571707842964, 24.01433568177108));
        locations.put("Десятий корпус", new mPoint(49.8365301891598, 24.01510815798094));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        Dexter.withContext(getApplicationContext()).withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        myPos = getCurrentLocation();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();


        String[] items = {"Головна будівля", "Перший корпус", "Другий корпус", "Третій корпус", "Четвертий корпус", "П'ятий корпус", "Шостий корпус", "Сьомий корпус", "Восьмий корпус", "Дев'ятий корпус", "Десятий корпус"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        spinner.setSelection(5);
        position_spinner = 5;


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                position_spinner = position;
                mPoint mPoint = locations.get(selectedItem);
                assert mPoint != null;
                setloc(mPoint.first, mPoint.second);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing.
            }
        });

    }

    public void setloc(double lat, double lng) {
        if(myPos != null) {
            LatLng corpPos = new LatLng(lat, lng);
            float[] results = new float[1];
            Location.distanceBetween(myPos.latitude, myPos.longitude,
                    corpPos.latitude, corpPos.longitude, results);
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(corpPos).title(spinner.getAdapter().getItem(position_spinner) + ", відстань " + results[0] + "  m"));
            mMap.addMarker(new MarkerOptions().position(myPos).title("Я"));
            Polyline line = mMap.addPolyline(new PolylineOptions().width(5).color(Color.RED));
            mMap.addPolyline(polylineOptions);
            line.setPoints(Arrays.asList(corpPos, myPos));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(corpPos, 15.7f));
        }
    }

    public LatLng getCurrentLocation() {
        final LatLng[] latLng = new LatLng[1];

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(@NonNull GoogleMap googleMap) {
                        if(location != null) {
                            latLng[0] = new LatLng(location.getLatitude(),location.getLongitude());
                            myPos = latLng[0];
                            MarkerOptions markerOptions = new MarkerOptions().position(latLng[0]).title("Current Location !");
                            googleMap.addMarker(markerOptions);
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng[0],15));
                            mMap = googleMap;
                            mPoint mPoint1 = locations.get("Головна будівля");
                            assert mPoint1 != null;
                            setloc(mPoint1.first, mPoint1.second);


                        }
                        else {
                            Toast.makeText(MainActivity.this, "Please on your Location App Permissions", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

        return latLng[0];
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.addMarker(new MarkerOptions().position(myPos).title("Я"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPos, 18f));
    }
}
