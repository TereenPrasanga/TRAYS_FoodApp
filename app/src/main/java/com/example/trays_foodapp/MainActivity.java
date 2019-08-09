package com.example.trays_foodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2;
    Animation frombottom,fromtop;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.btn1);
        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombuttom);
        btn1.setAnimation(frombottom);

        btn2 = (Button)findViewById(R.id.btn2);
        fromtop= AnimationUtils.loadAnimation(this,R.anim.fromtop);
        btn2.setAnimation(fromtop);


    }

    public void signin(View view) {
        Intent intent = new Intent(this,SignIn.class);
        startActivity(intent);

    }

    public void signup(View view) {
        Intent intent = new Intent(this,user_registration.class);
        startActivity(intent);
    }
}
