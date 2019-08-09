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
        Intent intent = new Intent(this,orders_customer_view_list.class);
        startActivity(intent);
    }

    public void add_foods(View view) {
        Intent intent = new Intent(this,add_item.class);
        startActivity(intent);
    }

    public void managefoods(View view) {
        Intent intent = new Intent(this,manage_view_foods_list.class);
        startActivity(intent);
    }

    public void managepromo(View view) {
        Intent intent = new Intent(this,manage_promotion_list.class);
        startActivity(intent);
    }

    public void addshops(View view) {
        Intent intent = new Intent(this,shop_register.class);
        startActivity(intent);
    }

    public void removeshop(View view) {
        Intent intent = new Intent(this,shop_delete_list.class);
        startActivity(intent);
    }

    public void removeuser(View view) {
        Intent intent = new Intent(this,RegisterdUserList.class);
        startActivity(intent);
    }

    public void logout(View view) {
        Intent intent = new Intent(this,SignIn.class);
        startActivity(intent);
    }
}
