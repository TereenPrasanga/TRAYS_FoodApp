package com.example.trays_foodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class beforeCart_showdetailsOfFood extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_cart_showdetails_of_food);
    }

    public void addtocart(View view) {
        Intent intent = new Intent(this,cart_list.class);
        startActivity(intent);
    }
}
