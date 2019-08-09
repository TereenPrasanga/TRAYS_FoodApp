package com.example.trays_foodapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class shop_register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_register);
    }

    public void signup(View view) {
        Toast.makeText(this, "Shop registered successfully", Toast.LENGTH_SHORT).show();
    }
}
