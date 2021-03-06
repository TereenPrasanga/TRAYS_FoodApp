package com.example.trays_foodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }


    public void view_orders(View view) {
        Intent intent = new Intent(this,OrdersViewFoodsActivity.class);
        startActivity(intent);
    }

    public void add_foods(View view) {
        Intent intent = new Intent(this,add_item.class);
        startActivity(intent);
    }
    public void add_promo(View view) {
        Intent intent = new Intent(this,add_promotion.class);
        startActivity(intent);
    }

    public void managefoods(View view) {
        Intent intent = new Intent(this,AdminFoodsDeleteUpdateActivity.class);
        startActivity(intent);
    }

    public void managepromo(View view) {
        Intent intent = new Intent(this,admin_promo_delete_update_Activity.class);
        startActivity(intent);
    }

    public void addshops(View view) {
        Intent intent = new Intent(this,shop_register.class);
        startActivity(intent);
    }

    public void removeshop(View view) {
        Intent intent = new Intent(this,shop_update.class);
        startActivity(intent);
    }



    public void logout(View view) {
        Intent intent = new Intent(this,SignIn.class);
        startActivity(intent);
    }

    public void ViewShops(View view) {
        Intent intent = new Intent(this,ShowShops.class);
        startActivity(intent);
    }
}
