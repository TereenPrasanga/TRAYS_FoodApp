package com.example.trays_foodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class user_registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
    }

    public void signup(View view) {
        Intent intent = new Intent(this,home.class);
        startActivity(intent);

        Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();


    }
}
