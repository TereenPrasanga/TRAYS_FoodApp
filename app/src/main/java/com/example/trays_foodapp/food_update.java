package com.example.trays_foodapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class food_update extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_update);
    }

    public void update(View view) {
        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
    }
}
