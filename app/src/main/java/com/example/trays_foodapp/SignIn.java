package com.example.trays_foodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void login(View view) {
        Intent intent = new Intent(this,home.class);
        startActivity(intent);

        Toast.makeText(getApplicationContext(),"Successfully Logged In", Toast.LENGTH_SHORT).show();

    }

    public void adminLogin(View view) {

        Intent intent = new Intent(this,admin.class);
        startActivity(intent);

        Toast.makeText(getApplicationContext(),"Admin mode", Toast.LENGTH_SHORT).show();

    }


}
