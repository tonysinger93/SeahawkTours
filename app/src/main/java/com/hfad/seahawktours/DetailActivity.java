package com.hfad.seahawktours;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.app.ActionBar;

import static com.hfad.seahawktours.Building.buildings;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //grabs data passed from main activity
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String data = getIntent().getExtras().getString("retrieve");
        TextView tw = findViewById(R.id.tw);
        ImageView building_pic = findViewById(R.id.building_pic);
        TextView extra_info = findViewById(R.id.extra_info);
        ImageView building_pic2 = findViewById(R.id.building_pic2);
        //puts building title into text view
        TextView buildingTitle = findViewById(R.id.buildingTitle);
        buildingTitle.setText(data);
        TextView url = findViewById(R.id.url);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);



        for (Building b : buildings ){
            if (getResources().getString(b.getName()).equals(data)){
                tw.setText(b.getDescription());
                building_pic.setImageResource(b.getImageResourceId());
                extra_info.setText(b.getAddinfo());
                building_pic2.setImageResource(b.getImageResourceId2());
                url.setText(b.getUrl());

            }
        }
    }
}
