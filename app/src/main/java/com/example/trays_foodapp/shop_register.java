package com.example.trays_foodapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class shop_register extends AppCompatActivity {

    EditText txtname,txtlocation,txtemail,txtphone,txtowner;
    Button btnsignupShop;
    DatabaseReference addRef;
    ShopClass shop;





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_register);

        txtname = findViewById(R.id.updateShopName);
        txtlocation = findViewById(R.id.updateShopAddress);
        txtemail = findViewById(R.id.updateShopEmail);
        txtphone = findViewById(R.id.updateShopPhone);
        txtowner = findViewById(R.id.insertShopOwnername);
        btnsignupShop = findViewById(R.id.btnsignUpShop);

        shop = new ShopClass();
        btnsignupShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRef = FirebaseDatabase.getInstance().getReference().child("Shops");

                try {

                    if (TextUtils.isEmpty(txtname.getText().toString()))
                    {
                        Toast.makeText(shop_register.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                    }else if (TextUtils.isEmpty(txtlocation.getText().toString()))
                    {
                        Toast.makeText(shop_register.this, "Please Enter address", Toast.LENGTH_SHORT).show();
                    }else if (TextUtils.isEmpty(txtemail.getText().toString()))
                    {
                        Toast.makeText(shop_register.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(txtphone.getText().toString()))
                    {
                        Toast.makeText(shop_register.this, "Please Enter Phone", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(txtowner.getText().toString()))
                    {
                        Toast.makeText(shop_register.this, "Please Enter Owner Name", Toast.LENGTH_SHORT).show();
                    }
                    else if((txtphone.getText().toString()).length() != 10)
                    {
                        Toast.makeText(shop_register.this, "Number Should be 10 Digits", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        shop.setName(txtname.getText().toString().trim());
                        shop.setEmail(txtemail.getText().toString().trim());
                        shop.setLocation(txtlocation.getText().toString().trim());
                        shop.setTelephone(txtphone.getText().toString().trim());
                        shop.setOwner(txtowner.getText().toString().trim());
                        addRef.child(txtphone.getText().toString().trim()).setValue(shop);
                        Intent intent = new Intent(shop_register.this,admin.class);
                        startActivity(intent);
                        Toast.makeText(shop_register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                    }

                }catch (NumberFormatException e)
                {
                    Toast.makeText(shop_register.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
