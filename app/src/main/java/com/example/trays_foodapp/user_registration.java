package com.example.trays_foodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class user_registration extends AppCompatActivity {

    EditText txtname,txtaddress,txtphone,txtemail,txtpassword;
    Button btnsignup;
    DatabaseReference addRef;
    user_registrationClass user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        txtname = findViewById(R.id.updateShopName);
        txtaddress = findViewById(R.id.txtshopemail);
        txtphone = findViewById(R.id.txtshopphone);
        txtemail = findViewById(R.id.txtshoplocation);
        txtpassword = findViewById(R.id.txtshoppassword);

        btnsignup = findViewById(R.id.btnsignUpUser);
        user = new user_registrationClass();

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRef = FirebaseDatabase.getInstance().getReference().child("userRegistration");
                try {

                    if (TextUtils.isEmpty(txtname.getText().toString()))
                    {
                        Toast.makeText(user_registration.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(txtemail.getText().toString()))
                    {
                        Toast.makeText(user_registration.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    }else if (TextUtils.isEmpty(txtaddress.getText().toString()))
                    {
                        Toast.makeText(user_registration.this, "Please Enter address", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(txtphone.getText().toString()))
                    {
                        Toast.makeText(user_registration.this, "Please Enter Phone", Toast.LENGTH_SHORT).show();
                    }
                    else if (txtphone.getText().toString().length() != 10)
                    {
                        Toast.makeText(user_registration.this, "Number Should be 10 digits!", Toast.LENGTH_SHORT).show();
                    }else if (TextUtils.isEmpty(txtpassword.getText().toString()))
                    {
                        Toast.makeText(user_registration.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        user.setName(txtname.getText().toString().trim());
                        user.setEmail(txtemail.getText().toString().trim());
                        user.setAddress(txtaddress.getText().toString().trim());
                        user.setPhone(txtphone.getText().toString().trim());
                        user.setPassword(txtpassword.getText().toString().trim());

                        addRef.child(txtphone.getText().toString().trim()).setValue(user);
                        Intent intent = new Intent(user_registration.this,home.class);
                        startActivity(intent);
                        Toast.makeText(user_registration.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                    }

                }catch (NumberFormatException e)
                {
                    Toast.makeText(user_registration.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }


}
