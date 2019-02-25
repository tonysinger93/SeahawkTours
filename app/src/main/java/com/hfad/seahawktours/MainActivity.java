package com.hfad.seahawktours;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.content.Intent;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private String last_building = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void onClickBuilding(View view) {
        //grabs value from spinner
        Spinner building = findViewById(R.id.building);
        String selectedBuilding = building.getSelectedItem().toString();
        last_building = selectedBuilding;
        //passes selected value to "last building" text view
        TextView previous_selection = findViewById(R.id.last_building);
        previous_selection.setText(last_building);
        //passes selected value to detail activity
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra( "retrieve", selectedBuilding);
        startActivity(intent);
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
}

