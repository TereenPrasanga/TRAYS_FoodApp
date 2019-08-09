package com.example.trays_foodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class shops extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);
    }

    public void vieworder(View view) {
        Intent intent = new Intent(this,shop_order_Accept_list.class);
        startActivity(intent);
    }

    public void editprofile(View view) {
        Intent intent = new Intent(this,shop_update.class);
        startActivity(intent);
    }


    public void logoutshop(View view) {
        Intent intent = new Intent(this,SignIn.class);
        startActivity(intent);
    }
}
