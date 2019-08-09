package com.example.trays_foodapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

   private DrawerLayout drawer;
   private ActionBarDrawerToggle mToggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView= findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.Open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }



    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
       if(menuItem.getItemId() == R.id.promo)
       {
           Intent intent = new Intent(getApplicationContext(),promotion_list.class);
           startActivity(intent);

       }
         if(menuItem.getItemId() == R.id.db)
        {
            Intent intent = new Intent(getApplicationContext(),home.class);
            startActivity(intent);
        }
         if(menuItem.getItemId() == R.id.search)
        {
            Intent intent = new Intent(getApplicationContext(),search_bar.class);
            startActivity(intent);
        }
        if(menuItem.getItemId() == R.id.shop)
        {
            Intent intent = new Intent(getApplicationContext(),shopView_list.class);
            startActivity(intent);
        }
         if(menuItem.getItemId() == R.id.profile)
        {
            Intent intent = new Intent(getApplicationContext(),user_update.class);
            startActivity(intent);
        }
         if(menuItem.getItemId() == R.id.signout) {
             Intent intent = new Intent(getApplicationContext(), SignIn.class);
             startActivity(intent);
         }
        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
       return true;
    }

    public void juicelist(View view) {

        Intent intent = new Intent(this,juice_view_list.class);
        startActivity(intent);
    }

    public void kottuorder(View view) {
        Intent intent = new Intent(this,Kottu_view_list.class);
        startActivity(intent);
    }

    public void pizzaorder(View view) {
        Intent intent = new Intent(this,pizza_view_list.class);
        startActivity(intent);
    }


    public void riceorder(View view) {
        Intent intent = new Intent(this,rice_view_list.class);
        startActivity(intent);
    }
}
