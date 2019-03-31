package com.hfad.seahawktours;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.content.Intent;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.widget.ListView;
import android.widget.AdapterView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static com.hfad.seahawktours.Building.buildings;


public class MainActivity extends AppCompatActivity {

    private String last_building = null;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//        ArrayAdapter<Building> buildingArrayAdapter = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_list_item_1,
//                Building.buildings);

        //create OnItemClickListener
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        String selection = listView.getSelectedItem().toString();
                        intent.putExtra( "retrieve", selection);
                        startActivity(intent);
                    }
                };
        ListView listView = findViewById(R.id.building);
        //listView.setAdapter(buildingArrayAdapter);
        listView.setOnItemClickListener(itemClickListener);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //if activity restarts last building selected is still visible
        if(savedInstanceState != null){
            last_building = savedInstanceState.getString("last_building");
            TextView previous_selection = findViewById(R.id.last_building);
            previous_selection.setText(last_building);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("last_building", last_building);}

    @Override
    public void onPause(){
        super.onPause();
        TextView previous_selection = findViewById(R.id.last_building);
        String saveLast =  previous_selection.getText().toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public Double distance(Double x1, Double y1, Double x2, Double y2){
        return Math.sqrt( Math.pow((x2-x1),2) + Math.pow((y2-y1),2));
    }

    public void onNearestClick(View view){
        if(ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            onNearestClick(view);
        }
        else{
            //get location and pass intent

            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object

                                Double latitude = location.getLatitude();
                                Double longitude = location.getLongitude();

                                Building closeBuilding = null;
                                Double shortestDistance = 100.00;

                                for (Building b : buildings){
                                    if(distance(latitude, longitude, b.getLatitude(), b.getLongitude()) < shortestDistance){
                                        shortestDistance = distance(latitude, longitude, b.getLatitude(), b.getLongitude());
                                        closeBuilding = b;
                                    }
                                }
                                //pass building to DetailActivity
                                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                                String closeBuildingName = getString(closeBuilding.getName());
                                intent.putExtra( "retrieve", closeBuildingName);
                                startActivity(intent);
                            }
                            //if null do what?
                        }
                    });

        }
    }

}

